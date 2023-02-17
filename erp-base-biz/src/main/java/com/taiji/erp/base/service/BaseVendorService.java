

package com.taiji.erp.base.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.taiji.erp.base.api.entity.BaseVendor;
import com.taiji.erp.base.api.vo.BaseVendorVo;
import com.taiji.erp.common.core.util.R;

/**
 * 供应商配置表
 *
 * @author yangcw
 * @date 2021-05-22 16:09:12
 */
public interface BaseVendorService extends IService<BaseVendor> {

    R<?> page(Page page, BaseVendorVo baseVendorVo);

    R<?> save(BaseVendorVo baseVendorVo);

    R<?> update(BaseVendorVo baseVendorVo);

    void updatePriority(Integer source,Integer target);

    R<?> getDetailById(Integer id);
}
