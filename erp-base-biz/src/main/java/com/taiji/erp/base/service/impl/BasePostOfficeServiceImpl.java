
package com.taiji.erp.base.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taiji.erp.admin.api.entity.SysDictItem;
import com.taiji.erp.admin.api.entity.SysOrg;
import com.taiji.erp.admin.api.feign.SysDictClient;
import com.taiji.erp.admin.api.feign.SysOrgClient;
import com.taiji.erp.base.api.entity.BaseArea;
import com.taiji.erp.base.api.entity.BasePostOffice;
import com.taiji.erp.base.api.entity.BasePostOfficeAreaLink;
import com.taiji.erp.base.api.vo.BasePostOfficeVo;
import com.taiji.erp.base.mapper.BasePostOfficeMapper;
import com.taiji.erp.base.service.BaseAreaService;
import com.taiji.erp.base.service.BasePostOfficeAreaLinkService;
import com.taiji.erp.base.service.BasePostOfficeService;
import com.taiji.erp.common.core.constant.SecurityConstants;
import com.taiji.erp.common.core.util.R;
import com.taiji.erp.common.security.util.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * 报刊发行局 
 *
 * @author yangcw
 * @date 2021-05-13 16:31:57
 */
@Service
@Slf4j
public class BasePostOfficeServiceImpl extends ServiceImpl<BasePostOfficeMapper, BasePostOffice> implements BasePostOfficeService {

    @Autowired
    private BaseAreaService baseAreaService;
    @Autowired
    private BasePostOfficeAreaLinkService basePostOfficeAreaLinkService;
    @Autowired
    private BasePostOfficeMapper basePostOfficeMapper;
    @Autowired
    private SysOrgClient sysOrgClient;
    @Autowired
    private SysDictClient sysDictClient;
    @Value("${erp-base.baseCentre.postOfficeType}")
    private String postOfficeType;

    @Override
    public R<?> getDetailById(Integer id) {

        BasePostOfficeVo officeVo=new BasePostOfficeVo();
        //查找发行局
        QueryWrapper<BasePostOffice> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("id",id)
                .eq("delete_flag",0);
        BasePostOffice office=this.getOne(queryWrapper);
        if(office==null){
            return R.failed("发行局不存在");
        }
        //bean转换
        BeanUtils.copyProperties(office,officeVo);
        //设置印点名和报社名
        if(Optional.ofNullable(officeVo.getLocationAreaId()).orElse(0)!=0){
            BaseArea baseArea=baseAreaService.getById(officeVo.getLocationAreaId());
            if(baseArea!=null){
                officeVo.setLocationName(baseArea.getAreaName());
            }
        }
        if(Optional.ofNullable(officeVo.getOrgId()).orElse(0)!=0){
            R<?> r=sysOrgClient.getById(officeVo.getOrgId());
            if(r.getCode()==0){
                SysOrg sysOrg= JSON.parseObject(JSON.toJSONString(r.getData()),SysOrg.class);
                if(sysOrg!=null){
                    officeVo.setOrgName(sysOrg.getOrgName());
                }
            }
        }

        return R.ok(officeVo);

    }

    @Override
    @Transactional
    public R<?> save(BasePostOfficeVo basePostOfficeVo) {

        //首先判断印点是否被绑定
        if(basePostOfficeVo.getOrgId()!=null){
            if(this.count(new LambdaQueryWrapper<BasePostOffice>().eq(BasePostOffice::getDeleteFlag,0).eq(BasePostOffice::getOrgId,basePostOfficeVo))>0){
                return R.failed("该印点已被绑定");
            }
        }
        BasePostOffice basePostOffice=new BasePostOffice();
        BeanUtils.copyProperties(basePostOfficeVo,basePostOffice);
        //设置优先级
        if(Optional.ofNullable(basePostOfficeVo.getPriority()).orElse(0)==0){
            basePostOffice.setPriority(this.count(new LambdaQueryWrapper<BasePostOffice>().eq(BasePostOffice::getDeleteFlag,0))+1);
        }else {
            this.updatePriority(null,basePostOffice.getPriority());
        }
        basePostOffice.setCreateDate(LocalDateTime.now());
        basePostOffice.setCreateUserId(SecurityUtils.getUser().getId());
        basePostOffice.setDeleteFlag(0);
        this.save(basePostOffice);
        if(basePostOfficeVo.getAreaList()!=null&&basePostOfficeVo.getAreaList().size()>0){
            //新建地区关联
            basePostOfficeVo.getAreaList().forEach(areaId->{
                BasePostOfficeAreaLink officeAreaLink=new BasePostOfficeAreaLink();
                officeAreaLink.setPostOfficeId(basePostOffice.getId());
                officeAreaLink.setAreaId(areaId);
                basePostOfficeAreaLinkService.save(officeAreaLink);
            });
        }
        return R.ok(true);
    }

    @Override
    public R<?> update(BasePostOfficeVo basePostOfficeVo) {

        BasePostOffice basePostOffice=new BasePostOffice();
        BeanUtils.copyProperties(basePostOfficeVo,basePostOffice);
        //设置优先级
        QueryWrapper<BasePostOffice> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("id",basePostOfficeVo.getId())
                .eq("delete_flag",0);
        BasePostOffice office=this.getOne(queryWrapper);
        if(office==null){
            return R.failed("发行局不存在");
        }
        basePostOffice.setUpdateDate(LocalDateTime.now());
        basePostOffice.setUpdateUserId(SecurityUtils.getUser().getId());

        return R.ok(this.updateById(basePostOffice));
    }

    @Override
    public R<?> page(Page page, BasePostOfficeVo basePostOfficeVo) {

        BasePostOffice office=new BasePostOffice();
        BeanUtils.copyProperties(basePostOfficeVo,office);
        office.setDeleteFlag(0);

        //获取列表数据
        IPage iPage=this.page(page,Wrappers.query(office).orderByAsc("priority"));
        List<BasePostOffice> officeList=iPage.getRecords();
        List<BasePostOfficeVo> officeVoList=new ArrayList<>();
        //设置分页数据
        R<List<SysDictItem>> r= sysDictClient.getDictByType(postOfficeType);
        List<SysDictItem> itemList=JSON.parseArray(JSON.toJSONString(r.getData()),SysDictItem.class);

        officeList.stream().forEach(basePostOffice->{
            BasePostOfficeVo officeVo=new BasePostOfficeVo();
            BeanUtils.copyProperties(basePostOffice,officeVo);
            if(!StringUtils.isEmpty(officeVo.getPostOfficeType())){
                //对字典项进行匹配
                officeVo.setPostOfficeTypeName(itemList.parallelStream().filter(
                        item->{
                            return officeVo.getPostOfficeType().equals(item.getValue());
                        }
                ).findFirst().orElseGet(()->{
                    SysDictItem item=new SysDictItem();
                    item.setLabel("");
                    return item;
                }).getLabel());
            }
            officeVoList.add(officeVo);
        });

//        officeVoList.sort(((o1, o2) -> {return Integer.compare(o1.getPriority(), o2.getPriority());}));
        iPage.setRecords(officeVoList);
        return R.ok(iPage);
    }

    @Override
    public void updatePriority(Integer source,Integer target){
        basePostOfficeMapper.updatePriority(source,target);
    }

}
