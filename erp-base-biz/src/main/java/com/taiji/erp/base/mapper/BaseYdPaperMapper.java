

package com.taiji.erp.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taiji.erp.base.api.entity.BaseYdPaper;
import com.taiji.erp.base.api.vo.BaseYdPaperVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 印点 纸张 配置表
 *
 * @author yangcw
 * @date 2021-05-22 16:09:13
 */
public interface BaseYdPaperMapper extends BaseMapper<BaseYdPaper> {


    List<BaseYdPaperVo> pageBaseYdPaper(@Param("page") Page page, @Param("vo") BaseYdPaperVo baseYdPaperVo);
}
