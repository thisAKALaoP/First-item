

package com.taiji.erp.base.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.taiji.erp.base.api.entity.BasePostOffice;
import com.taiji.erp.base.api.vo.BasePostOfficeVo;
import com.taiji.erp.common.core.util.R;

/**
 * 报刊发行局 
 *
 * @author yangcw
 * @date 2021-05-13 16:31:57
 */
public interface BasePostOfficeService extends IService<BasePostOffice> {

    R<?> getDetailById(Integer id);

    R<?> save(BasePostOfficeVo basePostOfficeVo);

    R<?> update(BasePostOfficeVo basePostOfficeVo);

    R<?> page(Page page,BasePostOfficeVo basePostOfficeVo);

    void updatePriority(Integer source,Integer target);
}
