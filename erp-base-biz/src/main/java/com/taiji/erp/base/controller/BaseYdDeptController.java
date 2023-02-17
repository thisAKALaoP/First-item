

package com.taiji.erp.base.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taiji.erp.base.api.entity.BaseYd;
import com.taiji.erp.common.core.util.R;
import com.taiji.erp.common.log.annotation.SysLog;
import com.taiji.erp.base.api.entity.BaseYdDept;
import com.taiji.erp.base.service.BaseYdDeptService;
import com.taiji.erp.common.security.util.SecurityUtils;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;


/**
 * 印点 部门配置表
 *
 * @author yangcw
 * @date 2021-05-22 16:09:13
 */
@RestController
@AllArgsConstructor
@RequestMapping("/baseyddept" )
@Api(value = "baseyddept", tags = "印点 部门配置表管理")
public class BaseYdDeptController {

    private final  BaseYdDeptService baseYdDeptService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param baseYdDept 印点 部门配置表
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page" )
    public R getBaseYdDeptPage(Page page, BaseYdDept baseYdDept) {
        return R.ok(baseYdDeptService.page(page, Wrappers.query(baseYdDept).lambda().eq(BaseYdDept::getDeleteFlag,0)));
    }

    @ApiModelProperty(value = "列表",notes = "列表查询")
    @GetMapping(value = "/list")
    public R<?> list(BaseYdDept baseYdDept){
        return R.ok(baseYdDeptService.list(Wrappers.query(baseYdDept).lambda().eq(BaseYdDept::getDeleteFlag,0)));
    }

    /**
     * 通过id查询印点 部门配置表
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/{id}" )
    public R getById(@PathVariable("id" ) Integer id) {
        return R.ok(baseYdDeptService.getById(id));
    }

    /**
     * 新增印点 部门配置表
     * @param baseYdDept 印点 部门配置表
     * @return R
     */
    @ApiOperation(value = "新增印点 部门配置表", notes = "新增印点 部门配置表")
    @SysLog("新增印点 部门配置表" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('base_baseyddept_add')" )
    public R save(@RequestBody BaseYdDept baseYdDept) {
        baseYdDept.setDeleteFlag(0);
        baseYdDept.setCreateDate(LocalDateTime.now());
        baseYdDept.setCreateUserId(SecurityUtils.getUser().getId());
        return R.ok(baseYdDeptService.save(baseYdDept));
    }

    /**
     * 修改印点 部门配置表
     * @param baseYdDept 印点 部门配置表
     * @return R
     */
    @ApiOperation(value = "修改印点 部门配置表", notes = "修改印点 部门配置表")
    @SysLog("修改印点 部门配置表" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('base_baseyddept_edit')" )
    public R updateById(@RequestBody BaseYdDept baseYdDept) {
        return R.ok(baseYdDeptService.updateById(baseYdDept));
    }

    /**
     * 通过id删除印点 部门配置表
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id删除印点 部门配置表", notes = "通过id删除印点 部门配置表")
    @SysLog("通过id删除印点 部门配置表" )
    @DeleteMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('base_baseyddept_del')" )
    public R removeById(@PathVariable Integer id) {
        //删除改为逻辑删除
        BaseYdDept baseYdDept=baseYdDeptService.getById(id);
        if(baseYdDept==null||baseYdDept.getDeleteFlag()==1){
            return R.failed("印点部门不存在");
        }
        baseYdDept.setDeleteFlag(1);
        return R.ok(baseYdDeptService.updateById(baseYdDept));
    }

}
