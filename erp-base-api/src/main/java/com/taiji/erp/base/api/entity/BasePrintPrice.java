

package com.taiji.erp.base.api.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 印刷单价 配置表
 *
 * @author yangcw
 * @date 2021-05-31 10:43:24
 */
@Data
@TableName("base_print_price")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "印刷单价 配置表")
public class BasePrintPrice extends Model<BasePrintPrice> {
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
    /**
     * 印点id
     */
    @ApiModelProperty(value = "印点id")
    private Integer ydId;
    /**
     * 出版月份
     */
    @ApiModelProperty(value = "出版月份")
    private String publishMonth;
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
    /**
     * 创建日期
     */
    @ApiModelProperty(value = "创建日期")
    private LocalDateTime createDate;
    /**
     *
     */
    @ApiModelProperty(value = "")
    private Integer createUserId;
    /**
     *
     */
    @ApiModelProperty(value = "")
    private LocalDateTime updateDate;
    /**
     *
     */
    @ApiModelProperty(value = "")
    private Integer updateUserId;
}
