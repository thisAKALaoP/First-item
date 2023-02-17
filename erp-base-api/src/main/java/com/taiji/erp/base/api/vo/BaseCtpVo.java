

package com.taiji.erp.base.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@ApiModel(value = "ctp 版价格及套数视图")
public class BaseCtpVo {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @ApiModelProperty(value = "")
    private Integer id;
    /**
     * 报刊id
     */
    @ApiModelProperty(value = "报刊id")
    private Integer newspaperId;

    @ApiModelProperty(value = "报刊名")
    private String newspaperName;

    /**
     * 印点id
     */
    @ApiModelProperty(value = "印点id")
    private Integer ydId;

    @ApiModelProperty(value = "印点名")
    private String ydName;

    /**
     * 出版月份
     */
    @ApiModelProperty(value = "出版月份-日期")
    private String publishMonth;

    @ApiModelProperty(value = "搜索用，开始年月份")
    private String startMonth;

    @ApiModelProperty(value = "搜索用，结束年月份")
    private String endMonth;

    /**
     * ctp1价格
     */
    @ApiModelProperty(value = "ctp1价格")
    private BigDecimal ctp1Price;
    /**
     * ctp1套数
     */
    @ApiModelProperty(value = "ctp1套数")
    private Integer ctp1Count;
    /**
     * ctp2价格
     */
    @ApiModelProperty(value = "ctp2价格")
    private BigDecimal ctp2Price;
    /**
     * ctp1套数
     */
    @ApiModelProperty(value = "ctp1套数")
    private Integer ctp2Count;
    /**
     * ctp3价格
     */
    @ApiModelProperty(value = "ctp3价格")
    private BigDecimal ctp3Price;
    /**
     * ctp3套数
     */
    @ApiModelProperty(value = "ctp3套数")
    private Integer ctp3Count;
    /**
     * 5:30交报数量
     */
    @ApiModelProperty(value = "5:30交报数量")
    private Integer deliveryCount;
    /**
     * 状态 0=未确认  1=已确认
     */
    @ApiModelProperty(value = "状态 0=未确认  1=已确认")
    private Integer ctpState;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String ctpDesc;
    /**
     * 删除标识 0=正常 1=删除
     */
    @ApiModelProperty(value = "删除标识 0=正常 1=删除")
    private Integer deleteFlag;

    @ApiModelProperty(value = "查询总数")
    private Integer total;
}
