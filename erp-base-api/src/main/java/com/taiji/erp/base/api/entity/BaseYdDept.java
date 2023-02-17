

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
 * 印点 部门配置表
 *
 * @author yangcw
 * @date 2021-05-22 16:09:13
 */
@Data
@TableName("base_yd_dept")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "印点 部门配置表")
public class BaseYdDept extends Model<BaseYdDept> {
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
     * 部门名称
     */
    @ApiModelProperty(value = "部门名称")
    private String deptName;
    /**
     * 部门类型 字典项 yd_dept_type use=使用部门 manage=管理部门  fill=填报部门  buy=采购部门
     */
    @ApiModelProperty(value = "部门类型 字典项 yd_dept_type use=使用部门 manage=管理部门  fill=填报部门  buy=采购部门")
    private String deptType;
    /**
     * 隶属  dept_belong 字典项 people=报社 factory=印厂
     */
    @ApiModelProperty(value = "隶属  dept_belong 字典项 people=报社 factory=印厂")
    private String deptBelong;
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
