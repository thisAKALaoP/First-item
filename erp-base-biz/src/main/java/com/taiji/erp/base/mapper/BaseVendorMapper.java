

package com.taiji.erp.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.taiji.erp.base.api.entity.BaseVendor;
import org.apache.ibatis.annotations.Param;

/**
 * 供应商配置表
 *
 * @author yangcw
 * @date 2021-05-22 16:09:12
 */
public interface BaseVendorMapper extends BaseMapper<BaseVendor> {

    void updatePriority(@Param("source") Integer source,@Param("target") Integer target);
}
