

package com.taiji.erp.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.taiji.erp.base.api.entity.BasePostOfficeAreaLink;
import com.taiji.erp.base.api.vo.AreaLinkVo;
import com.taiji.erp.base.api.vo.PostOfficeAreaLinkVo;
import com.taiji.erp.common.core.util.R;

import java.util.List;

/**
 * 邮局 地区 关联关系表
 *
 * @author yangcw
 * @date 2021-05-13 16:31:57
 */
public interface BasePostOfficeAreaLinkService extends IService<BasePostOfficeAreaLink> {

    /**
     * 根据邮局id查找地区列表
     * @param officeId 邮局id
     */
    List<AreaLinkVo> listAll(Integer officeId);

    R<?> save(PostOfficeAreaLinkVo postOfficeAreaLinkVo);

    R<?> delete(PostOfficeAreaLinkVo postOfficeAreaLinkVo);
}
