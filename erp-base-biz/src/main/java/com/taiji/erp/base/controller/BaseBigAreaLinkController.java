

package com.taiji.erp.base.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.taiji.erp.base.api.entity.BaseBigAreaLink;
import com.taiji.erp.base.api.vo.BigAreaLinkVo;
import com.taiji.erp.base.service.BaseBigAreaLinkService;
import com.taiji.erp.common.core.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * 大区 与 地区 关联关系表
 *
 * @author yangcw
 * @date 2021-05-10 16:50:10
 */
@RestController
@AllArgsConstructor
@RequestMapping("/baseBigAreaLink" )
@Api(value = "baseBigAreaLink", tags = "大区 与 地区 关联关系表管理")
public class BaseBigAreaLinkController {

    private final  BaseBigAreaLinkService baseBigAreaLinkService;

    /**
     * 列表查询
     */
    @ApiOperation(value = "列表查询", notes = "列表查询")
    @GetMapping("/page" )
    public R getBaseBigAreaLinkPage(BigAreaLinkVo bigAreaLinkVo) {

        return R.ok(baseBigAreaLinkService.findAreaList(bigAreaLinkVo.getBigAreaId()));

    }

    @ApiOperation(value = "删除大区与地区的关联",notes = "删除大区与地区的关联")
    @DeleteMapping(value = "/delete")
    @PreAuthorize("@pms.hasPermission('base_basebigarea_edit')" )
    public R<?> delete(@RequestBody @Validated BigAreaLinkVo bigAreaLinkVo){

        QueryWrapper<BaseBigAreaLink> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("bigarea_id",bigAreaLinkVo.getBigAreaId())
                .in("area_id",bigAreaLinkVo.getAreaList());

        return R.ok(baseBigAreaLinkService.remove(queryWrapper));

    }

    @ApiOperation(value = "添加大区与地区的关联",notes = "添加大区与地区的关联")
    @PostMapping(value = "/save")
    @PreAuthorize("@pms.hasPermission('base_basebigarea_edit')" )
    public R<?> save(@RequestBody @Validated BigAreaLinkVo bigAreaLinkVo){

        for (Integer areaId:bigAreaLinkVo.getAreaList()){
            BaseBigAreaLink baseBigAreaLink=new BaseBigAreaLink();
            baseBigAreaLink.setBigareaId(bigAreaLinkVo.getBigAreaId());
            baseBigAreaLink.setAreaId(areaId);
            baseBigAreaLinkService.save(baseBigAreaLink);
        }

        return R.ok();
    }

}
