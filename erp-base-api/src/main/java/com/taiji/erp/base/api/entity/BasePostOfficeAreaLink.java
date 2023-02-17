

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
 * 邮局 地区 关联关系表
 *
 * @author yangcw
 * @date 2021-05-13 16:31:57
 */
@Data
@TableName("base_post_office_area_link")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "邮局 地区 关联关系表")
public class BasePostOfficeAreaLink extends Model<BasePostOfficeAreaLink> {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId
    @ApiModelProperty(value = "")
    private Integer id;
    /**
     * 邮局id
     */
    @ApiModelProperty(value = "邮局id")
    private Integer postOfficeId;
    /**
     * 地区id
     */
    @ApiModelProperty(value = "地区id")
    private Integer areaId;
}
