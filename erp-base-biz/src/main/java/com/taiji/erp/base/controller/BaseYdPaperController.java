

package com.taiji.erp.base.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taiji.erp.base.api.entity.BasePaper;
import com.taiji.erp.base.api.entity.BaseYd;
import com.taiji.erp.base.api.vo.BaseYdPaperVo;
import com.taiji.erp.common.core.util.R;
import com.taiji.erp.common.log.annotation.SysLog;
import com.taiji.erp.base.api.entity.BaseYdPaper;
import com.taiji.erp.base.service.BaseYdPaperService;
import com.taiji.erp.common.security.util.SecurityUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;


/**
 * 印点 纸张 配置表
 *
 * @author yangcw
 * @date 2021-05-22 16:09:13
 */
@RestController
@AllArgsConstructor
@RequestMapping("/baseYdPaper" )
@Api(value = "baseYdPaper", tags = "印点 纸张 配置表管理")
public class BaseYdPaperController {

    private final  BaseYdPaperService baseYdPaperService;

    /**
     * 分页查询
     * @param page 分页对象
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page" )
    public R getBaseYdPaperPage(Page page, BaseYdPaperVo baseYdPaperVo) {
        return baseYdPaperService.page(page,baseYdPaperVo);
    }


    /**
     * 通过id查询印点 纸张 配置表
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/{id}" )
    public R getById(@PathVariable("id" ) Integer id) {
        BaseYdPaper baseYdPaper=baseYdPaperService.getById(id);
        if(baseYdPaper==null||baseYdPaper.getDeleteFlag()==1){
            return R.failed("纸张配置不存在");
        }

        return R.ok(baseYdPaper);
    }

    /**
     * 新增印点 纸张 配置表
     * @param baseYdPaper 印点 纸张 配置表
     * @return R
     */
    @ApiOperation(value = "新增印点 纸张 配置表", notes = "新增印点 纸张 配置表")
    @SysLog("新增印点 纸张 配置表" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('base_baseydpaper_add')" )
    public R save(@RequestBody BaseYdPaper baseYdPaper) {
        baseYdPaper.setDeleteFlag(0);
        baseYdPaper.setCreateDate(LocalDateTime.now());
        baseYdPaper.setCreateUserId(SecurityUtils.getUser().getId());
        return R.ok(baseYdPaperService.save(baseYdPaper));
    }

    /**
     * 修改印点 纸张 配置表
     * @param baseYdPaper 印点 纸张 配置表
     * @return R
     */
    @ApiOperation(value = "修改印点 纸张 配置表", notes = "修改印点 纸张 配置表")
    @SysLog("修改印点 纸张 配置表" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('base_baseydpaper_edit')" )
    public R updateById(@RequestBody BaseYdPaper baseYdPaper) {
        return R.ok(baseYdPaperService.updateById(baseYdPaper));
    }

    /**
     * 通过id删除印点 纸张 配置表
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id删除印点 纸张 配置表", notes = "通过id删除印点 纸张 配置表")
    @SysLog("通过id删除印点 纸张 配置表" )
    @DeleteMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('base_baseydpaper_del')" )
    public R removeById(@PathVariable Integer id) {

        BaseYdPaper baseYdPaper=baseYdPaperService.getById(id);
        if(baseYdPaper==null){
            return R.failed("纸张配置表不存在");
        }
        baseYdPaper.setDeleteFlag(1);
        return R.ok(baseYdPaperService.updateById(baseYdPaper));
    }

}
