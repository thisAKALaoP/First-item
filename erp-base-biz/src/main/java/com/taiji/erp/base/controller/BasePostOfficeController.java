

package com.taiji.erp.base.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taiji.erp.base.api.vo.BasePostOfficeVo;
import com.taiji.erp.common.core.util.R;
import com.taiji.erp.common.log.annotation.SysLog;
import com.taiji.erp.base.api.entity.BasePostOffice;
import com.taiji.erp.base.service.BasePostOfficeService;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


/**
 * 报刊发行局 
 *
 * @author yangcw
 * @date 2021-05-13 16:31:57
 */
@RestController
@AllArgsConstructor
@RequestMapping("/basePostOffice" )
@Api(value = "basePostOffice", tags = "报刊发行局 管理")
public class BasePostOfficeController {

    private final  BasePostOfficeService basePostOfficeService;

    /**
     * 分页查询
     * @param page 分页对象
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page" )
    public R getBasePostOfficePage(Page page, BasePostOfficeVo basePostOfficeVo) {

        return basePostOfficeService.page(page,basePostOfficeVo);
    }


    /**
     * 通过id查询报刊发行局 
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/{id}" )
    public R getById(@PathVariable("id" ) Integer id) {
        return R.ok(basePostOfficeService.getDetailById(id));
    }

    /**
     * 新增报刊发行局
     * @return R
     */
    @ApiOperation(value = "新增报刊发行局 ", notes = "新增报刊发行局 ")
    @SysLog("新增报刊发行局 " )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('base_basepostoffice_add')" )
    public R save(@RequestBody BasePostOfficeVo basePostOfficeVo) {
        return basePostOfficeService.save(basePostOfficeVo);
    }

    /**
     * 修改报刊发行局
     * @return R
     */
    @ApiOperation(value = "修改报刊发行局 ", notes = "修改报刊发行局 ")
    @SysLog("修改报刊发行局 " )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('base_basepostoffice_edit')" )
    public synchronized R updateById(@RequestBody BasePostOfficeVo basePostOfficeVo) {
        return basePostOfficeService.update(basePostOfficeVo);
    }

    /**
     * 通过id删除报刊发行局 
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id删除报刊发行局 ", notes = "通过id删除报刊发行局 ")
    @SysLog("通过id删除报刊发行局 " )
    @DeleteMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('base_basepostoffice_del')" )
    public R removeById(@PathVariable Integer id) {

        QueryWrapper<BasePostOffice> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("id",id)
                .eq("delete_flag",0);
        BasePostOffice office=basePostOfficeService.getOne(queryWrapper);
        if(office==null){
            return R.failed("发行局不存在");
        }
        basePostOfficeService.updatePriority(office.getPriority(),null);
        office.setDeleteFlag(1);
        return R.ok(basePostOfficeService.updateById(office));
    }

}
