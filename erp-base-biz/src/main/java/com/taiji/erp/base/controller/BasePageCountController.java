

package com.taiji.erp.base.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taiji.erp.admin.api.entity.SysOrg;
import com.taiji.erp.admin.api.feign.SysOrgClient;
import com.taiji.erp.base.api.entity.BaseNewspaper;
import com.taiji.erp.base.api.vo.BasePageCountVo;
import com.taiji.erp.base.service.BaseNewspaperService;
import com.taiji.erp.common.core.util.R;
import com.taiji.erp.common.log.annotation.SysLog;
import com.taiji.erp.base.api.entity.BasePageCount;
import com.taiji.erp.base.service.BasePageCountService;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


/**
 * 版数 设置
 *
 * @author yangcw
 * @date 2021-05-27 16:45:48
 */
@RestController
@AllArgsConstructor
@RequestMapping("/basePageCount" )
@Api(value = "basePageCount", tags = "版数 设置管理")
public class BasePageCountController {

    private final  BasePageCountService basePageCountService;

    private final BaseNewspaperService baseNewspaperService;

    private final SysOrgClient sysOrgClient;

    /**
     * 分页查询
     * @param page 分页对象
     * @param basePageCount 版数 设置
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page" )
    public R getBasePageCountPage(Page page, BasePageCount basePageCount) {
        List<BasePageCountVo> list=basePageCountService.page(page,basePageCount);
        if(list==null){
            return R.failed("分页查询失败");
        }
        return R.ok(list);
    }


    /**
     * 通过id查询版数 设置
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/{id}" )
    public R getById(@PathVariable("id" ) Integer id) {

        BasePageCount basePageCount=basePageCountService.getById(id);
        BasePageCountVo countVo=new BasePageCountVo();
        if(basePageCount==null||basePageCount.getDeleteFlag()==1){
            return R.failed("版数设置不存在");
        }
        BaseNewspaper baseNewspaper=baseNewspaperService.getById(basePageCount.getNewspaperId());
        BeanUtils.copyProperties(basePageCount,countVo);
        //设置报社名和印点名
        countVo.setNewspaperName(baseNewspaper.getNewspaperName());
        if(Optional.ofNullable(basePageCount.getYdId()).orElse(0)!=0){
            R<?> r=sysOrgClient.getById(basePageCount.getYdId());
            if(r.getCode()==0){
                SysOrg sysOrg= JSON.parseObject(JSON.toJSONString(r.getData()),SysOrg.class);
                countVo.setYdName(sysOrg.getOrgName());
            }
        }
        return R.ok(countVo);
    }

    /**
     * 新增版数 设置
     * @param basePageCount 版数 设置
     * @return R
     */
    @ApiOperation(value = "新增版数 设置", notes = "新增版数 设置")
    @SysLog("新增版数 设置" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('base_basepagecount_add')" )
    public R save(@RequestBody BasePageCount basePageCount) {
        return R.ok(basePageCountService.save(basePageCount));
    }

    /**
     * 修改版数 设置
     * @param basePageCount 版数 设置
     * @return R
     */
    @ApiOperation(value = "修改版数 设置", notes = "修改版数 设置")
    @SysLog("修改版数 设置" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('base_basepagecount_edit')" )
    public R updateById(@RequestBody BasePageCount basePageCount) {
        return R.ok(basePageCountService.updateById(basePageCount));
    }

    /**
     * 通过id删除版数 设置
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id删除版数 设置", notes = "通过id删除版数 设置")
    @SysLog("通过id删除版数 设置" )
    @DeleteMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('base_basepagecount_del')" )
    public R removeById(@PathVariable Integer id) {

        BasePageCount count=basePageCountService.getById(id);
        if(count==null||count.getDeleteFlag()==1){
            return R.failed("版数设置不存在");
        }
        count.setDeleteFlag(1);
        return R.ok(basePageCountService.updateById(count));
    }

}
