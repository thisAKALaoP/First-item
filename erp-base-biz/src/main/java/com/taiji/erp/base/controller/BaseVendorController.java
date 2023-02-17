

package com.taiji.erp.base.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taiji.erp.base.api.vo.BaseVendorVo;
import com.taiji.erp.common.core.util.R;
import com.taiji.erp.common.log.annotation.SysLog;
import com.taiji.erp.base.api.entity.BaseVendor;
import com.taiji.erp.base.service.BaseVendorService;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


/**
 * 供应商配置表
 *
 * @author yangcw
 * @date 2021-05-22 16:09:12
 */
@RestController
@AllArgsConstructor
@RequestMapping("/baseVendor" )
@Api(value = "baseVendor", tags = "供应商配置表管理")
public class BaseVendorController {

    private final  BaseVendorService baseVendorService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param vendorVo 供应商配置表
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page" )
    public R getBaseVendorPage(Page page, BaseVendorVo vendorVo) {
        return baseVendorService.page(page,vendorVo);
    }


    /**
     * 通过id查询供应商配置表
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/{id}" )
    public R getById(@PathVariable("id" ) Integer id) {
        return baseVendorService.getDetailById(id);
    }

    /**
     * 新增供应商配置表
     * @return R
     */
    @ApiOperation(value = "新增供应商配置表", notes = "新增供应商配置表")
    @SysLog("新增供应商配置表" )
    @PostMapping
//    @PreAuthorize("@pms.hasPermission('base_basevendor_add')" )
    public R save(@RequestBody BaseVendorVo baseVendorVo) {
        return baseVendorService.save(baseVendorVo);
    }

    /**
     * 修改供应商配置表
     * @param baseVendorVo 供应商配置表
     * @return R
     */
    @ApiOperation(value = "修改供应商配置表", notes = "修改供应商配置表")
    @SysLog("修改供应商配置表" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('base_basevendor_edit')" )
    public R updateById(@RequestBody BaseVendorVo baseVendorVo) {
        return baseVendorService.update(baseVendorVo);
    }

    /**
     * 通过id删除供应商配置表
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id删除供应商配置表", notes = "通过id删除供应商配置表")
    @SysLog("通过id删除供应商配置表" )
    @DeleteMapping("/{id}" )
//    @PreAuthorize("@pms.hasPermission('base_basevendor_del')" )
    public R removeById(@PathVariable Integer id) {

        BaseVendor baseVendor=baseVendorService.getOne(new LambdaQueryWrapper<BaseVendor>()
                .eq(BaseVendor::getDeleteFlag,0).eq(BaseVendor::getId,id));
        if(baseVendor==null){
            return R.failed("供应商不存在");
        }
        baseVendor.setDeleteFlag(1);
        baseVendorService.updatePriority(baseVendor.getPriority(),null);
        return R.ok(baseVendorService.updateById(baseVendor));
    }

}
