

package com.taiji.erp.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taiji.erp.base.api.entity.BasePrintPrice;
import com.taiji.erp.base.api.vo.BasePrintPriceVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 印刷单价 配置表
 *
 * @author yangcw
 * @date 2021-05-31 10:43:24
 */
public interface BasePrintPriceMapper extends BaseMapper<BasePrintPrice> {

    List<BasePrintPriceVo> search(@Param("page")Page page,@Param("vo")BasePrintPriceVo printPriceVo);

    List<BasePrintPriceVo> search(@Param("vo")BasePrintPriceVo printPriceVo);
}
