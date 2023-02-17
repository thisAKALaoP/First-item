package com.taiji.erp.base.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("区域关联关系表")
public class AreaLinkVo {

    @ApiModelProperty("区域id")
    private Integer areaId;

    @ApiModelProperty("区域名")
    private String areaName;
}
