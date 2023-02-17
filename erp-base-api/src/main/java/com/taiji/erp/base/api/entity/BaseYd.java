

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
 * 印点 基本信息 配置表
 *
 * @author yangcw
 * @date 2021-05-22 16:09:13
 */
@Data
@TableName("base_yd")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "印点 基本信息 配置表")
public class BaseYd extends Model<BaseYd> {
    private static final long serialVersionUID = 1L;

    /**
     *  印点id，非自增主键
     */
    @TableId(type = IdType.INPUT)
    @ApiModelProperty(value = "印点id")
    private Integer id;

    /**
     * 所属省或市ID，如果省市存在则为市的id
     */
    @ApiModelProperty(value = "所属省市ID")
    private Integer areaId;
    /**
     * 所属大区id
     */
    @ApiModelProperty(value = "所属大区id")
    private Integer bigareaId;
    /**
     * 联系人
     */
    @ApiModelProperty(value = "联系人")
    private String linkPerson;
    /**
     * 联系电话
     */
    @ApiModelProperty(value = "联系电话")
    private String linkPhone;
    /**
     * 传真
     */
    @ApiModelProperty(value = "传真")
    private String linkFax;
    /**
     * email
     */
    @ApiModelProperty(value = "email")
    private String linkEmail;
    /**
     * 库存最低天数
     */
    @ApiModelProperty(value = "库存最低天数")
    private Integer stockDayCount;
    /**
     * 仓库类型 字典项 warehouse_type own=自有  rent=外租
     */
    @ApiModelProperty(value = "仓库类型 字典项 warehouse_type own=自有  rent=外租 ")
    private String warehouseType;
    /**
     * 最大库存
     */
    @ApiModelProperty(value = "最大库存")
    private Integer warehouseMax;
    /**
     * 给人民最大库存
     */
    @ApiModelProperty(value = "给人民最大库存")
    private Integer warehousePeople;
    /**
     * 删除标识 0=正常 1=删除
     */
    @ApiModelProperty(value = "删除标识 0=正常 1=删除")
    private Integer deleteFlag;

    /**
     * 印点备注
     */
    @ApiModelProperty(value = "印点备注")
    private String ydDesc;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序号")
    private Integer priority;

    /**
     * 状态 0=正常 1=停用
     */
    @ApiModelProperty(value = "状态 0=正常 1=停用")
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
