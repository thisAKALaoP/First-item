
package com.taiji.erp.base.service.impl;

/**
 * 印点 基本信息 配置表
 *
 * @author yangcw * @date 2021-05-22 16:09:13
 */

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taiji.erp.admin.api.entity.SysOrg;
import com.taiji.erp.admin.api.feign.SysOrgClient;
import com.taiji.erp.base.api.entity.BaseArea;
import com.taiji.erp.base.api.entity.BaseBigarea;
import com.taiji.erp.base.api.entity.BaseYd;
import com.taiji.erp.base.api.entity.BaseYdDept;
import com.taiji.erp.base.api.vo.BaseYdVo;
import com.taiji.erp.base.api.vo.YdLinkPersonVo;
import com.taiji.erp.base.mapper.BaseYdMapper;
import com.taiji.erp.base.service.*;
import com.taiji.erp.common.core.util.R;
import com.taiji.erp.common.security.util.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BaseYdServiceImpl extends ServiceImpl<BaseYdMapper, BaseYd> implements BaseYdService {

    @Autowired
    private BaseYdDeptService baseYdDeptService;
    @Autowired
    private BaseYdLinkPersonService baseYdLinkPersonService;
    @Autowired
    private SysOrgClient sysOrgClient;
    @Autowired
    private BaseYdMapper baseYdMapper;
    @Autowired
    private BaseAreaService baseAreaService;
    @Autowired
    private BaseBigareaService baseBigareaService;

    @Override
    @Transactional
    public R<?> save(BaseYdVo baseYdVo) {

        //保存印点配置信息
        BaseYd baseYd = new BaseYd();
        BeanUtils.copyProperties(baseYdVo, baseYd);
        //设置优先级等信息
        if (Optional.ofNullable(baseYdVo.getPriority()).orElse(0) == 0) {
            baseYd.setPriority(this.count(new LambdaQueryWrapper<BaseYd>().eq(BaseYd::getDeleteFlag, 0)) + 1);
        } else {
            this.updatePriority(null, baseYd.getPriority());
        }
        baseYd.setCreateDate(LocalDateTime.now());
        baseYd.setCreateUserId(SecurityUtils.getUser().getId());
        baseYd.setDeleteFlag(0);
        baseYd.setState(0);

        return R.ok(this.save(baseYd));
    }

    @Override
    public R<?> page(Page page, BaseYdVo baseYdVo) {

        BaseYd baseYd = new BaseYd();
        BeanUtils.copyProperties(baseYdVo, baseYd);
        //最终返回的结果
        Page<BaseYdVo> baseYdVoPage = new Page<>();
        //查询到的印点配置的列表
        List<BaseYd> baseYdList=new ArrayList<>();
        //查询到的组织（印点）的列表
        List<SysOrg> orgList=new ArrayList<>();
        //查询分页的结果，用于设置分页数据
        IPage iPage=new Page();
        if(!StringUtils.isEmpty(baseYdVo.getYdName())||!StringUtils.isEmpty(baseYdVo.getYdSimpleName())){
            //如果名称和简称不为空，则判断联系人是否为空
            if(!StringUtils.isEmpty(baseYdVo.getLinkPerson())){
                //联系人不为空，首先查出所有联系人列表
                baseYdList=this.list(new LambdaQueryWrapper<BaseYd>()
                        .eq(BaseYd::getDeleteFlag,0)
                        .like(BaseYd::getLinkPerson,baseYdVo.getLinkPerson()));
                //根据印点配置列表查询印点（组织）
                List<Integer> orgIdList=new ArrayList<>();
                //收集组织id成列表，发送请求，返回组装好的印点视图列表
                baseYdList.forEach((yd)->{orgIdList.add(yd.getId());});
                R<List<SysOrg>> r=sysOrgClient.selectByIds(orgIdList);
                if(r.getCode()!=0){
                    log.info("获取印点列表失败，原因：{}",r.getMsg());
                    return R.failed("获取印点信息失败");
                }
                orgList=JSON.parseArray(JSON.toJSONString(r.getData()),SysOrg.class);
                //过滤名称和简称
                orgList=orgList.stream().filter((org)->{
                    boolean result=true;
                    if(!StringUtils.isEmpty(baseYdVo.getYdName())){
                        result=result&org.getOrgName().contains(baseYdVo.getYdName());
                    }
                    if(!StringUtils.isEmpty(baseYdVo.getYdSimpleName())){
                        result=result&&org.getAbbrName().contains(baseYdVo.getYdSimpleName());
                    }
                    return result;
                }).collect(Collectors.toList());
                //根据orglist设置分页
                List<SysOrg> tempOrgList=new ArrayList<>();
                if(orgList.size()>(page.getCurrent()-1)*page.getSize()){
                    int size=0;
                    if(orgList.size()<page.getCurrent()*page.getSize())
                        size=orgList.size();
                    else {
                        size= (int) (page.getCurrent()*page.getSize());
                    }
                    for (int i = (int) ((page.getCurrent()-1)*page.getSize()); i < size; i++) {
                        tempOrgList.add(orgList.get(i));
                    }
                }else if(page.getCurrent()==1){
                    tempOrgList=orgList;
                }
                orgList=tempOrgList;
                orgIdList.clear();
                orgList.forEach((org)->{orgIdList.add(org.getOrgId());});
                if(orgIdList.size()>0){
                    baseYdList=this.list(new LambdaQueryWrapper<BaseYd>()
                            .eq(BaseYd::getDeleteFlag,0)
                            .in(BaseYd::getId,orgIdList));
                }else {
                    baseYdList.clear();
                }
                //根据orgList包装成ipage
                iPage.setTotal(orgIdList.size());
                iPage.setCurrent(page.getCurrent());
                iPage.setSize(page.getSize());
            }
            else {
                //联系人为空的情况，则根据简称查出数据
                R<Page<SysOrg>> r=sysOrgClient.selectByName(baseYdVo.getYdName(),baseYdVo.getYdSimpleName(),2,(int)page.getCurrent(),(int )page.getSize());
                if(r.getCode()!=0){
                    log.info("获取印点列表失败，失败原因：{}",r.getMsg());
                    return R.failed("印点获取列表失败");
                }
                iPage=JSON.parseObject(JSON.toJSONString(r.getData()),Page.class);
                orgList=JSON.parseArray(JSON.toJSONString(iPage.getRecords()),SysOrg.class);
                //列出id
                List<Integer> orgIdList=new ArrayList<>();
                orgList.stream().forEach((org)->{orgIdList.add(org.getOrgId());});
                //收集印点
                if(orgIdList.size()>0){
                    baseYdList=this.list(new LambdaQueryWrapper<BaseYd>()
                            .eq(BaseYd::getDeleteFlag,0)
                            .in(BaseYd::getId,orgIdList));
                }
            }
        }
        else {
            //首先查出印点配置列表
            iPage = this.page(page, Wrappers.lambdaQuery(baseYd).eq(BaseYd::getDeleteFlag, 0).orderByAsc(BaseYd::getPriority));
            baseYdList= iPage.getRecords();
            //设置视图列表
            List<Integer> orgIdList=new ArrayList<>();
            //收集组织id成列表，发送请求，返回组装好的印点视图列表
            baseYdList.forEach((yd)->{orgIdList.add(yd.getId());});
            R<List<SysOrg>> r=sysOrgClient.selectByIds(orgIdList);
            if(r.getCode()!=0){
                log.info("获取印点列表失败，原因：{}",r.getMsg());
                return R.failed("获取印点信息失败");
            }
            orgList=JSON.parseArray(JSON.toJSONString(r.getData()),SysOrg.class);
        }
        //最终返回的列表数据
        List<BaseYdVo> ydVoList = new LinkedList<>();
        List<SysOrg> finalOrgList = orgList;
        baseYdList.forEach((yd)->{
            BaseYdVo ydVo=new BaseYdVo();
            BeanUtils.copyProperties(yd,ydVo);
            SysOrg org= finalOrgList.stream().filter((sysOrg -> {
                return sysOrg.getOrgId().intValue()==yd.getId();
            })).findAny().orElseGet(()->{
                return null;
            });
            //设置省市和大区
            if (yd.getAreaId() != null) {
                BaseArea baseArea = baseAreaService.getById(yd.getAreaId());
                if (baseArea != null) {
                    BaseArea parentArea = baseAreaService.getById(baseArea.getParentId());
                    if (parentArea.getAreaName().contains("中国")) {
                        //说明区域是省
                        ydVo.setProvince(baseArea.getAreaName());
                    } else {
                        ydVo.setProvince(parentArea.getAreaName());
                        ydVo.setCity(baseArea.getAreaName());
                    }
                }
            }
            if(org!=null){
                ydVo.setYdName(org.getOrgName());
                ydVo.setYdSimpleName(org.getAbbrName());
                ydVoList.add(ydVo);
            }
        });
        baseYdVoPage.setTotal(iPage.getTotal());
        baseYdVoPage.setCurrent(iPage.getCurrent());
        baseYdVoPage.setSize(iPage.getSize());
        baseYdVoPage.setRecords(ydVoList);

        return R.ok(baseYdVoPage);
    }

    @Override
    public R<?> update(BaseYdVo baseYdVo) {

        //保存印点信息
        BaseYd baseYd = new BaseYd();
        BeanUtils.copyProperties(baseYdVo, baseYd);

        return R.ok(this.updateById(baseYd));
    }

    @Override
    public R<?> getDetailById(Integer id) {

        QueryWrapper<BaseYd> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("delete_flag", 0)
                .eq("id", id);

        BaseYd baseYd = this.getOne(queryWrapper);
        if (baseYd == null) {
            return R.failed("印点配置不存在");
        }
        //设置视图对象
        BaseYdVo baseYdVo = new BaseYdVo();
        BeanUtils.copyProperties(baseYd, baseYdVo);
        //设置省市和大区
        if (baseYd.getAreaId() != null) {
            BaseArea baseArea = baseAreaService.getById(baseYd.getAreaId());
            if (baseArea != null) {
                BaseArea parentArea = baseAreaService.getById(baseArea.getParentId());
                if (parentArea.getAreaName().contains("中国")) {
                    //说明区域是省
                    baseYdVo.setProvince(baseArea.getAreaName());
                } else {
                    baseYdVo.setProvince(parentArea.getAreaName());
                    baseYdVo.setCity(baseArea.getAreaName());
                }
            }
        }
        if (baseYd.getBigareaId() != null) {
            BaseBigarea bigarea =baseBigareaService.getById(baseYd.getBigareaId());
            if(bigarea!=null){
                baseYdVo.setBigAreaName(bigarea.getBigareaName());
            }
        }
        //设置印点名称和印点简介
        R<?> r = sysOrgClient.getById(baseYd.getId());
        if (r.getCode() == 0) {
            SysOrg sysOrg = JSON.parseObject(JSON.toJSONString(r.getData()), SysOrg.class);
            baseYdVo.setYdName(sysOrg.getOrgName());
            baseYdVo.setYdSimpleName(sysOrg.getAbbrName());
        } else {
            return R.failed("印点不存在");
        }
        return R.ok(baseYdVo);
    }

    @Override
    public void updatePriority(Integer source, Integer target) {
        baseYdMapper.updatePriority(source, target);
    }


}
