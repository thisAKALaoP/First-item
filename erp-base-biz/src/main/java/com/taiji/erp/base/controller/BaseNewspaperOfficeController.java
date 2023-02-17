

package com.taiji.erp.base.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taiji.erp.base.api.vo.BaseNewspaperOfficeVo;
import com.taiji.erp.common.core.util.R;
import com.taiji.erp.common.log.annotation.SysLog;
import com.taiji.erp.base.api.entity.BaseNewspaperOffice;
import com.taiji.erp.base.service.BaseNewspaperOfficeService;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


/**
 * 报社 设置表
 *
 * @author yangcw
 * @date 2021-05-18 10:13:07
 */
@RestController
@AllArgsConstructor
@RequestMapping("/baseNewspaperOffice" )
@Api(value = "baseNewspaperOffice", tags = "报社 设置表管理")
public class BaseNewspaperOfficeController {

    private final  BaseNewspaperOfficeService baseNewspaperOfficeService;

    /**
     * 分页查询
     * @param page 分页对象
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page" )
    public R getBaseNewspaperOfficePage(Page page, BaseNewspaperOfficeVo baseNewspaperOfficeVo) {

        BaseNewspaperOffice office=new BaseNewspaperOffice();
        BeanUtils.copyProperties(baseNewspaperOfficeVo,office);

        return R.ok(baseNewspaperOfficeService.page(page, Wrappers.query(office)));
    }


    /**
     * 通过id查询报社 设置表
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/{id}" )
    public R getById(@PathVariable("id" ) Integer id) {
        return R.ok(baseNewspaperOfficeService.getById(id));
    }

    /**
     * 新增报社 设置表
     * @return R
     */
    @ApiOperation(value = "新增报社 设置表", notes = "新增报社 设置表")
    @SysLog("新增报社 设置表" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('base_basenewspaperoffice_add')" )
    public R save(@RequestBody BaseNewspaperOfficeVo baseNewspaperOfficeVo){
        return baseNewspaperOfficeService.save(baseNewspaperOfficeVo);
    }

    /**
     * 修改报社 设置表
     * @return R
     */
    @ApiOperation(value = "修改报社 设置表", notes = "修改报社 设置表")
    @SysLog("修改报社 设置表" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('base_basenewspaperoffice_edit')" )
    public R updateById(@RequestBody BaseNewspaperOfficeVo baseNewspaperOfficeVo) {

        return baseNewspaperOfficeService.update(baseNewspaperOfficeVo);

    }

    /**
     * 通过id删除报社 设置表
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id删除报社 设置表", notes = "通过id删除报社 设置表")
    @SysLog("通过id删除报社 设置表" )
    @DeleteMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('base_basenewspaperoffice_del')" )
    public R removeById(@PathVariable Integer id) {

        BaseNewspaperOffice office=baseNewspaperOfficeService.getById(id);
        if(office==null){
            return R.failed("报社不存在");
        }
        else {
            office.setDeleteFlag(1);
            return R.ok(baseNewspaperOfficeService.updateById(office));
        }

    }

}
