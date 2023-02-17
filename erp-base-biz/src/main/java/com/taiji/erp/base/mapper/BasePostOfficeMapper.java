

package com.taiji.erp.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.taiji.erp.base.api.entity.BasePostOffice;
import org.apache.ibatis.annotations.Param;

/**
 * 报刊发行局 
 *
 * @author yangcw
 * @date 2021-05-13 16:31:57
 */
public interface BasePostOfficeMapper extends BaseMapper<BasePostOffice> {

    void updatePriority(@Param(value = "source") Integer source,@Param(value = "target") Integer target);
}
