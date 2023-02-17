package com.taiji.erp.base.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "印点联系人")
public class YdLinkPersonVo {

    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "印点部门id")
    private Integer ydDeptId;

    @ApiModelProperty(value = "部门名称")
    private String deptName;

    @ApiModelProperty(value = "负责人")
    private String chargePerson;

    @ApiModelProperty(value = "座机")
    private String telephone;

    @ApiModelProperty(value = "手机")
    private String mobile;

    @ApiModelProperty(value = "隶属  dept_belong 字典项 people=报社 factory=印厂")
    private String deptBelong;
}
