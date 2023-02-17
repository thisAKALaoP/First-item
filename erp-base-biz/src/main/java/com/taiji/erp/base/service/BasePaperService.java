

package com.taiji.erp.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.taiji.erp.base.api.entity.BasePaper;
import com.taiji.erp.base.api.vo.BasePaperVo;
import com.taiji.erp.common.core.util.R;

/**
 * 纸张编码 表
 *
 * @author yangcw
 * @date 2021-05-22 16:09:13
 */
public interface BasePaperService extends IService<BasePaper> {

    R<?> save(BasePaperVo basePaperVo);

    R<?> update(BasePaperVo basePaperVo);

    void updatePriority(Integer source,Integer target);
}
