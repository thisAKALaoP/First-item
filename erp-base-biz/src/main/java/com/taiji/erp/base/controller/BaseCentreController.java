

package com.taiji.erp.base.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taiji.erp.base.api.entity.BaseCentre;
import com.taiji.erp.base.api.vo.BaseCentreVo;
import com.taiji.erp.base.service.BaseCentreService;
import com.taiji.erp.common.core.util.R;
import com.taiji.erp.common.log.annotation.SysLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;


/**
 * 中央各部门设置表
 *
 * @author yangcw
 * @date 2021-05-10 16:50:10
 */
@RestController
@AllArgsConstructor
@RequestMapping("/baseCentre" )
@Api(value = "baseCentre", tags = "中央各部门设置表管理")
@Validated
public class BaseCentreController {

    private final  BaseCentreService baseCentreService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param baseCentreVo 中央各部门设置表
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page" )
    public R getBaseCentrePage(Page page, BaseCentreVo baseCentreVo) {
        return baseCentreService.page(page,baseCentreVo);
    }

    /**
     * 新增中央各部门设置表
     * @param baseCentreVo 中央各部门设置表
     * @return R
     */
    @ApiOperation(value = "新增中央各部门设置表", notes = "新增中央各部门设置表")
    @SysLog("新增中央各部门设置表" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('base_basecentre_add')" )
    public R save(@RequestBody @Validated BaseCentreVo baseCentreVo) {
        return baseCentreService.save(baseCentreVo);
    }

    /**
     * 修改中央各部门设置表
     * @param baseCentreVo 中央各部门设置表
     * @return R
     */
    @ApiOperation(value = "修改中央各部门设置表", notes = "修改中央各部门设置表")
    @SysLog("修改中央各部门设置表" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('base_basecentre_edit')" )
    public R updateById(@RequestBody BaseCentreVo baseCentreVo) {
        return baseCentreService.update(baseCentreVo);
    }

    /**
     * 通过id删除中央各部门设置表
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id删除中央各部门设置表", notes = "通过id删除中央各部门设置表")
    @SysLog("通过id删除中央各部门设置表" )
    @DeleteMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('base_basecentre_del')" )
    public synchronized R removeById(@PathVariable Integer id) {
        return baseCentreService.delete(id);
    }

    @ApiOperation(value = "批量修改部门状态")
    @PutMapping(value = "/updateState")
    @PreAuthorize("@pms.hasPermission('base_basecentre_edit')")
    @ApiImplicitParams(value = {@ApiImplicitParam(name = "idList",value = "部门id集合"),@ApiImplicitParam(name = "state",value = "部门状态")})
    public R<?> updateState(@Validated @NotNull(message = "id不能为空")  @RequestParam(value = "idList") List<Integer> idList,@Validated @NotNull(message = "状态不能为空") Integer state){

        QueryWrapper<BaseCentre> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("delete_flag",0)
                .in("id",idList);
        List<BaseCentre> list=baseCentreService.list(queryWrapper);
        for (BaseCentre baseCentre:list){
            baseCentre.setState(state);
            baseCentreService.updateById(baseCentre);
        }

        return R.ok();

    }

}
