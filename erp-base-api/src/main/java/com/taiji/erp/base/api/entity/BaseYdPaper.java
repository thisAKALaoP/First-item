

package com.taiji.erp.base.api.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 印点 纸张 配置表
 *
 * @author yangcw
 * @date 2021-05-22 16:09:13
 */
@Data
@TableName("base_yd_paper")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "印点 纸张 配置表")
public class BaseYdPaper extends Model<BaseYdPaper> {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId
    @ApiModelProperty(value = "")
    private Integer id;
    /**
     * 印点id
     */
    @ApiModelProperty(value = "印点id")
    private Integer ydId;
    /**
     * 纸张id
     */
    @ApiModelProperty(value = "纸张id")
    private Integer paperId;
    /**
     * 配比
     */
    @ApiModelProperty(value = "配比")
    private BigDecimal useRatio;
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
