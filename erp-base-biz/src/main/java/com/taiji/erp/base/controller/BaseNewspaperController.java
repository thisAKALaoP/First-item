

package com.taiji.erp.base.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taiji.erp.base.api.vo.BaseNewspaperVo;
import com.taiji.erp.common.core.util.R;
import com.taiji.erp.common.log.annotation.SysLog;
import com.taiji.erp.base.api.entity.BaseNewspaper;
import com.taiji.erp.base.service.BaseNewspaperService;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


/**
 * 报刊 配置 表
 *
 * @author yangcw
 * @date 2021-05-18 10:13:07
 */
@RestController
@AllArgsConstructor
@RequestMapping("/baseNewspaper" )
@Api(value = "baseNewspaper", tags = "报刊 配置 表管理")
public class BaseNewspaperController {

    private final  BaseNewspaperService baseNewspaperService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param baseNewspaperVo 报刊 配置 表
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page" )
    public R getBaseNewspaperPage(Page page, BaseNewspaperVo baseNewspaperVo) {

        return baseNewspaperService.list(page,baseNewspaperVo);

    }

    @ApiModelProperty(value = "列表查询",notes = "列表查询")
    @GetMapping(value = "/list")
    public R<?> list(BaseNewspaperVo baseNewspaperVo){

        BaseNewspaper baseNewspaper=new BaseNewspaper();
        BeanUtils.copyProperties(baseNewspaperVo,baseNewspaper);

        return R.ok(baseNewspaperService.list(Wrappers.query(baseNewspaper)));
    }


    /**
     * 通过id查询报刊 配置 表
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/{id}" )
    public R getById(@PathVariable("id" ) Integer id) {
        return R.ok(baseNewspaperService.getById(id));
    }

    /**
     * 新增报刊 配置 表
     * @return R
     */
    @ApiOperation(value = "新增报刊 配置 表", notes = "新增报刊 配置 表")
    @SysLog("新增报刊 配置 表" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('base_basenewspaper_add')" )
    public R save(@RequestBody BaseNewspaperVo baseNewspaperVo) {
        return baseNewspaperService.save(baseNewspaperVo);
    }

    /**
     * 修改报刊 配置 表
     * @return R
     */
    @ApiOperation(value = "修改报刊 配置 表", notes = "修改报刊 配置 表")
    @SysLog("修改报刊 配置 表" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('base_basenewspaper_edit')" )
    public R updateById(@RequestBody BaseNewspaperVo baseNewspaperVo) {
        return baseNewspaperService.update(baseNewspaperVo);
    }

    /**
     * 通过id删除报刊 配置 表
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id删除报刊 配置 表", notes = "通过id删除报刊 配置 表")
    @SysLog("通过id删除报刊 配置 表" )
    @DeleteMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('base_basenewspaper_del')" )
    public R removeById(@PathVariable Integer id) {

        BaseNewspaper baseNewspaper=baseNewspaperService.getById(id);
        if(baseNewspaper==null){
            return R.failed("报刊不存在");
        }
        else {
            baseNewspaper.setDeleteFlag(1);
            baseNewspaperService.updatePriority(baseNewspaper.getPriority(),null);
            return R.ok(baseNewspaperService.updateById(baseNewspaper));
        }
    }

}
