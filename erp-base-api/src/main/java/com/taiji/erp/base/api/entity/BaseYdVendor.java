

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
 * 印点 供应商配置 表
 *
 * @author yangcw
 * @date 2021-05-22 16:09:13
 */
@Data
@TableName("base_yd_vendor")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "印点 供应商配置 表")
public class BaseYdVendor extends Model<BaseYdVendor> {
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
     * 供应商id
     */
    @ApiModelProperty(value = "供应商id")
    private Integer vendorId;
    /**
     * 运输方式id
     */
    @ApiModelProperty(value = "运输方式id")
    private Integer transportId;
    /**
     * 收货人
     */
    @ApiModelProperty(value = "收货人")
    private String receiver;
    /**
     * 联系电话
     */
    @ApiModelProperty(value = "联系电话")
    private String linkPhone;
    /**
     * 收货地点
     */
    @ApiModelProperty(value = "收货地点")
    private String receivePlace;

    /**
     * 优先级
     */
//    @ApiModelProperty(value = "优先级")
//    private Integer priority;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String receiveDesc;
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
