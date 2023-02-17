package com.taiji.erp.base.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@ApiModel(value = "报刊设置类")
public class BaseNewspaperVo {

    @ApiModelProperty(value = "报刊id")
    private Integer id;

    @ApiModelProperty(value = "报刊代码")
    private String newspaperCode;

    @ApiModelProperty(value = "报刊名称")
    private String newspaperName;

    @ApiModelProperty(value = "所属报社id")
    private Integer newspaperOfficeId;

    @ApiModelProperty(value = "所属报社名称")
    private Integer newspaperOfficeName;

    @ApiModelProperty(value = "发行周期字典项")
    private String releasePeriod;

    @ApiModelProperty(value = "发行周期")
    private String releasePeriodName;

    @ApiModelProperty(value = "优先级")
    private Integer priority;

    @ApiModelProperty(value = "状态 0=正常 1=停用")
    private Integer state;
}
