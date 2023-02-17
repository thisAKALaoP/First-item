

package com.taiji.erp.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.taiji.erp.base.api.vo.AreaLinkVo;
import com.taiji.erp.base.api.entity.BaseBigAreaLink;

import java.util.List;

/**
 * 大区 与 地区 关联关系表
 *
 * @author yangcw
 * @date 2021-05-10 16:50:10
 */
public interface BaseBigAreaLinkService extends IService<BaseBigAreaLink> {

    /**
     * 根据大区id查找区域列表
     * @param bigAreaId 大区id
     * @return 表，包含数据{id->区域id,name->区域名}
     */
    List<AreaLinkVo> findAreaList(Integer bigAreaId);
}
