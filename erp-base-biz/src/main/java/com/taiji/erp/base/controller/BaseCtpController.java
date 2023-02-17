

package com.taiji.erp.base.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taiji.erp.base.api.vo.BaseCtpVo;
import com.taiji.erp.common.core.util.R;
import com.taiji.erp.common.log.annotation.SysLog;
import com.taiji.erp.base.api.entity.BaseCtp;
import com.taiji.erp.base.service.BaseCtpService;
import com.taiji.erp.common.security.util.SecurityUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


/**
 * ctp 版价格及套数设置
 *
 * @author yangcw
 * @date 2021-05-31 10:43:24
 */
@RestController
@AllArgsConstructor
@RequestMapping("/baseCtp" )
@Api(value = "baseCtp", tags = "ctp 版价格及套数设置管理")
public class BaseCtpController {

    private final  BaseCtpService baseCtpService;

    /**
     * 分页查询
     * @param page 分页对象
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page" )
    public R getBaseCtpPage(Page page, BaseCtpVo baseCtpVo) {
        return baseCtpService.page(page,baseCtpVo);
    }


    /**
     * 通过id查询ctp 版价格及套数设置
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/{id}" )
    public R getById(@PathVariable("id" ) Integer id) {
        return R.ok(baseCtpService.getById(id));
    }

    /**
     * 新增ctp 版价格及套数设置
     * @param baseCtp ctp 版价格及套数设置
     * @return R
     */
    @ApiOperation(value = "新增ctp 版价格及套数设置", notes = "新增ctp 版价格及套数设置")
    @SysLog("新增ctp 版价格及套数设置" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('base_basectp_add')" )
    public R save(@RequestBody BaseCtp baseCtp) {
        baseCtp.setDeleteFlag(0);
        baseCtp.setCreateDate(LocalDateTime.now());
        baseCtp.setCreateUserId(SecurityUtils.getUser().getId());
        return R.ok(baseCtpService.save(baseCtp));
    }

    /**
     * 修改ctp 版价格及套数设置
     * @param baseCtp ctp 版价格及套数设置
     * @return R
     */
    @ApiOperation(value = "修改ctp 版价格及套数设置", notes = "修改ctp 版价格及套数设置")
    @SysLog("修改ctp 版价格及套数设置" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('base_basectp_edit')" )
    public R updateById(@RequestBody BaseCtp baseCtp) {
        return R.ok(baseCtpService.updateById(baseCtp));
    }

    /**
     * 通过id删除ctp 版价格及套数设置
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id删除ctp 版价格及套数设置", notes = "通过id删除ctp 版价格及套数设置")
    @SysLog("通过id删除ctp 版价格及套数设置" )
    @DeleteMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('base_basectp_del')" )
    public R removeById(@PathVariable Integer id) {
        BaseCtp baseCtp=baseCtpService.getById(id);
        if(baseCtp==null||baseCtp.getDeleteFlag()==1){
            return R.failed("ctp不存在");
        }
        baseCtp.setDeleteFlag(1);
        return R.ok(baseCtpService.updateById(baseCtp));
    }

}
