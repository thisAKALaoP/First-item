

package com.taiji.erp.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.taiji.erp.base.api.entity.BaseBigarea;
import org.apache.ibatis.annotations.Param;

/**
 * 大区设置表 
 *
 * @author yangcw
 * @date 2021-05-10 16:50:10
 */
public interface BaseBigareaMapper extends BaseMapper<BaseBigarea> {

    void updatePriority(@Param("source") Integer source,@Param("target") Integer target);
}
