
package com.taiji.erp.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taiji.erp.base.api.entity.BaseBigarea;
import com.taiji.erp.base.api.vo.BaseBigAreaVo;
import com.taiji.erp.base.mapper.BaseBigareaMapper;
import com.taiji.erp.base.service.BaseBigareaService;
import com.taiji.erp.common.security.util.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * 大区设置表
 *
 * @author yangcw
 * @date 2021-05-10 16:50:10
 */
@Service
public class BaseBigareaServiceImpl extends ServiceImpl<BaseBigareaMapper, BaseBigarea> implements BaseBigareaService {

    @Autowired
    private BaseBigareaMapper baseBigareaMapper;

    @Override
    public Boolean saveByVo(BaseBigAreaVo baseBigAreaVo) {

        BaseBigarea baseBigarea = new BaseBigarea();
        BeanUtils.copyProperties(baseBigAreaVo, baseBigarea);
        baseBigarea.setCreateDate(LocalDateTime.now());
        baseBigarea.setDeleteFlag(0);
        baseBigarea.setCreateUserId(SecurityUtils.getUser().getId());
        //设置优先级
        if (Optional.ofNullable(baseBigAreaVo.getPriority()).orElse(0) == 0) {
            int total = this.count(new LambdaQueryWrapper<BaseBigarea>().eq(BaseBigarea::getDeleteFlag,0));
            baseBigarea.setPriority(total + 1);
        }else {
            this.updatePriority(null,baseBigarea.getPriority());
        }

        return this.save(baseBigarea);

    }

    @Override
    public Boolean updateByVo(BaseBigAreaVo baseBigAreaVo) {

        QueryWrapper<BaseBigarea> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", baseBigAreaVo.getId())
                .eq("delete_flag", 0);
        BaseBigarea baseBigarea = this.getOne(queryWrapper);
        baseBigarea.setPriority(baseBigAreaVo.getPriority());
        baseBigarea.setBigareaCode(baseBigAreaVo.getBigareaCode());
        baseBigarea.setBigareaName(baseBigAreaVo.getBigareaName());

        return this.updateById(baseBigarea);
    }

    @Override
    public void updatePriority(Integer source, Integer target) {
        baseBigareaMapper.updatePriority(source, target);
    }


}
