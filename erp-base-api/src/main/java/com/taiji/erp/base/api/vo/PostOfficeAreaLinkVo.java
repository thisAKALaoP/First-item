package com.taiji.erp.base.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@ApiModel(value = "发行局关联关系表")
public class PostOfficeAreaLinkVo {

    @ApiModelProperty(value = "发行局id")
    @NotNull(message = "发行局id不能为空")
    private Integer officeId;

    @ApiModelProperty(value = "地域id集合")
    @NotNull(message = "区域不能为空")
    private List<Integer> areaList;
}
