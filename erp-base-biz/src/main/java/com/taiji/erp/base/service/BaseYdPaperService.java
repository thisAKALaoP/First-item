

package com.taiji.erp.base.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.taiji.erp.base.api.entity.BaseYdPaper;
import com.taiji.erp.base.api.vo.BaseYdPaperVo;
import com.taiji.erp.common.core.util.R;

/**
 * 印点 纸张 配置表
 *
 * @author yangcw
 * @date 2021-05-22 16:09:13
 */
public interface BaseYdPaperService extends IService<BaseYdPaper> {

    R<?> page(Page page, BaseYdPaperVo baseYdPaperVo);
}
