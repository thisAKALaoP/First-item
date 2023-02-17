package com.taiji.erp.base.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("印点供应商配置视图")
public class BaseYdVendorVo {

    @ApiModelProperty(value = "")
    private Integer id;

    @ApiModelProperty(value = "印点id")
    private Integer ydId;

    @ApiModelProperty(value = "供应商id")
    private Integer vendorId;

    @ApiModelProperty(value = "供应商名称")
    private String vendorName;

    @ApiModelProperty(value = "运输方式id")
    private Integer transportId;

    @ApiModelProperty(value = "运输方式")
    private String transport;

    @ApiModelProperty(value = "收货人")
    private String receiver;

    @ApiModelProperty(value = "联系电话")
    private String linkPhone;

    @ApiModelProperty(value = "收货地点")
    private String receivePlace;

    @ApiModelProperty(value = "备注")
    private String receiveDesc;

    @ApiModelProperty(value = "状态 0=正常 1=停用")
    private Integer state;

}
