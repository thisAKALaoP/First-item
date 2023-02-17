

package com.taiji.erp.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.taiji.erp.base.api.vo.AreaLinkVo;
import com.taiji.erp.base.api.entity.BasePostOfficeAreaLink;

import java.util.List;

/**
 * 邮局 地区 关联关系表
 *
 * @author yangcw
 * @date 2021-05-13 16:31:57
 */
public interface BasePostOfficeAreaLinkMapper extends BaseMapper<BasePostOfficeAreaLink> {

    List<AreaLinkVo> listAll(Integer officeId);
}
