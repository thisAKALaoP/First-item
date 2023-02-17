package com.taiji.erp.base.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "报社设置和视图")
public class BaseNewspaperOfficeVo {

    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "报社代码")
    private String officeCode;

    @ApiModelProperty(value = "报社名字")
    private String officeName;

    @ApiModelProperty(value = "报社地址")
    private String officeAddress;

    @ApiModelProperty(value = "报社邮编")
    private String postCode;

    @ApiModelProperty(value="联系电话")
    private String linkPhone;

    @ApiModelProperty(value="联系人")
    private String linkPerson;
}
