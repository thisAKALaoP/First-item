

package com.taiji.erp.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.taiji.erp.base.api.entity.BaseYdLinkPerson;
import com.taiji.erp.base.api.vo.YdLinkPersonVo;

/**
 * 印点联系人 配置 表
 *
 * @author yangcw
 * @date 2021-05-22 16:09:13
 */
public interface BaseYdLinkPersonMapper extends BaseMapper<BaseYdLinkPerson> {

    /**
     * 根据印点id查找印点部门和联系人
     * @param ydId 印点id
     */
    YdLinkPersonVo findDeptLinkPerson(Integer ydId);
}
