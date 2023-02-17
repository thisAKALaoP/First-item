

package com.taiji.erp.base.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.taiji.erp.base.api.entity.BaseYd;
import com.taiji.erp.base.api.vo.BaseYdVo;
import com.taiji.erp.common.core.util.R;

/**
 * 印点 基本信息 配置表
 *
 * @author yangcw
 * @date 2021-05-22 16:09:13
 */
public interface BaseYdService extends IService<BaseYd> {

    R<?> save(BaseYdVo baseYdVo);

    R<?> page(Page page,BaseYdVo baseYdVo);

    R<?> update(BaseYdVo baseYdVo);

    R<?> getDetailById(Integer id);

    void updatePriority(Integer source,Integer target);
}
