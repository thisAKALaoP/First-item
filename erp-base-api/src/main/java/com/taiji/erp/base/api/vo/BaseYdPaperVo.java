package com.taiji.erp.base.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel(value = "印点纸张视图")
public class BaseYdPaperVo {

    @ApiModelProperty(value = "")
    private Integer id;

    @ApiModelProperty(value = "印点id")
    private Integer ydId;

    @ApiModelProperty(value = "纸张id")
    private Integer paperId;

    @ApiModelProperty(value = "配比")
    private BigDecimal useRatio;

    @ApiModelProperty(value = "规格")
    private Integer paperSize;

    @ApiModelProperty(value = "纸张名称")
    private String paperName;

    @ApiModelProperty(value = "产地来源 字典项 paper_origin domestic=国产 import=进口")
    private String paperOrigin;

    @ApiModelProperty(value = "状态 0=正常 1=停用")
    private Integer state;
}
