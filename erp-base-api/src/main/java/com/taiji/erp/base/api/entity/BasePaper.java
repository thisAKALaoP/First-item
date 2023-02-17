

package com.taiji.erp.base.api.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 纸张编码 表
 *
 * @author yangcw
 * @date 2021-05-22 16:09:13
 */
@Data
@TableName("base_paper")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "纸张编码 表")
public class BasePaper extends Model<BasePaper> {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId
    @ApiModelProperty(value = "")
    private Integer id;
    /**
     * 纸张名称  自动生成 克重+规格+品牌+产地
     */
    @ApiModelProperty(value = "纸张名称  自动生成 克重+规格+品牌+产地 ")
    private String paperName;
    /**
     * 纸张编码 自动生成 数字 4位     自然数排  0001
     */
    @ApiModelProperty(value = "纸张编码 自动生成 数字 4位     自然数排  0001")
    private String paperCode;
    /**
     * 克重
     */
    @ApiModelProperty(value = "克重")
    private Integer paperWeight;
    /**
     * 规格
     */
    @ApiModelProperty(value = "规格")
    private Integer paperSize;
    /**
     * 品牌
     */
    @ApiModelProperty(value = "品牌")
    private String paperBrand;
    /**
     * 产地来源 字典项 paper_origin domestic=国产 import=进口
     */
    @ApiModelProperty(value = "产地来源 字典项 paper_origin domestic=国产 import=进口")
    private String paperOrigin;
    /**
     * 产地
     */
    @ApiModelProperty(value = "产地")
    private String produceArea;
    /**
     * 等级
     */
    @ApiModelProperty(value = "等级")
    private String paperLevel;
    /**
     * 排序优先级
     */
    @ApiModelProperty(value = "排序优先级")
    private Integer priority;
    /**
     * 状态 0=正常 1=停用
     */
    @ApiModelProperty(value = "状态 0=正常 1=停用")
    private Integer state;
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
