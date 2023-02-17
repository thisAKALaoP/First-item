
package com.taiji.erp.base.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taiji.erp.admin.api.entity.SysDictItem;
import com.taiji.erp.admin.api.feign.SysDictClient;
import com.taiji.erp.base.api.entity.BaseCentre;
import com.taiji.erp.base.api.vo.BaseCentreVo;
import com.taiji.erp.base.mapper.BaseCentreMapper;
import com.taiji.erp.base.service.BaseCentreService;
import com.taiji.erp.common.core.constant.SecurityConstants;
import com.taiji.erp.common.core.util.R;
import com.taiji.erp.common.security.util.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 中央各部门设置表
 *
 * @author yangcw
 * @date 2021-05-10 16:50:10
 */
@Service
public class BaseCentreServiceImpl extends ServiceImpl<BaseCentreMapper, BaseCentre> implements BaseCentreService {

    @Autowired
    private BaseCentreMapper baseCentreMapper;
    @Autowired
    private SysDictClient sysDictClient;
    @Value("${erp-base.baseCentre.centreType}")
    private String centreType;

    @Override
    public R<?> save(BaseCentreVo baseCentreVo) {

        QueryWrapper<BaseCentre> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("centre_code", baseCentreVo.getCentreCode())
                .or()
                .eq("centre_name", baseCentreVo.getCentreName());
        if (this.count(queryWrapper) > 0) {
            return R.failed("单位代码或名称已存在");
        }
        BaseCentre baseCentre = new BaseCentre();
        BeanUtils.copyProperties(baseCentreVo, baseCentre);
        //设置其他信息
        baseCentre.setCreateDate(LocalDateTime.now());
        baseCentre.setCreateUserId(SecurityUtils.getUser().getId());
        baseCentre.setDeleteFlag(0);
        //设置优先级
        if (Optional.ofNullable(baseCentre.getPriority()).orElse(0) == 0) {
            int total = this.count(new LambdaQueryWrapper<BaseCentre>().eq(BaseCentre::getDeleteFlag,0));
            baseCentre.setPriority(total + 1);
        }else {
            this.updatePriority(null,baseCentre.getPriority());
        }
    git
        return R.ok(this.save(baseCentre));
    }

    @Override
    public R<?> update(BaseCentreVo baseCentreVo) {
        QueryWrapper<BaseCentre> queryWrapper = new QueryWrapper<>();
        queryWrapper.ne("id", baseCentreVo.getId()).and(true, (baseCentreQueryWrapper) -> {
            baseCentreQueryWrapper.eq("centre_code", baseCentreVo.getCentreCode())
                    .or()
                    .eq("centre_name", baseCentreVo.getCentreName());
        });
        if (this.count(queryWrapper) > 0) {
            return R.failed("单位代码或名称已存在");
        }
        BaseCentre baseCentre = this.getById(baseCentreVo.getId());
        if (baseCentre == null) {
            return R.failed("单位不存在");
        }
        baseCentre.setPriority(baseCentreVo.getPriority());
        baseCentre.setCentreCode(baseCentreVo.getCentreCode());
        baseCentre.setCentreName(baseCentreVo.getCentreName());
        baseCentre.setCentrePrincipal(baseCentreVo.getCentrePrincipal());
        baseCentre.setCentreType(baseCentreVo.getCentreType());

        return R.ok(this.updateById(baseCentre));
    }

    @Override
    public R delete(Integer id) {
        BaseCentre baseCentre = this.getById(id);
        if (baseCentre == null) {
            return R.failed("单位不存在");
        }
        baseCentre.setDeleteFlag(1);
        this.updatePriority(baseCentre.getPriority(),null);
        return R.ok(this.updateById(baseCentre));
    }

    @Override
    public R<?> page(Page page, BaseCentreVo baseCentreVo) {
        BaseCentre baseCentre = new BaseCentre();
        BeanUtils.copyProperties(baseCentreVo, baseCentre);
        baseCentre.setDeleteFlag(0);
        //获取分页后的数据
        IPage iPage = this.page(page, Wrappers.query(baseCentre).orderByAsc("priority"));
        List<BaseCentre> baseCentres = iPage.getRecords();
        List<BaseCentreVo> voList = new ArrayList<>();
        //获取字典项
        R<List<SysDictItem>> r = sysDictClient.getDictByType(centreType);
        List<SysDictItem> itemList = JSON.parseArray(JSON.toJSONString(r.getData()), SysDictItem.class);
        baseCentres.stream().forEach(centre -> {
            if (!StringUtils.isEmpty(centre.getCentreType())) {
                BaseCentreVo centreVo = new BaseCentreVo();
                BeanUtils.copyProperties(centre, centreVo);
                //对字典项进行匹配
                centreVo.setCentreTypeName(itemList.parallelStream().filter(
                        item -> {
                            return centreVo.getCentreType().equals(item.getType());
                        }
                ).findFirst().orElseGet(() -> {
                    SysDictItem item = new SysDictItem();
                    item.setLabel("");
                    return item;
                }).getLabel());
            }
        });
        iPage.setRecords(baseCentres);
        return R.ok(iPage);
    }

    /**
     * 修改优先级
     *
     * @param source 修改前的优先级
     * @param target 修改后的优先级
     */
    private void updatePriority(Integer source, Integer target) {
        baseCentreMapper.updatePriority(source, target);
    }


}
