package com.taiji.erp.base.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "印点配置信息")
public class BaseYdVo {

    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "印点名称")
    private String ydName;

    @ApiModelProperty(value = "印点简称")
    private String ydSimpleName;

    @ApiModelProperty(value = "所属省市ID")
    private Integer areaId;

    @ApiModelProperty(value = "省名")
    private String province;

    @ApiModelProperty(value = "市名")
    private String city;

    @ApiModelProperty(value = "所属大区id")
    private Integer bigareaId;

    @ApiModelProperty(value = "大区名")
    private String bigAreaName;

    @ApiModelProperty(value = "联系人")
    private String linkPerson;

    @ApiModelProperty(value = "联系电话")
    private String linkPhone;

    @ApiModelProperty(value = "传真")
    private String linkFax;

    @ApiModelProperty(value = "email")
    private String linkEmail;

    //少了备注、排序号、系统手机号、印点部门
    @ApiModelProperty(value = "印点备注")
    private String ydDesc;

    @ApiModelProperty(value = "优先级")
    private Integer priority;

    @ApiModelProperty(value = "系统手机号")
    private String systemMobilePhone;

    @ApiModelProperty(value = "仓库类型 字典项 warehouse_type own=自有  rent=外租 ")
    private String warehouseType;

    @ApiModelProperty(value = "最大库存")
    private Integer warehouseMax;

    @ApiModelProperty(value = "给人民最大库存")
    private Integer warehousePeople;

    @ApiModelProperty(value = "库存最低天数")
    private Integer stockDayCount;
}
