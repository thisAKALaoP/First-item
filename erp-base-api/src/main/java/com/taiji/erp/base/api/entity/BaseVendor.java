

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
 * 供应商配置表
 *
 * @author yangcw
 * @date 2021-05-22 16:09:12
 */
@Data
@TableName("base_vendor")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "供应商配置表")
public class BaseVendor extends Model<BaseVendor> {
    private static final long serialVersionUID = 1L;

    /**
     * 供应商 基本信息配置表
     */
    @TableId(type = IdType.INPUT)
    @ApiModelProperty(value = "供应商 基本信息配置表")
    private Integer id;
    /**
     * 联系人
     */
    @ApiModelProperty(value = "联系人")
    private String linkPerson;
    /**
     *
     */
    @ApiModelProperty(value = "")
    private String linkPhone;
    /**
     * 税号
     */
    @ApiModelProperty(value = "税号")
    private String taxCode;
    /**
     *
     */
    @ApiModelProperty(value = "")
    private String chargePerson;
    /**
     * 负责人电话
     */
    @ApiModelProperty(value = "负责人电话")
    private String chargePhone;
    /**
     * 开户行
     */
    @ApiModelProperty(value = "开户行")
    private String bankName;
    /**
     * 银行账号
     */
    @ApiModelProperty(value = "银行账号")
    private String bankAccount;
    /**
     * 详细地址
     */
    @ApiModelProperty(value = "详细地址")
    private String address;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String vendorDesc;

    @ApiModelProperty(value = "优先级")
    private Integer priority;
    /**
     * 删除标识 0=正常 1=删除
     */
    @ApiModelProperty(value = "删除标识 0=正常 1=删除")
    private Integer deleteFlag;

    @ApiModelProperty(value = "状态 0-正常 1-禁用")
    private Integer state;
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
