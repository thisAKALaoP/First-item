package com.taiji.erp.base.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@ApiModel(value = "大区视图",description = "大区请求参数")
public class BaseBigAreaVo {


    private Integer id;

    @NotEmpty
    @ApiModelProperty(value = "大区代码")
    private String bigareaCode;

    @NotEmpty
    @ApiModelProperty(value = "大区名")
    private String bigareaName;

    @ApiModelProperty(value = "优先级")
    private Integer priority;
}
