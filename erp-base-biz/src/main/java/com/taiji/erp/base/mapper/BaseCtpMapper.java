

package com.taiji.erp.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taiji.erp.base.api.entity.BaseCtp;
import com.taiji.erp.base.api.vo.BaseCtpVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ctp 版价格及套数设置
 *
 * @author yangcw
 * @date 2021-05-31 10:43:24
 */
public interface BaseCtpMapper extends BaseMapper<BaseCtp> {

    /**
     * 根据报刊名和年月查询
     * @param page 分页
     * @param baseCtpVo 查询参数
     */
    List<BaseCtpVo> search(@Param("page")Page page,@Param("vo") BaseCtpVo baseCtpVo);

    List<BaseCtpVo> search(BaseCtpVo baseCtpVo);
}
