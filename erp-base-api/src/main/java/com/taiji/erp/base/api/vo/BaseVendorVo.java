package com.taiji.erp.base.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "供应商视图")
public class BaseVendorVo {

    @ApiModelProperty(value = "供应商id")
    private Integer id;

    @ApiModelProperty(value = "供应商名称")
    private String vendorName;

    @ApiModelProperty(value = "供应商简称")
    private String vendorSimpleName;

    @ApiModelProperty(value = "联系电话")
    private String linkPhone;

    @ApiModelProperty(value = "联系人")
    private String linkPerson;

    @ApiModelProperty(value = "详细地址")
    private String address;

    @ApiModelProperty(value = "税号")
    private String taxCode;

    @ApiModelProperty(value = "开户行")
    private String bankName;

    @ApiModelProperty(value = "银行账号")
    private String bankAccount;

    @ApiModelProperty(value = "备注")
    private String vendorDesc;

    @ApiModelProperty(value = "优先级")
    private Integer priority;

    @ApiModelProperty(value = "状态 0-正常 1-禁用")
    private Integer state;
}
