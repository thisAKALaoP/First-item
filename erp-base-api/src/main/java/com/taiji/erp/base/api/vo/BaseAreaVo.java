package com.taiji.erp.base.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@ApiModel(value = "区域视图",description = "区域请求参数")
public class BaseAreaVo {

    @ApiModelProperty(value = "id")
    private Integer id;

    @NotEmpty
    @ApiModelProperty(value = "区域名")
    private String areaName;

    @ApiModelProperty(value = "父级id")
    private Integer parentId;

    @ApiModelProperty(value = "父级区域名")
    private String parentName;

    @ApiModelProperty(value = "排序")
    private Integer priority;

    @ApiModelProperty(value = "状态 0=正常 1=停用")
    private Integer state;
}
