

package com.taiji.erp.base.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taiji.erp.common.core.util.R;
import com.taiji.erp.common.log.annotation.SysLog;
import com.taiji.erp.base.api.entity.BaseTransport;
import com.taiji.erp.base.service.BaseTransportService;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


/**
 * 运输方式配置表
 *
 * @author yangcw
 * @date 2021-05-22 16:09:13
 */
@RestController
@AllArgsConstructor
@RequestMapping("/baseTransport" )
@Api(value = "baseTransport", tags = "运输方式配置表管理")
public class BaseTransportController {

    private final  BaseTransportService baseTransportService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param baseTransport 运输方式配置表
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page" )
    public R getBaseTransportPage(Page page, BaseTransport baseTransport) {
        return R.ok(baseTransportService.page(page, Wrappers.query(baseTransport)));
    }

    /**
     * 修改运输方式配置表
     * @param baseTransport 运输方式配置表
     * @return R
     */
    @ApiOperation(value = "修改运输方式配置表", notes = "修改运输方式配置表")
    @SysLog("修改运输方式配置表" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('base_basetransport_edit')" )
    public R updateById(@RequestBody BaseTransport baseTransport) {
        return R.ok(baseTransportService.updateById(baseTransport));
    }

}
