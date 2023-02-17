

package com.taiji.erp.base.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taiji.erp.base.api.entity.BaseBigarea;
import com.taiji.erp.base.api.vo.BaseBigAreaVo;
import com.taiji.erp.base.service.BaseBigareaService;
import com.taiji.erp.common.core.util.R;
import com.taiji.erp.common.log.annotation.SysLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static javafx.scene.input.KeyCode.R;


/**
 * 大区设置表 
 *
 * @author yangcw
 * @date 2021-05-10 16:50:10
 */
@RestController
@AllArgsConstructor
@RequestMapping("/baseBigArea" )
@Api(value = "baseBigArea", tags = "大区设置表 管理")
public class BaseBigareaController {

    private final  BaseBigareaService baseBigareaService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param baseBigarea 大区设置表 
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page" )
    public R getBaseBigareaPage(Page page, BaseBigarea baseBigarea) {
        return R.ok(baseBigareaService.page(page, Wrappers.query(baseBigarea).eq("delete_flag",0).orderByAsc("priority")));
    }

    @ApiOperation(value = "列表查询",notes = "列表查询")
    @GetMapping(value = "/list")
    public R<?> list(BaseBigAreaVo bigAreaVo){
        BaseBigarea bigarea=new BaseBigarea();
        BeanUtils.copyProperties(bigAreaVo,bigarea);
        return R.ok(baseBigareaService.list(Wrappers.query(bigarea).lambda().eq(BaseBigarea::getDeleteFlag,0)));
    }

    /**
     * 通过id查询大区设置表 
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/{id}" )
    public R getById(@PathVariable("id" ) Integer id) {
        return R.ok(baseBigareaService.getById(id));
    }

    /**
     * 新增大区设置表 
     * @param baseBigAreaVo 大区设置表
     * @return R
     */
    @ApiOperation(value = "新增大区设置表 ", notes = "新增大区设置表 ")
    @SysLog("新增大区设置表 " )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('base_basebigarea_add')" )
    public synchronized R save(@RequestBody @Validated BaseBigAreaVo baseBigAreaVo) {

        QueryWrapper<BaseBigarea> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("bigarea_code",baseBigAreaVo.getBigareaCode())
                .or().eq("bigarea_name",baseBigAreaVo.getBigareaName());
        if(baseBigareaService.count(queryWrapper)>0){
            return R.failed("大区名称或代码已存在");
        }
        return R.ok(baseBigareaService.saveByVo(baseBigAreaVo));
    }

    /**
     * 修改大区设置表 
     * @param baseBigAreaVo 大区设置表
     * @return R
     */
    @ApiOperation(value = "修改大区设置表 ", notes = "修改大区设置表 ")
    @SysLog("修改大区设置表 " )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('base_basebigarea_edit')" )
    public R updateById(@RequestBody @Validated BaseBigAreaVo baseBigAreaVo) {

        QueryWrapper<BaseBigarea> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("id",baseBigAreaVo.getId())
                .eq("delete_flag",0);
        if(baseBigareaService.count(queryWrapper)==0){
            return R.failed("大区不存在");
        }

        return R.ok(baseBigareaService.updateByVo(baseBigAreaVo));
    }

    /**
     * 通过id删除大区设置表 
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id删除大区设置表 ", notes = "通过id删除大区设置表 ")
    @SysLog("通过id删除大区设置表 " )
    @DeleteMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('base_basebigarea_del')" )
    public R removeById(@PathVariable Integer id) {
        //删除大区
        BaseBigarea baseBigarea=baseBigareaService.getById(id);
        if(baseBigarea==null){
            return R.failed("大区不存在");
        }
        baseBigarea.setDeleteFlag(1);
        baseBigareaService.updatePriority(baseBigarea.getPriority(),null);
        return R.ok(baseBigareaService.updateById(baseBigarea));
    }

}
