package com.taiji.erp.base.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@ApiModel(value = "大区与区域关联视图",description = "大区与区域关联")
public class BigAreaLinkVo {

    @NotNull(message = "地区不能为空")
    @ApiModelProperty(value = "区域id")
    private List<Integer> areaList;

    @NotNull(message = "大区不能为空")
    @ApiModelProperty(value = "大区id")
    private Integer bigAreaId;

}
