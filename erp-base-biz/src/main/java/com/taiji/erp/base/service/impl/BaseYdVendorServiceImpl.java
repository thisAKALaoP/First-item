
package com.taiji.erp.base.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taiji.erp.admin.api.entity.SysOrg;
import com.taiji.erp.admin.api.feign.SysOrgClient;
import com.taiji.erp.base.api.entity.BaseYdVendor;
import com.taiji.erp.base.api.vo.BaseVendorVo;
import com.taiji.erp.base.api.vo.BaseYdVendorVo;
import com.taiji.erp.base.mapper.BaseYdVendorMapper;
import com.taiji.erp.base.service.BaseYdVendorService;
import com.taiji.erp.common.core.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 印点 供应商配置 表
 *
 * @author yangcw
 * @date 2021-05-22 16:09:13
 */
@Service
public class BaseYdVendorServiceImpl extends ServiceImpl<BaseYdVendorMapper, BaseYdVendor> implements BaseYdVendorService {

    @Autowired
    private BaseYdVendorMapper baseYdVendorMapper;
    @Autowired
    private SysOrgClient sysOrgClient;

    @Override
    public R<?> page(Page page, BaseYdVendorVo baseYdVendorVo) {

        //查找供应商名称
        List<BaseYdVendorVo> vendorVoList = baseYdVendorMapper.pageBaseYdVendorVo(page, baseYdVendorVo);
        List<Integer> orgIdList = vendorVoList.stream().map(BaseYdVendorVo::getVendorId).collect(Collectors.toList());
        R<List<SysOrg>> r = sysOrgClient.selectByIds(orgIdList);
        if (r.getCode() != 0) {
            return R.failed("获取供应商列表失败");
        }
        List<SysOrg> orgList = JSON.parseArray(JSON.toJSONString(r.getData()), SysOrg.class);
        vendorVoList.stream().filter((vo) -> {
            //仅当供应商不为空时才查询
            return vo.getVendorId() != null;
        }).forEach((vo) -> {
            //获取id匹配的第一个供应商
            SysOrg sysOrg = orgList.stream().filter((org) -> {
                return org.getOrgId().intValue() == vo.getVendorId();
            }).findAny().orElseGet(() -> {
                SysOrg org=new SysOrg();
                org.setOrgName("");
                return org;
            });
            //设置供应商名称
            vo.setVendorName(sysOrg.getOrgName());}
        );

        LambdaQueryWrapper<BaseYdVendor> queryWrapper=new LambdaQueryWrapper<BaseYdVendor>()
                .eq(BaseYdVendor::getDeleteFlag,0);
        if(baseYdVendorVo.getYdId()!=null){
            queryWrapper.eq(BaseYdVendor::getYdId,baseYdVendorVo.getYdId());
        }
        Page<BaseYdVendorVo> resultPage=new Page<>();
        resultPage.setCurrent(page.getCurrent());
        resultPage.setSize(page.getSize());
        resultPage.setRecords(vendorVoList);
        resultPage.setTotal(this.count(queryWrapper));
        return R.ok(resultPage);
    }
}
