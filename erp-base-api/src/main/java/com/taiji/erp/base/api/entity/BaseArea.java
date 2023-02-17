

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
 * 区域表
 *
 * @author yangcw
 * @date 2021-05-10 16:50:10
 */
@Data
@TableName("base_area")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "区域表")
public class BaseArea extends Model<BaseArea> {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "")
    private Integer id;
    /**
     *
     */
    @ApiModelProperty(value = "")
    private String areaName;
    /**
     * 这个没有用，请用areaName代替
     */
    @ApiModelProperty(value = "")
    private String areaAbbr;
    /**
     *
     */
    @ApiModelProperty(value = "")
    private Integer parentId;
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
}
