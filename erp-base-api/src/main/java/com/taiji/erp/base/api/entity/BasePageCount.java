

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
 * 版数 设置
 *
 * @author yangcw
 * @date 2021-05-27 16:45:48
 */
@Data
@TableName("base_page_count")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "版数 设置")
public class BasePageCount extends Model<BasePageCount> {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId
    @ApiModelProperty(value = "")
    private Integer id;
    /**
     * 报刊id
     */
    @ApiModelProperty(value = "报刊id")
    private Integer newspaperId;
    /**
     * 印点id
     */
    @ApiModelProperty(value = "印点id")
    private Integer ydId;
    /**
     * 周   1   2  3  4  5  6  7
     */
    @ApiModelProperty(value = "周   1   2  3  4  5  6  7 ")
    private Integer weekDay;
    /**
     * 出版日期  (有出版日期的优先取出版日期的版数)
     */
    @ApiModelProperty(value = "出版日期  (有出版日期的优先取出版日期的版数)")
    private LocalDateTime publishDate;
    /**
     * 版数
     */
    @ApiModelProperty(value = "版数")
    private Integer pageCount;
    /**
     * 增版情况 字典项 add_page  temporary=临时增版
     */
    @ApiModelProperty(value = "增版情况 字典项 add_page  temporary=临时增版")
    private String addPage;
    /**
     * 刊期类型 字典项journal_type  normal=正常  stop=停刊  add=增刊  conjunction=合刊
     */
    @ApiModelProperty(value = "刊期类型 字典项journal_type  normal=正常  stop=停刊  add=增刊  conjunction=合刊")
    private String journalType;
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
