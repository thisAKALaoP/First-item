

package com.taiji.erp.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taiji.erp.base.api.entity.BaseYdVendor;
import com.taiji.erp.base.api.vo.BaseYdVendorVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 印点 供应商配置 表
 *
 * @author yangcw
 * @date 2021-05-22 16:09:13
 */
public interface BaseYdVendorMapper extends BaseMapper<BaseYdVendor> {


    List<BaseYdVendorVo> pageBaseYdVendorVo(@Param("page")Page page,@Param("vo") BaseYdVendorVo baseYdVendorVo);
}
