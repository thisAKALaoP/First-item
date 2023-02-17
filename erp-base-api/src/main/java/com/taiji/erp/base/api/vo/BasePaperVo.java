package com.taiji.erp.base.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "纸张配置视图")
public class BasePaperVo {

    @ApiModelProperty(value="")
    private Integer id;

    @ApiModelProperty(value="纸张名称  自动生成 克重+规格+品牌+产地 ")
    private String paperName;

    @ApiModelProperty(value="纸张编码 自动生成 数字 4位     自然数排  0001")
    private String paperCode;

    @ApiModelProperty(value="克重")
    private Integer paperWeight;

    @ApiModelProperty(value="规格")
    private Integer paperSize;

    @ApiModelProperty(value="品牌")
    private String paperBrand;

    @ApiModelProperty(value="产地来源 字典项 paper_origin domestic=国产 import=进口")
    private String paperOrigin;

    @ApiModelProperty(value="产地")
    private String produceArea;

    @ApiModelProperty(value="等级")
    private String paperLevel;

    @ApiModelProperty(value="状态 0=正常 1=停用")
    private Integer state;

    @ApiModelProperty(value = "排序优先级")
    private Integer priority;
}
