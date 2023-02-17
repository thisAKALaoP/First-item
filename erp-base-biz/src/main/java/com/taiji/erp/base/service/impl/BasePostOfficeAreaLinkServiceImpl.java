
package com.taiji.erp.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taiji.erp.base.api.entity.BasePostOfficeAreaLink;
import com.taiji.erp.base.api.vo.AreaLinkVo;
import com.taiji.erp.base.api.vo.PostOfficeAreaLinkVo;
import com.taiji.erp.base.mapper.BasePostOfficeAreaLinkMapper;
import com.taiji.erp.base.service.BasePostOfficeAreaLinkService;
import com.taiji.erp.common.core.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 邮局 地区 关联关系表
 *
 * @author yangcw
 * @date 2021-05-13 16:31:57
 */
@Service
public class BasePostOfficeAreaLinkServiceImpl extends ServiceImpl<BasePostOfficeAreaLinkMapper, BasePostOfficeAreaLink> implements BasePostOfficeAreaLinkService {

    @Autowired
    private BasePostOfficeAreaLinkMapper basePostOfficeAreaLinkMapper;

    @Override
    public List<AreaLinkVo> listAll(Integer officeId) {

        return basePostOfficeAreaLinkMapper.listAll(officeId);
    }

    @Override
    public R<?> save(PostOfficeAreaLinkVo postOfficeAreaLinkVo) {

        for(Integer areaId: postOfficeAreaLinkVo.getAreaList()){
            BasePostOfficeAreaLink officeAreaLink=new BasePostOfficeAreaLink();
            officeAreaLink.setPostOfficeId(postOfficeAreaLinkVo.getOfficeId());
            officeAreaLink.setAreaId(areaId);
            this.save(officeAreaLink);
        }
        return R.ok();
    }

    @Override
    public R<?> delete(PostOfficeAreaLinkVo postOfficeAreaLinkVo) {

        QueryWrapper<BasePostOfficeAreaLink> queryWrapper=new QueryWrapper<>();

        queryWrapper.eq("post_office_id",postOfficeAreaLinkVo.getOfficeId())
                .in("area_id",postOfficeAreaLinkVo.getAreaList());

        return R.ok(this.remove(queryWrapper));

    }


}
