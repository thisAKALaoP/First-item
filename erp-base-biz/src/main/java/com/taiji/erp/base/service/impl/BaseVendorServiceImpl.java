
package com.taiji.erp.base.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taiji.erp.admin.api.entity.SysOrg;
import com.taiji.erp.admin.api.feign.SysOrgClient;
import com.taiji.erp.base.api.entity.BaseArea;
import com.taiji.erp.base.api.entity.BaseVendor;
import com.taiji.erp.base.api.entity.BaseYd;
import com.taiji.erp.base.api.vo.BaseVendorVo;
import com.taiji.erp.base.mapper.BaseVendorMapper;
import com.taiji.erp.base.service.BaseVendorService;
import com.taiji.erp.common.core.util.R;
import com.taiji.erp.common.security.util.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 供应商配置表
 *
 * @author yangcw
 * @date 2021-05-22 16:09:12
 */
@Service
@Slf4j
public class BaseVendorServiceImpl extends ServiceImpl<BaseVendorMapper, BaseVendor> implements BaseVendorService {

    @Autowired
    private BaseVendorMapper baseVendorMapper;
    @Autowired
    private SysOrgClient sysOrgClient;

    @Override
    public R<?> page(Page page, BaseVendorVo baseVendorVo) {

        BaseVendor baseVendor=new BaseVendor();
        BeanUtils.copyProperties(baseVendorVo,baseVendor);
        IPage iPage=new Page();
        //供应商列表
        List<BaseVendor> baseVendorList=new ArrayList<>();
        //组织（供应商）id列表
        List<Integer> orgIdList=new ArrayList<>();
        //供应商列表
        List<SysOrg> orgList=new ArrayList<>();
        if(StringUtils.isEmpty(baseVendorVo.getVendorName())&&StringUtils.isEmpty(baseVendorVo.getVendorSimpleName())){
            //如果简称和名称都为空
            iPage=this.page(page, Wrappers.lambdaQuery(baseVendor).eq(BaseVendor::getDeleteFlag,0).orderByAsc(BaseVendor::getPriority));
            baseVendorList=iPage.getRecords();
            baseVendorList.forEach((yd)->{orgIdList.add(yd.getId());});
            R<List<SysOrg>> r=sysOrgClient.selectByIds(orgIdList);
            if(r.getCode()!=0){
                return R.failed("获取印点信息失败");
            }
            orgList=JSON.parseArray(JSON.toJSONString(r.getData()),SysOrg.class);
        }
        else {
            //名称和简称不为空的情况，则判断联系人是否存在
            if(StringUtils.isEmpty(baseVendorVo.getLinkPerson())){
                //如果联系人为空
                R<Page<SysOrg>> r=sysOrgClient.selectByName(baseVendorVo.getVendorName(),baseVendorVo.getVendorSimpleName(),3,(int)page.getCurrent(),(int )page.getSize());
                if(r.getCode()!=0){
                    log.info("获取印点列表失败，失败原因：{}",r.getMsg());
                    return R.failed("印点获取列表失败");
                }
                iPage=JSON.parseObject(JSON.toJSONString(r.getData()),Page.class);
                orgList=JSON.parseArray(JSON.toJSONString(iPage.getRecords()),SysOrg.class);
                //设置供应商列表
                orgList.stream().forEach((org)->{orgIdList.add(org.getOrgId());});
                if(orgIdList.size()>0){
                    baseVendorList=this.list(new LambdaQueryWrapper<BaseVendor>()
                            .eq(BaseVendor::getDeleteFlag,0)
                            .in(BaseVendor::getId,orgIdList));
                }
            }
            else {
                //如果联系人不为空，则先查出联系人列表
                baseVendorList=this.list(new LambdaQueryWrapper<BaseVendor>()
                        .eq(BaseVendor::getDeleteFlag,0)
                        .eq(BaseVendor::getLinkPerson,baseVendorVo.getLinkPerson()));
                baseVendorList.forEach((vendor)->{orgIdList.add(vendor.getId());});
                R<List<SysOrg>> r=sysOrgClient.selectByIds(orgIdList);
                if(r.getCode()!=0){
                    log.info("获取印点列表失败，原因：{}",r.getMsg());
                    return R.failed("获取印点信息失败");
                }
                orgList=JSON.parseArray(JSON.toJSONString(r.getData()),SysOrg.class);
                //然后根据名称和简称过滤
                orgList=orgList.stream().filter((org)->{
                    boolean result=true;
                    if(!StringUtils.isEmpty(baseVendorVo.getVendorName())){
                        result=result&org.getOrgName().contains(baseVendorVo.getVendorName());
                    }
                    if(!StringUtils.isEmpty(baseVendorVo.getVendorSimpleName())){
                        result=result&&org.getAbbrName().contains(baseVendorVo.getVendorSimpleName());
                    }
                    return result;
                }).collect(Collectors.toList());
                //然后重新设置分页，包装成page
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
                }
                orgList=tempOrgList;
                orgIdList.clear();
                orgList.forEach((org)->{orgIdList.add(org.getOrgId());});
                if(orgIdList.size()>0){
                    baseVendorList=this.list(new LambdaQueryWrapper<BaseVendor>()
                            .eq(BaseVendor::getDeleteFlag,0)
                            .in(BaseVendor::getId,orgIdList));
                }else {
                    baseVendorList.clear();
                }
                //根据orgList包装成ipage
                iPage.setTotal(orgIdList.size());
                iPage.setCurrent(page.getCurrent());
                iPage.setSize(page.getSize());
            }
        }
        //设置供应商名称和供应商简称
        List<BaseVendorVo> vendorVoList=new ArrayList<>();

