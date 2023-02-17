package com.taiji.erp.base.api.feign;

import com.taiji.erp.base.api.vo.BaseYdVo;
import com.taiji.erp.common.core.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "erp-base",contextId = "baseYd",path = "/baseYd")
public interface RemoteBaseYdService {

    /**
     * 保存印点
     * @param baseYdVo
     * @return
     */
    @PostMapping
    R save(@RequestBody BaseYdVo baseYdVo);

    /**
     * 根据组织id删除印点
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    R removeById(@PathVariable("id") Integer id);

}
