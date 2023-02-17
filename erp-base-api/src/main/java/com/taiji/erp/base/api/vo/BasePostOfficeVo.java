package com.taiji.erp.base.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@ApiModel(value = "发行局设置")
public class BasePostOfficeVo {

    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value="邮局代码")
    private String postOfficeCode;

    @ApiModelProperty(value="邮局名称")
    private String postOfficeName;

    @ApiModelProperty(value="联系人")
    private String linkPerson;

    @ApiModelProperty(value="联系电话")
    private String linkPhone;

    @ApiModelProperty(value="所在地区")
    private Integer locationAreaId;

    @ApiModelProperty(value = "所在地区名")
    private String locationName;

    @ApiModelProperty(value="要报印点id")
    private Integer orgId;

    @ApiModelProperty(value = "印点名")
    private String orgName;

    @ApiModelProperty(value="发行局类型 字典项 post_office_type     post_office=邮局 ")
    private String postOfficeType;

    @ApiModelProperty(value = "发行局类型名")
    private String postOfficeTypeName;

    @ApiModelProperty(value = "接报时间")
    private LocalDateTime receiveTime;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateDate;

    @ApiModelProperty(value="排序优先级")
    private Integer priority;

    @ApiModelProperty(value = "区域id集合")
    private List<Integer> areaList;
}
