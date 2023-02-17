
package com.taiji.erp.base.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taiji.erp.base.api.entity.BaseYdLinkPerson;
import com.taiji.erp.base.api.vo.YdLinkPersonVo;
import com.taiji.erp.base.mapper.BaseYdLinkPersonMapper;
import com.taiji.erp.base.service.BaseYdLinkPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 印点联系人 配置 表
 *
 * @author yangcw
 * @date 2021-05-22 16:09:13
 */
@Service
public class BaseYdLinkPersonServiceImpl extends ServiceImpl<BaseYdLinkPersonMapper, BaseYdLinkPerson> implements BaseYdLinkPersonService {

    @Autowired
    private BaseYdLinkPersonMapper baseYdLinkPersonMapper;



    @Override
    public YdLinkPersonVo findDeptLinkPerson(Integer ydId) {
        return baseYdLinkPersonMapper.findDeptLinkPerson(ydId);
    }


}
