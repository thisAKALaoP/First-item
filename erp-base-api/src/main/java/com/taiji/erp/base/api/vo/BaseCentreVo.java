package com.taiji.erp.base.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;


@Data
@ApiModel(value = "中央部门视图",description = "中央部门设置")
public class BaseCentreVo {


    private Integer id;

    @NotEmpty(message = "单位代码不能为空")
    @ApiModelProperty(value = "单位代码")
    private String centreCode;

    @NotEmpty(message = "单位名称不能为空")
    @ApiModelProperty(value = "单位名称")
    private String centreName;

    @NotEmpty(message = "单位类别不能为空")
    @ApiModelProperty(value = "单位类别")
    private String centreType;

    @ApiModelProperty(value = "单位类别名")
    private String centreTypeName;

    @ApiModelProperty(value = "负责人")
    private String centrePrincipal;

    @ApiModelProperty(value = "优先级")
    private Integer priority;

    @ApiModelProperty(value = "状态 0=正常 1=停用")
    private Integer state;
}
