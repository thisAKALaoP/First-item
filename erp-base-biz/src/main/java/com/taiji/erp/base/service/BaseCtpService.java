

package com.taiji.erp.base.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.taiji.erp.base.api.entity.BaseCtp;
import com.taiji.erp.base.api.vo.BaseCtpVo;
import com.taiji.erp.common.core.util.R;

/**
 * ctp 版价格及套数设置
 *
 * @author yangcw
 * @date 2021-05-31 10:43:24
 */
public interface BaseCtpService extends IService<BaseCtp> {

    R<?> page(Page page, BaseCtpVo baseCtpVo);
}
