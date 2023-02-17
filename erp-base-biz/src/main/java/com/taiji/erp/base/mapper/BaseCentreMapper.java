

package com.taiji.erp.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.taiji.erp.base.api.entity.BaseCentre;
import org.apache.ibatis.annotations.Param;

/**
 * 中央各部门设置表
 *
 * @author yangcw
 * @date 2021-05-10 16:50:10
 */
public interface BaseCentreMapper extends BaseMapper<BaseCentre> {

    void updatePriority(@Param("source") Integer source,@Param("target") Integer target);
}
