

package com.taiji.erp.base.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taiji.erp.common.core.util.R;
import com.taiji.erp.common.log.annotation.SysLog;
import com.taiji.erp.base.api.entity.BaseYdLinkPerson;
import com.taiji.erp.base.service.BaseYdLinkPersonService;
import com.taiji.erp.common.security.util.SecurityUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;


/**
 * 印点联系人 配置 表
 *
 * @author yangcw
 * @date 2021-05-22 16:09:13
 */
@RestController
@AllArgsConstructor
@RequestMapping("/baseYdLinkPerson" )
@Api(value = "baseYdLinkPerson", tags = "印点联系人 配置 表管理")
public class BaseYdLinkPersonController {

    private final  BaseYdLinkPersonService baseYdLinkPersonService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param baseYdLinkPerson 印点联系人 配置 表
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page" )
    public R getBaseYdLinkPersonPage(Page page, BaseYdLinkPerson baseYdLinkPerson) {
        return R.ok(baseYdLinkPersonService.page(page, Wrappers.query(baseYdLinkPerson).lambda().eq(BaseYdLinkPerson::getDeleteFlag,0)));
    }


    /**
     * 通过id查询印点联系人 配置 表
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/{id}" )
    public R getById(@PathVariable("id" ) Integer id) {
        return R.ok(baseYdLinkPersonService.getById(id));
    }

    /**
     * 新增印点联系人 配置 表
     * @param baseYdLinkPerson 印点联系人 配置 表
     * @return R
     */
    @ApiOperation(value = "新增印点联系人 配置 表", notes = "新增印点联系人 配置 表")
    @SysLog("新增印点联系人 配置 表" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('base_baseydlinkperson_add')" )
    public R save(@RequestBody BaseYdLinkPerson baseYdLinkPerson) {
        baseYdLinkPerson.setDeleteFlag(0);
        baseYdLinkPerson.setCreateDate(LocalDateTime.now());
        baseYdLinkPerson.setCreateUserId(SecurityUtils.getUser().getId());
        return R.ok(baseYdLinkPersonService.save(baseYdLinkPerson));
    }

    /**
     * 修改印点联系人 配置 表
     * @param baseYdLinkPerson 印点联系人 配置 表
     * @return R
     */
    @ApiOperation(value = "修改印点联系人 配置 表", notes = "修改印点联系人 配置 表")
    @SysLog("修改印点联系人 配置 表" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('base_baseydlinkperson_edit')" )
    public R updateById(@RequestBody BaseYdLinkPerson baseYdLinkPerson) {
        return R.ok(baseYdLinkPersonService.updateById(baseYdLinkPerson));
    }

    /**
     * 通过id删除印点联系人 配置 表
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id删除印点联系人 配置 表", notes = "通过id删除印点联系人 配置 表")
    @SysLog("通过id删除印点联系人 配置 表" )
    @DeleteMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('base_baseydlinkperson_del')" )
    public R removeById(@PathVariable Integer id) {
        BaseYdLinkPerson baseYdLinkPerson=baseYdLinkPersonService.getById(id);
        if(baseYdLinkPerson==null||baseYdLinkPerson.getDeleteFlag()==1){
            return R.failed("印点联系人不存在");
        }
        baseYdLinkPerson.setDeleteFlag(1);
        return R.ok(baseYdLinkPersonService.updateById(baseYdLinkPerson));
    }

}
