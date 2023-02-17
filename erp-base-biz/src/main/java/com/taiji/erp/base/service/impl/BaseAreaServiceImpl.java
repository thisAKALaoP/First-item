
package com.taiji.erp.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taiji.erp.base.api.entity.BaseArea;
import com.taiji.erp.base.api.vo.BaseAreaVo;
import com.taiji.erp.base.mapper.BaseAreaMapper;
import com.taiji.erp.base.service.BaseAreaService;
import com.taiji.erp.common.core.util.R;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

/**
 * 区域表
 *
 * @author yangcw
 * @date 2021-05-10 16:50:10
 */
@Service
public class BaseAreaServiceImpl extends ServiceImpl<BaseAreaMapper, BaseArea> implements BaseAreaService {

    @Autowired
    private BaseAreaMapper baseAreaMapper;

    @Override
    public Boolean updateByVo(BaseAreaVo baseAreaVo) {

        BaseArea baseArea = new BaseArea();
        BeanUtils.copyProperties(baseAreaVo, baseArea);
        baseArea.setDeleteFlag(0);
        BaseArea sourceArea = baseAreaMapper.selectById(baseAreaVo.getId());
        //再更新实体类
        return this.updateById(baseArea);
    }

    @Override
    public void updatePriority(Integer source, Integer target) {
        baseAreaMapper.updatePriority(source, target);
    }

    @Override
    public Boolean saveByVo(BaseAreaVo baseAreaVo) {

        if (Optional.ofNullable(baseAreaVo.getPriority()).orElse(0) == 0) {
            QueryWrapper<BaseArea> countQuery = new QueryWrapper<>();
            if (baseAreaVo.getParentId() == null) {
                countQuery.isNull("parent_id");
            } else {
                countQuery.eq("parent_id", baseAreaVo.getParentId());
            }
            countQuery.eq("delete_flag",0);
            int total = this.count(countQuery);
            baseAreaVo.setPriority(total + 1);
        }else {
            this.updatePriority(null,baseAreaVo.getPriority());
        }
        //新增
        BaseArea baseArea = new BaseArea();
        BeanUtils.copyProperties(baseAreaVo, baseArea);
        baseArea.setDeleteFlag(0);

        return this.save(baseArea);
    }

    @Override
    public R<?> getDetailById(Integer id) {
        return R.ok(baseAreaMapper.getDetailById(id));
    }

    @Override
    public R<?> getChinaProvinceList() {
        return R.ok(baseAreaMapper.getChinaProvinceList());
    }


}
