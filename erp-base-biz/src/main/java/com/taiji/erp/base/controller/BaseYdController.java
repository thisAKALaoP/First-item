

package com.taiji.erp.base.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taiji.erp.base.api.entity.BaseYdVendor;
import com.taiji.erp.base.api.vo.BaseYdVo;
import com.taiji.erp.common.core.util.R;
import com.taiji.erp.common.log.annotation.SysLog;
import com.taiji.erp.base.api.entity.BaseYd;
import com.taiji.erp.base.service.BaseYdService;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


/**
 * 印点 基本信息 配置表
 *
 * @author yangcw
 * @date 2021-05-22 16:09:13
 */
@RestController
@AllArgsConstructor
@RequestMapping("/baseYd" )
@Api(value = "baseYd", tags = "印点 基本信息 配置表管理")
public class BaseYdController {

    private final  BaseYdService baseYdService;

    /**
     * 分页查询
     * @param page 分页对象
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page" )
    public R getBaseYdPage(Page page, BaseYdVo baseYdVo) {
        return baseYdService.page(page,baseYdVo);
    }


    /**
     * 通过id查询印点 基本信息 配置表
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/{id}" )
    public R getById(@PathVariable("id" ) Integer id) {
        return baseYdService.getDetailById(id);
    }

    /**
     * 新增印点 基本信息 配置表
     * @return R
     */
    @ApiOperation(value = "新增印点 基本信息 配置表", notes = "新增印点 基本信息 配置表")
    @SysLog("新增印点 基本信息 配置表" )
    @PostMapping
//    @PreAuthorize("@pms.hasPermission('base_baseyd_add')" )
    public R save(@RequestBody BaseYdVo baseYdVo) {
        return baseYdService.save(baseYdVo);
    }

    /**
     * 修改印点 基本信息 配置表
     * @param baseYdVo 印点 基本信息 配置表
     * @return R
     */
    @ApiOperation(value = "修改印点 基本信息 配置表", notes = "修改印点 基本信息 配置表")
    @SysLog("修改印点 基本信息 配置表" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('base_baseyd_edit')" )
    public R updateById(@RequestBody BaseYdVo baseYdVo) {
        return baseYdService.update(baseYdVo);
    }

    /**
     * 通过id删除印点 基本信息 配置表
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id删除印点 基本信息 配置表", notes = "通过id删除印点 基本信息 配置表")
    @SysLog("通过id删除印点 基本信息 配置表" )
    @DeleteMapping("/{id}" )
//    @PreAuthorize("@pms.hasPermission('base_baseyd_del')" )
    public R removeById(@PathVariable Integer id) {


        QueryWrapper<BaseYd> queryWrapper=new QueryWrapper<BaseYd>().eq("delete_flag",0)
                .eq("id",id);
        BaseYd baseYd=baseYdService.getOne(queryWrapper);
        if(baseYd!=null){
            baseYdService.updatePriority(baseYd.getPriority(),null);
            baseYd.setDeleteFlag(1);
            return R.ok(baseYdService.updateById(baseYd));
        }
        return R.failed("印点不存在");
    }

}
