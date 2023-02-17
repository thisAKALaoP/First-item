

package com.taiji.erp.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.taiji.erp.base.api.entity.BaseBigarea;
import com.taiji.erp.base.api.vo.BaseBigAreaVo;

/**
 * 大区设置表 
 *
 * @author yangcw
 * @date 2021-05-10 16:50:10
 */
public interface BaseBigareaService extends IService<BaseBigarea> {

    Boolean saveByVo(BaseBigAreaVo baseBigAreaVo);

    Boolean updateByVo(BaseBigAreaVo baseBigAreaVo);

    void updatePriority(Integer source, Integer target);
}
