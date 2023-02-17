

package com.taiji.erp.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.taiji.erp.base.api.entity.BaseYdLinkPerson;
import com.taiji.erp.base.api.vo.YdLinkPersonVo;

/**
 * 印点联系人 配置 表
 *
 * @author yangcw
 * @date 2021-05-22 16:09:13
 */
public interface BaseYdLinkPersonService extends IService<BaseYdLinkPerson> {

    YdLinkPersonVo findDeptLinkPerson(Integer ydId);

}
