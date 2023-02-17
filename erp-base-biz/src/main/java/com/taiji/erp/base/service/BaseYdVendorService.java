

package com.taiji.erp.base.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.taiji.erp.base.api.entity.BaseYdVendor;
import com.taiji.erp.base.api.vo.BaseVendorVo;
import com.taiji.erp.base.api.vo.BaseYdVendorVo;
import com.taiji.erp.common.core.util.R;

/**
 * 印点 供应商配置 表
 *
 * @author yangcw
 * @date 2021-05-22 16:09:13
 */
public interface BaseYdVendorService extends IService<BaseYdVendor> {

    R<?> page(Page page, BaseYdVendorVo baseYdVendorVo);
}
