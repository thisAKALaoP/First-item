

package com.taiji.erp.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.taiji.erp.base.api.entity.BaseYd;
import org.apache.ibatis.annotations.Param;

/**
 * 印点 基本信息 配置表
 *
 * @author yangcw
 * @date 2021-05-22 16:09:13
 */
public interface BaseYdMapper extends BaseMapper<BaseYd> {

    void updatePriority(@Param(value = "source")Integer source,@Param(value = "target") Integer target);
}
