

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
 * 大区 与 地区 关联关系表
 *
 * @author yangcw
 * @date 2021-05-10 16:50:10
 */
@Data
@TableName("base_big_area_link")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "大区 与 地区 关联关系表")
public class BaseBigAreaLink extends Model<BaseBigAreaLink> {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "")
    private Integer id;
    /**
     * 地区id
     */
    @ApiModelProperty(value = "地区id")
    private Integer areaId;
    /**
     * 大区id
     */
    @ApiModelProperty(value = "大区id")
    private Integer bigareaId;
}
