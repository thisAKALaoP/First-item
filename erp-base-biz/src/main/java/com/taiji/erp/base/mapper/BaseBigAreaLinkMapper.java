

package com.taiji.erp.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.taiji.erp.base.api.vo.AreaLinkVo;
import com.taiji.erp.base.api.entity.BaseBigAreaLink;

import java.util.List;

/**
 * 大区 与 地区 关联关系表
 *
 * @author yangcw
 * @date 2021-05-10 16:50:10
 */
public interface BaseBigAreaLinkMapper extends BaseMapper<BaseBigAreaLink> {


    List<AreaLinkVo> findAreaList(Integer id);
}
