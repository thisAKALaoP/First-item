
package com.taiji.erp.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taiji.erp.base.api.entity.BaseNewspaperOffice;
import com.taiji.erp.base.api.vo.BaseNewspaperOfficeVo;
import com.taiji.erp.base.api.vo.BaseNewspaperVo;
import com.taiji.erp.base.mapper.BaseNewspaperOfficeMapper;
import com.taiji.erp.base.service.BaseNewspaperOfficeService;
import com.taiji.erp.common.core.util.R;
import com.taiji.erp.common.security.util.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * 报社 设置表
 *
 * @author yangcw
 * @date 2021-05-18 10:13:07
 */
@Service
public class BaseNewspaperOfficeServiceImpl extends ServiceImpl<BaseNewspaperOfficeMapper, BaseNewspaperOffice> implements BaseNewspaperOfficeService {

    @Override
    public R<?> save(BaseNewspaperOfficeVo baseNewspaperOfficeVo) {

        QueryWrapper<BaseNewspaperOffice> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("office_name",baseNewspaperOfficeVo.getOfficeName())
                .or()
                .eq("office_code",baseNewspaperOfficeVo.getOfficeCode());
        if(this.count(queryWrapper)>0){
            return R.failed("报社代码或名称已存在");
        }
        BaseNewspaperOffice office=new BaseNewspaperOffice();
        BeanUtils.copyProperties(baseNewspaperOfficeVo,office);
        //设置状态删除标志
        office.setCreateDate(LocalDateTime.now());
        office.setDeleteFlag(0);
        office.setCreateUserId(SecurityUtils.getUser().getId());

        return R.ok(this.save(office));
    }

    @Override
    public R<?> update(BaseNewspaperOfficeVo baseNewspaperOfficeVo) {

        QueryWrapper<BaseNewspaperOffice> queryWrapper=new QueryWrapper<>();
        queryWrapper.ne("id",baseNewspaperOfficeVo.getId())
                .and(true,wrapper->{
                    wrapper.eq("office_name",baseNewspaperOfficeVo.getOfficeName())
                            .or()
                            .eq("office_code",baseNewspaperOfficeVo.getOfficeCode());
                });
        if(this.count(queryWrapper)>0){
            return R.failed("报社代码或名称已存在");
        }
        BaseNewspaperOffice office=new BaseNewspaperOffice();
        BeanUtils.copyProperties(baseNewspaperOfficeVo,office);

        return R.ok(this.updateById(office));
    }
}
