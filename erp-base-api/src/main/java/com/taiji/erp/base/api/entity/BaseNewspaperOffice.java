

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
 * 报社 设置表
 *
 * @author yangcw
 * @date 2021-05-18 10:13:07
 */
@Data
@TableName("base_newspaper_office")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "报社 设置表")
public class BaseNewspaperOffice extends Model<BaseNewspaperOffice> {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId
    @ApiModelProperty(value = "")
    private Integer id;
    /**
     * 报社代码
     */
    @ApiModelProperty(value = "报社代码")
    private String officeCode;
    /**
     * 报社名称
     */
    @ApiModelProperty(value = "报社名称")
    private String officeName;
    /**
     * 报社地址
     */
    @ApiModelProperty(value = "报社地址")
    private String officeAddress;
    /**
     *
     */
    @ApiModelProperty(value = "报社邮编")
    private String postCode;
    /**
     * 联系电话
     */
    @ApiModelProperty(value = "联系电话")
    private String linkPhone;
    /**
     * 联系人
     */
    @ApiModelProperty(value = "联系人")
    private String linkPerson;
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
