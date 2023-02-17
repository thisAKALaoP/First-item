

package com.taiji.erp.base.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taiji.erp.base.api.vo.BasePrintPriceVo;
import com.taiji.erp.common.core.util.R;
import com.taiji.erp.common.log.annotation.SysLog;
import com.taiji.erp.base.api.entity.BasePrintPrice;
import com.taiji.erp.base.service.BasePrintPriceService;
import com.taiji.erp.common.security.util.SecurityUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sun.security.util.SecurityConstants;

import java.time.LocalDateTime;


/**
 * 印刷单价 配置表
 *
 * @author yangcw
 * @date 2021-05-31 10:43:24
 */
@RestController
@AllArgsConstructor
@RequestMapping("/basePrintPrice" )
@Api(value = "basePrintPrice", tags = "印刷单价 配置表管理")
public class BasePrintPriceController {

    private final  BasePrintPriceService basePrintPriceService;

    /**
     * 分页查询
     * @param page 分页对象
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page" )
    public R getBasePrintPricePage(Page page, BasePrintPriceVo basePrintPriceVo) {
        return basePrintPriceService.page(page,basePrintPriceVo);
    }


    /**
     * 通过id查询印刷单价 配置表
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/{id}" )
    public R getById(@PathVariable("id" ) Integer id) {
        return R.ok(basePrintPriceService.getById(id));
    }

    /**
     * 新增印刷单价 配置表
     * @param basePrintPrice 印刷单价 配置表
     * @return R
     */
    @ApiOperation(value = "新增印刷单价 配置表", notes = "新增印刷单价 配置表")
    @SysLog("新增印刷单价 配置表" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('base_baseprintprice_add')" )
    public R save(@RequestBody BasePrintPrice basePrintPrice) {
        basePrintPrice.setDeleteFlag(0);
        basePrintPrice.setCreateUserId(SecurityUtils.getUser().getId());
        basePrintPrice.setCreateDate(LocalDateTime.now());
        return R.ok(basePrintPriceService.save(basePrintPrice));
    }

    /**
     * 修改印刷单价 配置表
     * @param basePrintPrice 印刷单价 配置表
     * @return R
     */
    @ApiOperation(value = "修改印刷单价 配置表", notes = "修改印刷单价 配置表")
    @SysLog("修改印刷单价 配置表" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('base_baseprintprice_edit')" )
    public R updateById(@RequestBody BasePrintPrice basePrintPrice) {
        return R.ok(basePrintPriceService.updateById(basePrintPrice));
    }

    /**
     * 通过id删除印刷单价 配置表
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id删除印刷单价 配置表", notes = "通过id删除印刷单价 配置表")
    @SysLog("通过id删除印刷单价 配置表" )
    @DeleteMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('base_baseprintprice_del')" )
    public R removeById(@PathVariable Integer id) {
        BasePrintPrice printPrice=basePrintPriceService.getById(id);
        if(printPrice==null||printPrice.getDeleteFlag()==1){
            return R.failed("单价表不存在");
        }
        printPrice.setDeleteFlag(1);
        return R.ok(basePrintPriceService.updateById(printPrice));
    }

}
