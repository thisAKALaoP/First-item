

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
 * 印点联系人 配置 表
 *
 * @author yangcw
 * @date 2021-05-22 16:09:13
 */
@Data
@TableName("base_yd_link_person")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "印点联系人 配置 表")
public class BaseYdLinkPerson extends Model<BaseYdLinkPerson> {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId
    @ApiModelProperty(value = "")
    private Integer id;

    /**
     * 印点部门id
     */
    @ApiModelProperty(value = "印点部门id")
    private Integer ydDeptId;
    /**
     * 负责人
     */
    @ApiModelProperty(value = "负责人")
    private String chargePerson;
    /**
     * 座机
     */
    @ApiModelProperty(value = "座机")
    private String telephone;
    /**
     * 手机
     */
    @ApiModelProperty(value = "手机")
    private String mobile;
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
