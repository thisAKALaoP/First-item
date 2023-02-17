

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
 * 报刊 配置 表
 *
 * @author yangcw
 * @date 2021-05-18 10:13:07
 */
@Data
@TableName("base_newspaper")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "报刊 配置 表")
public class BaseNewspaper extends Model<BaseNewspaper> {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId
    @ApiModelProperty(value = "")
    private Integer id;
    /**
     *
     */
    @ApiModelProperty(value = "")
    private String newspaperCode;
    /**
     * 报刊名称
     */
    @ApiModelProperty(value = "报刊名称")
    private String newspaperName;
    /**
     * 所属报社id
     */
    @ApiModelProperty(value = "所属报社id")
    private Integer newspaperOfficeId;
    /**
     * 发行周期 字典项  release_period    daily=日报  monthly=月刊
     */
    @ApiModelProperty(value = "发行周期 字典项  release_period    daily=日报  monthly=月刊")
    private String releasePeriod;
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
