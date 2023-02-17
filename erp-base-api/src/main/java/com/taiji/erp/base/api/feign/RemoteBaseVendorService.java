package com.taiji.erp.base.api.feign;

import com.taiji.erp.base.api.vo.BaseVendorVo;
import com.taiji.erp.common.core.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "erp-base",path = "/baseVendor",contextId = "baseVendor")
public interface RemoteBaseVendorService {

    /**
     * 保存供应商
     * @param baseVendorVo
     * @return
     */
    @PostMapping
    R save(@RequestBody BaseVendorVo baseVendorVo);

    /**
     * 根据组织id删除供应商
     * @param id
     * @return
     */
    @DeleteMapping("/{id}" )
    R removeById(@PathVariable(value = "id") Integer id);
}
