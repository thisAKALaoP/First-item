

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
 * ctp 版价格及套数设置
 *
 * @author yangcw
 * @date 2021-05-31 10:43:24
 */
@Data
@TableName("base_ctp")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ctp 版价格及套数设置")
public class BaseCtp extends Model<BaseCtp> {
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
