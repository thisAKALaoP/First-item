

package com.taiji.erp.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.taiji.erp.base.api.entity.BaseNewspaperOffice;
import com.taiji.erp.base.api.vo.BaseNewspaperOfficeVo;
import com.taiji.erp.base.api.vo.BaseNewspaperVo;
import com.taiji.erp.base.api.vo.BasePostOfficeVo;
import com.taiji.erp.common.core.util.R;

/**
 * 报社 设置表
 *
 * @author yangcw
 * @date 2021-05-18 10:13:07
 */
public interface BaseNewspaperOfficeService extends IService<BaseNewspaperOffice> {


    R<?> save(BaseNewspaperOfficeVo baseNewspaperOfficeVo);

    R<?> update(BaseNewspaperOfficeVo baseNewspaperOfficeVo);

}
