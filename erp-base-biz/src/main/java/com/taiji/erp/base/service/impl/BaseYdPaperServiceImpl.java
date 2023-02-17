
package com.taiji.erp.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taiji.erp.base.api.entity.BaseYdPaper;
import com.taiji.erp.base.api.vo.BaseYdPaperVo;
import com.taiji.erp.base.mapper.BaseYdPaperMapper;
import com.taiji.erp.base.service.BaseYdPaperService;
import com.taiji.erp.common.core.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 印点 纸张 配置表
 *
 * @author yangcw
 * @date 2021-05-22 16:09:13
 */
@Service
public class BaseYdPaperServiceImpl extends ServiceImpl<BaseYdPaperMapper, BaseYdPaper> implements BaseYdPaperService {

    @Autowired
    private BaseYdPaperMapper baseYdPaperMapper;

    @Override
    public R<?> page(Page page, BaseYdPaperVo baseYdPaperVo) {
        Page<BaseYdPaperVo> resultPage=new Page<>();
        resultPage.setCurrent(page.getCurrent());
        resultPage.setSize(page.getSize());
        List<BaseYdPaperVo> paperList=baseYdPaperMapper.pageBaseYdPaper(page,baseYdPaperVo);
        resultPage.setRecords(paperList);
        LambdaQueryWrapper<BaseYdPaper> queryWrapper=new LambdaQueryWrapper<BaseYdPaper>()
                .eq(BaseYdPaper::getDeleteFlag,0);
        if(baseYdPaperVo.getYdId()!=null){
            queryWrapper.eq(BaseYdPaper::getYdId,baseYdPaperVo.getYdId());
        }
        resultPage.setTotal(this.count());
        return R.ok(resultPage);
    }
}
