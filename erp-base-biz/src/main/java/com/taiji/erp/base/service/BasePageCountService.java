

package com.taiji.erp.base.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.taiji.erp.base.api.entity.BasePageCount;
import com.taiji.erp.base.api.vo.BasePageCountVo;
import com.taiji.erp.common.core.util.R;

import java.util.List;

/**
 * 版数 设置
 *
 * @author yangcw
 * @date 2021-05-27 16:45:48
 */
public interface BasePageCountService extends IService<BasePageCount> {

    List<BasePageCountVo> page(Page page, BasePageCount count);
}
