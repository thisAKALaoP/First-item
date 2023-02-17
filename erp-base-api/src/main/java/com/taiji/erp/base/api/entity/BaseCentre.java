

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
 * 中央各部门设置表
 *
 * @author yangcw
 * @date 2021-05-10 16:50:10
 */
@Data
@TableName("base_centre")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "中央各部门设置表")
public class BaseCentre extends Model<BaseCentre> {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId
    @ApiModelProperty(value = "")
    private Integer id;
    /**
     * 单位代码
     */
    @ApiModelProperty(value = "单位代码")
    private String centreCode;
    /**
     * 单位名称
     */
    @ApiModelProperty(value = "单位名称")
    private String centreName;
    /**
     * 单位类别 字典项 centre_type   zygjjggw=中央国家机关工委
     */
    @ApiModelProperty(value = "单位类别 字典项 centre_type   zygjjggw=中央国家机关工委")
    private String centreType;
    /**
     * 负责人
     */
    @ApiModelProperty(value = "负责人")
    private String centrePrincipal;
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
