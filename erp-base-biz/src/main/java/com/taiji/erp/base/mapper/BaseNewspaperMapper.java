

package com.taiji.erp.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taiji.erp.base.api.entity.BaseNewspaper;
import com.taiji.erp.base.api.vo.BaseNewspaperVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 报刊 配置 表
 *
 * @author yangcw
 * @date 2021-05-18 10:13:07
 */
public interface BaseNewspaperMapper extends BaseMapper<BaseNewspaper> {

    void updatePriority(@Param(value = "source") Integer source,@Param(value = "target") Integer target);

    List<BaseNewspaperVo> list(@Param(value = "page") Page page,@Param(value = "vo") BaseNewspaperVo baseNewspaperVo);
}
