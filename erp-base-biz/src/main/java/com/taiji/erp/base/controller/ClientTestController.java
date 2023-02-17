package com.taiji.erp.base.controller;

import com.taiji.erp.admin.api.feign.SysOrgClient;
import com.taiji.erp.base.api.dto.PostOfficeAreaLinkerDto;
import com.taiji.erp.base.api.feign.RemoteBasePostOfficeAreaLinkService;
import com.taiji.erp.base.api.feign.RemoteBaseVendorService;
import com.taiji.erp.base.api.feign.RemoteBaseYdService;
import com.taiji.erp.common.core.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@Api(value = "用于swagger测试feign")
@RestController
public class ClientTestController {

    @Autowired
    private RemoteBaseYdService remoteBaseYdService;
    @Autowired
    private RemoteBaseVendorService remoteBaseVendorService;
    @Autowired
    private RemoteBasePostOfficeAreaLinkService remoteBasePostOfficeAreaLinkService;
    @Autowired
    private SysOrgClient sysOrgClient;

    @ApiOperation(value = "通过邮局id查询关联的邮局和关联地区",notes = "通过邮局id查询关联的邮局和关联地区")
    @GetMapping(value = "/name")
    public R name(String name){

        return sysOrgClient.selectByName(name,null,2,1,10);

    }

}
