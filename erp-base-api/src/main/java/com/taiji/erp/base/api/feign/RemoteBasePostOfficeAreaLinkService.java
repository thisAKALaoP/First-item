package com.taiji.erp.base.api.feign;

import com.taiji.erp.base.api.dto.PostOfficeAreaLinkerDto;
import com.taiji.erp.common.core.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "erp-base",contextId = "basePostOfficeAreaLink",path = "/basePostOfficeAreaLink")
public interface RemoteBasePostOfficeAreaLinkService {

    /**
     * 根据报刊发行局的id查询名称和关联区域
     * @param orgId 组织id
     */
    @GetMapping(value = "/findAreaLinkByPostOfficeId/{orgId}")
    R<PostOfficeAreaLinkerDto> findAreaLinkByPostOfficeId(@PathVariable(value = "orgId") Integer orgId);
}