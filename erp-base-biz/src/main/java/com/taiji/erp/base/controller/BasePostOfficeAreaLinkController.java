

package com.taiji.erp.base.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.taiji.erp.base.api.dto.PostOfficeAreaLinkerDto;
import com.taiji.erp.base.api.entity.BaseArea;
import com.taiji.erp.base.api.entity.BasePostOffice;
import com.taiji.erp.base.api.vo.AreaLinkVo;
import com.taiji.erp.base.api.vo.PostOfficeAreaLinkVo;
import com.taiji.erp.base.service.BaseAreaService;
import com.taiji.erp.base.service.BasePostOfficeAreaLinkService;
import com.taiji.erp.base.service.BasePostOfficeService;
import com.taiji.erp.common.core.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 * 邮局 地区 关联关系表
 *
 * @author yangcw
 * @date 2021-05-13 16:31:57
 */
@RestController
@AllArgsConstructor
@RequestMapping("/basePostOfficeAreaLink" )
@Api(value = "basePostOfficeAreaLink", tags = "报刊发行局 地区 关联关系表管理")
public class BasePostOfficeAreaLinkController {

    private final  BasePostOfficeAreaLinkService basePostOfficeAreaLinkService;

    private final BasePostOfficeService basePostOfficeService;

    private final BaseAreaService baseAreaService;

    /**
     * 通过id查询邮局 地区 关联关系表
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/{id}" )
    public R getById(@PathVariable("id" ) Integer id) {
        return R.ok(basePostOfficeAreaLinkService.listAll(id));
    }

    /**
     * 新增邮局 地区 关联关系表
     * @param postOfficeAreaLinkVo 邮局 地区 关联关系表
     * @return R
     */
    @ApiOperation(value = "新增邮局 地区 关联关系表", notes = "新增邮局 地区 关联关系表")
    @PostMapping
    @PreAuthorize("@pms.hasPermission('base_basepostoffice_edit')" )
    public R save(@RequestBody PostOfficeAreaLinkVo postOfficeAreaLinkVo) {
        return basePostOfficeAreaLinkService.save(postOfficeAreaLinkVo);
    }

    /**
     * 通过id删除邮局 地区 关联关系表
     * @return R
     */
    @ApiOperation(value = "通过id删除邮局 地区 关联关系表", notes = "通过id删除邮局 地区 关联关系表")
    @DeleteMapping("/delete" )
    @PreAuthorize("@pms.hasPermission('base_basepostoffice_edit')" )
    public R removeById(@RequestBody PostOfficeAreaLinkVo postOfficeAreaLinkVo) {
        return basePostOfficeAreaLinkService.delete(postOfficeAreaLinkVo);
    }

    @ApiOperation(value = "通过邮局id查询关联的邮局和关联地区",notes = "通过邮局id查询关联的邮局和关联地区")
    @GetMapping(value = "/findAreaLinkByPostOfficeId/{orgId}")
    public R<PostOfficeAreaLinkerDto> findAreaLinkByPostOfficeId(@PathVariable(value = "orgId") @Validated @NotNull(message = "邮局id不能为空") Integer orgId){

        BasePostOffice office= basePostOfficeService.getOne(new LambdaQueryWrapper<BasePostOffice>()
                .eq(BasePostOffice::getDeleteFlag,0)
                .eq(BasePostOffice::getOrgId,orgId));
        if(office==null){
            return R.failed("邮局不存在");
        }
        //查找所在省份的id
        Integer localAreaId=0;
        if(office.getLocationAreaId()!=null){
            BaseArea baseArea=baseAreaService.getById(office.getLocationAreaId());
            if(baseArea!=null){
                localAreaId= Optional.ofNullable(baseArea.getParentId()).orElse(0);
            }
        }
        List<AreaLinkVo> linkVoList=basePostOfficeAreaLinkService.listAll(office.getId());
        PostOfficeAreaLinkerDto postOfficeAreaLinkerDto =new PostOfficeAreaLinkerDto();
        //过滤掉本省的
        final Integer finalLocalAreaId = localAreaId;
        postOfficeAreaLinkerDto.setAreaLinkVoList(linkVoList.stream().filter((vo)-> vo.getAreaId().intValue()!= finalLocalAreaId).collect(Collectors.toList()));
        postOfficeAreaLinkerDto.setPostOfficeId(office.getId());
        postOfficeAreaLinkerDto.setPostOfficeName(office.getPostOfficeName());

        return R.ok(postOfficeAreaLinkerDto);
    }
}
