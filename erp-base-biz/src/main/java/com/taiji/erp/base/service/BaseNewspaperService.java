

package com.taiji.erp.base.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.taiji.erp.base.api.entity.BaseNewspaper;
import com.taiji.erp.base.api.vo.BaseNewspaperVo;
import com.taiji.erp.common.core.util.R;

/**
 * 报刊 配置 表
 *
 * @author yangcw
 * @date 2021-05-18 10:13:07
 */
public interface BaseNewspaperService extends IService<BaseNewspaper> {

    R<?> save(BaseNewspaperVo baseNewspaperVo);

    R<?> update(BaseNewspaperVo baseNewspaperVo);

    R<?> list(Page page,BaseNewspaperVo baseNewspaperVo);

    void updatePriority(Integer source,Integer target);
}