        List<SysOrg> finalOrgList = orgList;
        baseVendorList.forEach((vendor)->{
            BaseVendorVo vendorVo=new BaseVendorVo();
            BeanUtils.copyProperties(vendor,vendorVo);
            SysOrg org= finalOrgList.stream().filter((sysOrg -> {
                return sysOrg.getOrgId().intValue()==vendor.getId();
            })).findAny().orElseGet(()->{
                return null;
            });
            //设置省市和大区
            if(org!=null){
                vendorVo.setVendorName(org.getOrgName());
                vendorVo.setVendorSimpleName(org.getAbbrName());
                vendorVoList.add(vendorVo);
            }
        });
        //重新包装成page
        Page<BaseVendorVo> baseVendorPage = new Page<>();
        baseVendorPage.setTotal(iPage.getTotal());
        baseVendorPage.setCurrent(iPage.getCurrent());
        baseVendorPage.setSize(iPage.getSize());
        baseVendorPage.setRecords(vendorVoList);

        return R.ok(baseVendorPage);
    }

    @Override
    public R<?> save(BaseVendorVo baseVendorVo) {

        BaseVendor baseVendor=new BaseVendor();
        BeanUtils.copyProperties(baseVendorVo,baseVendor);
        //设置优先级
        if(Optional.ofNullable(baseVendorVo.getPriority()).orElse(0)==0){
            baseVendor.setPriority(this.count(new LambdaQueryWrapper<BaseVendor>().eq(BaseVendor::getDeleteFlag,0))+1);
        }else {
            this.updatePriority(null,baseVendorVo.getPriority());
        }
        baseVendor.setCreateDate(LocalDateTime.now());
        baseVendor.setCreateUserId(SecurityUtils.getUser().getId());
        baseVendor.setDeleteFlag(0);

        return R.ok(this.save(baseVendor));
    }

    @Override
    public R<?> update(BaseVendorVo baseVendorVo) {
        BaseVendor baseVendor=new BaseVendor();
        BeanUtils.copyProperties(baseVendorVo,baseVendor);

        return R.ok(this.updateById(baseVendor));
    }

    @Override
    public void updatePriority(Integer source, Integer target) {
        baseVendorMapper.updatePriority(source, target);
    }

    @Override
    public R<?> getDetailById(Integer id) {

        BaseVendor baseVendor=this.getOne(new LambdaQueryWrapper<BaseVendor>()
                .eq(BaseVendor::getId,id)
                .eq(BaseVendor::getDeleteFlag,0));
        if(baseVendor==null){
            return R.failed("供应商配置不存在");
        }

        BaseVendorVo baseVendorVo=new BaseVendorVo();
        BeanUtils.copyProperties(baseVendor,baseVendorVo);
        //设置供应商名称和简称
        R<SysOrg> r=sysOrgClient.getById(id);
        if(r.getCode()==1){
            return R.failed("供应商不存在");
        }
        SysOrg sysOrg= JSON.parseObject(JSON.toJSONString(r.getData()),SysOrg.class);
        baseVendorVo.setVendorName(sysOrg.getOrgCode());
        baseVendorVo.setVendorSimpleName(sysOrg.getAbbrName());

        return R.ok(baseVendorVo);
    }
}
