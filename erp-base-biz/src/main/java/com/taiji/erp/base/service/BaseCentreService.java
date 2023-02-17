

package com.taiji.erp.base.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.taiji.erp.base.api.entity.BaseCentre;
import com.taiji.erp.base.api.vo.BaseCentreVo;
import com.taiji.erp.common.core.util.R;

/**
 * 中央各部门设置表
 *
 * @author yangcw
 * @date 2021-05-10 16:50:10
 */
public interface BaseCentreService extends IService<BaseCentre> {

    R<?> save(BaseCentreVo baseCentreVo);

    R<?> update(BaseCentreVo baseCentreVo);

    R<?> delete(Integer id);

    R<?> page(Page page,BaseCentreVo baseCentreVo);
}
