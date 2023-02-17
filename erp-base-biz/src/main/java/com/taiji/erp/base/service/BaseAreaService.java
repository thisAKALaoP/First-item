

package com.taiji.erp.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.taiji.erp.base.api.entity.BaseArea;
import com.taiji.erp.base.api.vo.BaseAreaVo;
import com.taiji.erp.common.core.util.R;

/**
 * 区域表
 *
 * @author yangcw
 * @date 2021-05-10 16:50:10
 */
public interface BaseAreaService extends IService<BaseArea> {

    Boolean updateByVo(BaseAreaVo baseAreaVo);

    /**
     * 仅当新增和删除时更新排序优先级
     * @param source 原排序优先级，null代表新增
     * @param target 目标排序优先级，null代表删除
     */
    void updatePriority(Integer source,Integer target);

    Boolean saveByVo(BaseAreaVo baseAreaVo);

    R<?> getDetailById(Integer id);

    R<?> getChinaProvinceList();
}
