
package com.taiji.erp.base.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taiji.erp.admin.api.entity.SysOrg;
import com.taiji.erp.admin.api.feign.SysOrgClient;
import com.taiji.erp.base.api.entity.BasePageCount;
import com.taiji.erp.base.api.vo.BasePageCountVo;
import com.taiji.erp.base.mapper.BasePageCountMapper;
import com.taiji.erp.base.service.BasePageCountService;
import com.taiji.erp.common.core.util.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 版数 设置
 *
 * @author yangcw
 * @date 2021-05-27 16:45:48
 */
@Service
@Slf4j
public class BasePageCountServiceImpl extends ServiceImpl<BasePageCountMapper, BasePageCount> implements BasePageCountService {

    @Autowired
    private BasePageCountMapper basePageCountMapper;

    @Autowired
    private SysOrgClient sysOrgClient;

    @Override
    public List<BasePageCountVo> page(Page page, BasePageCount count) {

        List<BasePageCountVo> countVoList=basePageCountMapper.aPage(page,count);
        //首先收集印点id
        List<Integer> ydIdList=new ArrayList<>();
        countVoList.stream().filter((vo)->{return vo.getYdId()!=null;}).forEach((vo)->{ydIdList.add(vo.getYdId());});
        //然后列出并收集印点，并设置印点名称
        R<List<SysOrg>> r=sysOrgClient.selectByIds(ydIdList);
        if(r.getCode()!=0){
            log.info("印点查询失败：{}",r.getMsg());
            return null;
        }
        List<SysOrg> orgList= JSON.parseArray(JSON.toJSONString(r.getData()),SysOrg.class);
        countVoList.stream().forEach((vo)->{
            SysOrg org=orgList.stream()
                    .filter((sysOrg -> {return sysOrg.getOrgId().intValue()==vo.getYdId();}))
                    .findFirst().orElseGet(()->{
                        return null;
                    });
            if(org!=null){
                vo.setYdName(org.getOrgName());
            }
        });

        return countVoList;
    }
}
