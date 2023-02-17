

package com.taiji.erp.base.api.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@ApiModel(value = "印刷单价 配置表")
public class BasePrintPriceVo{
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId
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
    @ApiModelProperty(value = "出版月份")
    private String publishMonth;

    @ApiModelProperty(value = "搜索用，开始年月份")
    private String startMonth;

    @ApiModelProperty(value = "搜索用，结束年月份")
    private String endMonth;

    /**
     * 黑白印刷费单价  元/每千印张
     */
    @ApiModelProperty(value = "黑白印刷费单价  元/每千印张")
    private BigDecimal priceHb;
    /**
     * 套色印刷费单价
     */
    @ApiModelProperty(value = "套色印刷费单价")
    private BigDecimal priceTs;
    /**
     * 单彩印刷费单价  元/每千印张
     */
    @ApiModelProperty(value = "单彩印刷费单价  元/每千印张")
    private BigDecimal priceDc;
    /**
     * 双彩印刷费单价 元/每千印张
     */
    @ApiModelProperty(value = "双彩印刷费单价 元/每千印张")
    private BigDecimal priceSc;
    /**
     * 前期制作费单价
     */
    @ApiModelProperty(value = "前期制作费单价")
    private BigDecimal priceQq;
    /**
     * 状态 0=未确认  1=已确认
     */
    @ApiModelProperty(value = "状态 0=未确认  1=已确认")
    private Integer priceState;
    /**
     * 删除标识 0=正常 1=删除
     */
    @ApiModelProperty(value = "删除标识 0=正常 1=删除")
    private Integer deleteFlag;

    @ApiModelProperty(value = "总数")
    private Integer total;
}
