

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
 * 报刊发行局
 *
 * @author yangcw
 * @date 2021-05-13 16:31:57
 */
@Data
@TableName("base_post_office")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "报刊发行局 ")
public class BasePostOffice extends Model<BasePostOffice> {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId
    @ApiModelProperty(value = "")
    private Integer id;
    /**
     * 邮局代码
     */
    @ApiModelProperty(value = "邮局代码")
    private String postOfficeCode;
    /**
     * 邮局名称
     */
    @ApiModelProperty(value = "邮局名称")
    private String postOfficeName;
    /**
     * 联系人
     */
    @ApiModelProperty(value = "联系人")
    private String linkPerson;
    /**
     * 联系电话
     */
    @ApiModelProperty(value = "联系电话")
    private String linkPhone;
    /**
     * 所在地区
     */
    @ApiModelProperty(value = "所在地区")
    private Integer locationAreaId;
    /**
     * 要报印点id
     */
    @ApiModelProperty(value = "要报印点id")
    private Integer orgId;
    /**
     * 要报印点名称
     */
    @ApiModelProperty(value = "要报印点名称")
    private String orgName;
    /**
     * 接报时间
     */
    @ApiModelProperty(value = "接报时间")
    private LocalDateTime receiveTime;
    /**
     * 发行局类型 字典项 post_office_type     post_office=邮局
     */
    @ApiModelProperty(value = "发行局类型 字典项 post_office_type     post_office=邮局 ")
    private String postOfficeType;
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
