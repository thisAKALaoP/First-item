

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
 * 运输方式配置表
 *
 * @author yangcw
 * @date 2021-05-22 16:09:13
 */
@Data
@TableName("base_transport")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "运输方式配置表")
public class BaseTransport extends Model<BaseTransport> {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId
    @ApiModelProperty(value = "")
    private Integer id;
    /**
     * 运输方式名称
     */
    @ApiModelProperty(value = "运输方式名称")
    private String transportName;
    /**
     * 标准运量
     */
    @ApiModelProperty(value = "标准运量")
    private Integer standardAmount;
    /**
     * 单位
     */
    @ApiModelProperty(value = "单位")
    private String standardUnit;
}
