package com.taiji.erp.base.api.dto;

import com.taiji.erp.base.api.vo.AreaLinkVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("报刊发行局区域关联视图表")
public class PostOfficeAreaLinkerDto {

    @ApiModelProperty(value = "报刊发行局id")
    private Integer postOfficeId;

    @ApiModelProperty(value = "报刊发行局名称")
    private String postOfficeName;

    @ApiModelProperty(value = "关联的区域列表")
    private List<AreaLinkVo> areaLinkVoList;

}
