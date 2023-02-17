

package com.taiji.erp.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.taiji.erp.base.api.entity.BasePaper;
import org.apache.ibatis.annotations.Param;

/**
 * 纸张编码 表
 *
 * @author yangcw
 * @date 2021-05-22 16:09:13
 */
public interface BasePaperMapper extends BaseMapper<BasePaper> {

    void updatePriority(@Param("source") Integer source,@Param("target") Integer target);
}
