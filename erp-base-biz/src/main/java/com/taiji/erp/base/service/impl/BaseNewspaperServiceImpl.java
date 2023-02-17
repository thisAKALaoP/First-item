
package com.taiji.erp.base.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taiji.erp.admin.api.entity.SysDictItem;
import com.taiji.erp.admin.api.feign.SysDictClient;
import com.taiji.erp.base.api.entity.BaseNewspaper;
import com.taiji.erp.base.api.vo.BaseNewspaperVo;
import com.taiji.erp.base.mapper.BaseNewspaperMapper;
import com.taiji.erp.base.service.BaseNewspaperService;
import com.taiji.erp.common.core.util.R;
import com.taiji.erp.common.security.util.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * 报刊 配置 表
 *
 * @author yangcw
 * @date 2021-05-18 10:13:07
 */
@Service
public class BaseNewspaperServiceImpl extends ServiceImpl<BaseNewspaperMapper, BaseNewspaper> implements BaseNewspaperService {

    @Autowired
    private BaseNewspaperMapper baseNewspaperMapper;
    @Autowired
    private SysDictClient sysDictClient;
    @Value("${erp-base.baseCentre.releasePeriodType}")
    private String releasePeriodType;

    @Override
    public R<?> save(BaseNewspaperVo baseNewspaperVo) {

        //首先判断是否重复
        QueryWrapper<BaseNewspaper> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("newspaper_code", baseNewspaperVo.getNewspaperCode())
                .or().eq("newspaper_name", baseNewspaperVo.getNewspaperName());

        if (this.count(queryWrapper) > 0) {
            return R.failed("报刊代码或名称已存在");
        }
        BaseNewspaper baseNewspaper = new BaseNewspaper();
        BeanUtils.copyProperties(baseNewspaperVo, baseNewspaper);
        //设置优先级
        if (Optional.ofNullable(baseNewspaper.getPriority()).orElse(0) == 0) {
            int total = this.count(new LambdaQueryWrapper<BaseNewspaper>().eq(BaseNewspaper::getDeleteFlag,0));
            baseNewspaper.setPriority(total + 1);
        }else {
            this.updatePriority(null,baseNewspaper.getPriority());
        }
        //设置删除标志等状态
        baseNewspaper.setDeleteFlag(0);
        baseNewspaper.setState(0);
        baseNewspaper.setCreateDate(LocalDateTime.now());
        baseNewspaper.setCreateUserId(SecurityUtils.getUser().getId());

        return R.ok(this.save(baseNewspaper));
    }

    @Override
    public R<?> update(BaseNewspaperVo baseNewspaperVo) {

        //判断是否重复
        QueryWrapper<BaseNewspaper> queryWrapper = new QueryWrapper<>();
        queryWrapper.ne("id", baseNewspaperVo.getId())
                .and(true, (baseNewspaperQueryWrapper -> {
                    baseNewspaperQueryWrapper.eq("newspaper_code", baseNewspaperVo.getNewspaperCode())
                            .or().eq("newspaper_name", baseNewspaperVo.getNewspaperName());
                }));
        if (this.count(queryWrapper) > 0) {
            return R.failed("报刊代码或名称已存在");
        }
        BaseNewspaper baseNewspaper = new BaseNewspaper();
        BeanUtils.copyProperties(baseNewspaperVo, baseNewspaper);
        BaseNewspaper oldNewspaper = this.getById(baseNewspaperVo.getId());

        return R.ok(this.updateById(baseNewspaper));
    }

    @Override
    public R<?> list(Page page, BaseNewspaperVo baseNewspaperVo) {

        BaseNewspaper baseNewspaper = new BaseNewspaper();
        BeanUtils.copyProperties(baseNewspaperVo, baseNewspaper);
        baseNewspaper.setDeleteFlag(0);

        List<BaseNewspaperVo> list = baseNewspaperMapper.list(page, baseNewspaperVo);
        //设置报刊发型周期
        R<List<SysDictItem>> r=sysDictClient.getDictByType(releasePeriodType);
        List<SysDictItem> itemList= JSON.parseArray(JSON.toJSONString(r.getData()),SysDictItem.class);
        list.parallelStream().forEach(newspaperVo -> {
            if(!StringUtils.isEmpty(newspaperVo.getReleasePeriod())){
                newspaperVo.setReleasePeriodName(itemList.parallelStream().filter((item)->{
                    return newspaperVo.getReleasePeriod().equals(item.getValue());
                }).findAny().orElseGet(()->{
                    SysDictItem item=new SysDictItem();
                    item.setLabel("");
                    return item;
                }).getLabel());
            }
        });
        //分页设置
        Integer size = this.count(Wrappers.query(baseNewspaper));
        Page resultPage = new Page();
        resultPage.setCurrent(page.getCurrent());
        resultPage.setRecords(list);
        resultPage.setTotal(size);

        return R.ok(resultPage);
    }

    @Override
    public void updatePriority(Integer source, Integer target) {
        baseNewspaperMapper.updatePriority(source, target);
    }
}
