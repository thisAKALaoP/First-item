

package com.taiji.erp.base.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taiji.erp.base.api.vo.BaseYdVendorVo;
import com.taiji.erp.common.core.util.R;
import com.taiji.erp.common.log.annotation.SysLog;
import com.taiji.erp.base.api.entity.BaseYdVendor;
import com.taiji.erp.base.service.BaseYdVendorService;
import com.taiji.erp.common.security.util.SecurityUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;


/**
 * 印点 供应商配置 表
 *
 * @author yangcw
 * @date 2021-05-22 16:09:13
 */
@RestController
@AllArgsConstructor
@RequestMapping("/baseYdVendor" )
@Api(value = "baseYdVendor", tags = "印点 供应商配置 表管理")
public class BaseYdVendorController {

    private final  BaseYdVendorService baseYdVendorService;

    /**
     * 分页查询
     * @param page 分页对象
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page" )
    public R getBaseYdVendorPage(Page page, BaseYdVendorVo baseYdVendorVo){

        return baseYdVendorService.page(page,baseYdVendorVo);
    }


    /**
     * 通过id查询印点 供应商配置 表
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/{id}" )
    public R getById(@PathVariable("id" ) Integer id) {
        return R.ok(baseYdVendorService.getById(id));
    }

    /**
     * 新增印点 供应商配置 表
     * @param baseYdVendor 印点 供应商配置 表
     * @return R
     */
    @ApiOperation(value = "新增印点 供应商配置 表", notes = "新增印点 供应商配置 表")
    @SysLog("新增印点 供应商配置 表" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('base_baseydvendor_add')" )
    public R save(@RequestBody BaseYdVendor baseYdVendor) {
        baseYdVendor.setDeleteFlag(0);
        baseYdVendor.setCreateDate(LocalDateTime.now());
        baseYdVendor.setCreateUserId(SecurityUtils.getUser().getId());
        return R.ok(baseYdVendorService.save(baseYdVendor));
    }

    /**
     * 修改印点 供应商配置 表
     * @param baseYdVendor 印点 供应商配置 表
     * @return R
     */
    @ApiOperation(value = "修改印点 供应商配置 表", notes = "修改印点 供应商配置 表")
    @SysLog("修改印点 供应商配置 表" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('base_baseydvendor_edit')" )
    public R updateById(@RequestBody BaseYdVendor baseYdVendor) {
        return R.ok(baseYdVendorService.updateById(baseYdVendor));
    }

    /**
     * 通过id删除印点 供应商配置 表
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id删除印点 供应商配置 表", notes = "通过id删除印点 供应商配置 表")
    @SysLog("通过id删除印点 供应商配置 表" )
    @DeleteMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('base_baseydvendor_del')" )
    public R removeById(@PathVariable Integer id) {

        BaseYdVendor baseYdVendor=baseYdVendorService.getById(id);
        if(baseYdVendor==null){
            return R.failed("供应商配置表不存在");
        }
        baseYdVendor.setDeleteFlag(1);
        return R.ok(baseYdVendorService.updateById(baseYdVendor));
    }

}
