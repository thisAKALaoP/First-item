

package com.taiji.erp.base.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.taiji.erp.base.api.entity.BasePrintPrice;
import com.taiji.erp.base.api.vo.BasePrintPriceVo;
import com.taiji.erp.common.core.util.R;

/**
 * 印刷单价 配置表
 *
 * @author yangcw
 * @date 2021-05-31 10:43:24
 */
public interface BasePrintPriceService extends IService<BasePrintPrice> {

    R<?> page(Page page, BasePrintPriceVo priceVo);
}
