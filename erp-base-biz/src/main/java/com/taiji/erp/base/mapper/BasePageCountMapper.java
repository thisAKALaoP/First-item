

package com.taiji.erp.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taiji.erp.base.api.entity.BasePageCount;
import com.taiji.erp.base.api.vo.BasePageCountVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 版数 设置
 *
 * @author yangcw
 * @date 2021-05-27 16:45:48
 */
public interface BasePageCountMapper extends BaseMapper<BasePageCount> {

    List<BasePageCountVo> aPage(@Param("page")Page page, @Param("count")BasePageCount count);
}
