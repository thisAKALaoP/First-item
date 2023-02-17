package com.taiji.erp.base.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ApiModel(value = "板数设置视图")
public class BasePageCountVo {


    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "报刊id")
    private Integer newspaperId;

    @ApiModelProperty(value = "报刊名")
    private String newspaperName;

    @ApiModelProperty(value = "印点id")
    private Integer ydId;

    @ApiModelProperty(value = "印点名")
    private String ydName;

    @ApiModelProperty(value = "周   1   2  3  4  5  6  7 ")
    private Integer weekDay;

    @ApiModelProperty(value = "出版日期  (有出版日期的优先取出版日期的版数)")
    private LocalDateTime publishDate;

    @ApiModelProperty(value = "版数")
    private Integer pageCount;

    @ApiModelProperty(value = "增版情况 字典项 add_page  temporary=临时增版")
    private String addPage;

    @ApiModelProperty(value = "刊期类型 字典项journal_type  normal=正常  stop=停刊  add=增刊  conjunction=合刊")
    private String journalType;
}
