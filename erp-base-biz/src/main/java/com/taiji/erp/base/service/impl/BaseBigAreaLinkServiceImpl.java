
package com.taiji.erp.base.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taiji.erp.base.api.vo.AreaLinkVo;
import com.taiji.erp.base.api.entity.BaseBigAreaLink;
import com.taiji.erp.base.mapper.BaseBigAreaLinkMapper;
import com.taiji.erp.base.service.BaseBigAreaLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 大区 与 地区 关联关系表
 *
 * @author yangcw
 * @date 2021-05-10 16:50:10
 */
@Service
public class BaseBigAreaLinkServiceImpl extends ServiceImpl<BaseBigAreaLinkMapper, BaseBigAreaLink> implements BaseBigAreaLinkService {

    @Autowired
    private BaseBigAreaLinkMapper baseBigAreaLinkMapper;

    @Override
    public List<AreaLinkVo> findAreaList(Integer bigAreaId) {
        return baseBigAreaLinkMapper.findAreaList(bigAreaId);
    }
}
