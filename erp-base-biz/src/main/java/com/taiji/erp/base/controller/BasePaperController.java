

package com.taiji.erp.base.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taiji.erp.base.api.vo.BasePaperVo;
import com.taiji.erp.common.core.util.R;
import com.taiji.erp.common.log.annotation.SysLog;
import com.taiji.erp.base.api.entity.BasePaper;
import com.taiji.erp.base.service.BasePaperService;
import com.taiji.erp.common.security.annotation.Inner;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


/**
 * 纸张编码 表
 *
 * @author yangcw
 * @date 2021-05-22 16:09:13
 */
@RestController
@AllArgsConstructor
@RequestMapping("/basePaper" )
@Api(value = "basePaper", tags = "纸张编码 表管理")
public class BasePaperController {

    private final  BasePaperService basePaperService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param basePaper 纸张编码 表
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page" )
    public R getBasePaperPage(Page page, BasePaper basePaper) {
        return R.ok(basePaperService.page(page, Wrappers.query(basePaper).lambda().eq(BasePaper::getDeleteFlag,0).orderByAsc(BasePaper::getPriority)));
    }

    @ApiOperation(value = "纸张列表",notes = "纸张列表")
    @GetMapping(value = "/list")
    public R list(BasePaper basePaper){
        return R.ok(basePaperService.list(Wrappers.lambdaQuery(basePaper).eq(BasePaper::getDeleteFlag,0).orderByAsc(BasePaper::getPriority)));
    }

    /**
     * 通过id查询纸张编码 表
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/{id}" )
    public R getById(@PathVariable("id" ) Integer id) {
        return R.ok(basePaperService.getById(id));
    }

    /**
     * 新增纸张编码 表
     */
    @ApiOperation(value = "新增纸张编码 表", notes = "新增纸张编码 表")
    @SysLog("新增纸张编码 表" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('base_basepaper_add')" )
    public R save(@RequestBody BasePaperVo basePaperVo) {
        return basePaperService.save(basePaperVo);
    }

    /**
     * 修改纸张编码 表
     * @return R
     */
    @ApiOperation(value = "修改纸张编码 表", notes = "修改纸张编码 表")
    @SysLog("修改纸张编码 表" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('base_basepaper_edit')" )
    public R updateById(@RequestBody BasePaperVo basePaperVo) {
        return basePaperService.update(basePaperVo);
    }

    /**
     * 通过id删除纸张编码 表
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id删除纸张编码 表", notes = "通过id删除纸张编码 表")
    @SysLog("通过id删除纸张编码 表" )
    @DeleteMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('base_basepaper_del')" )
    public R removeById(@PathVariable Integer id) {

        BasePaper basePaper=basePaperService.getOne(new LambdaQueryWrapper<BasePaper>()
                .eq(BasePaper::getId,id)
                .eq(BasePaper::getDeleteFlag,0));
        if(basePaper==null){
            return R.failed("纸张编码表不存在");
        }
        basePaper.setDeleteFlag(1);

        return R.ok(basePaperService.updateById(basePaper));
    }

}
