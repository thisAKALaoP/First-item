

package com.taiji.erp.base.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * 大区设置表
 *
 * @author yangcw
 * @date 2021-05-10 16:50:10
 */
@Data
@TableName("base_bigarea")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "大区设置表 ")
public class BaseBigarea extends Model<BaseBigarea> {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "")
    private Integer id;
    /**
     * 大区代码
     */
    @ApiModelProperty(value = "大区代码")
    private String bigareaCode;
    /**
     * 大区名称
     */
    @ApiModelProperty(value = "大区名称")
    private String bigareaName;
    /**
     * 排序优先级
     */
    @ApiModelProperty(value = "排序优先级")
    private Integer priority;
    /**
     * 删除标识 1=删除
     */
    @ApiModelProperty(value = "删除标识 1=删除")
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
