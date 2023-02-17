
package com.taiji.erp.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taiji.erp.base.api.entity.BasePaper;
import com.taiji.erp.base.api.vo.BasePaperVo;
import com.taiji.erp.base.mapper.BasePaperMapper;
import com.taiji.erp.base.service.BasePaperService;
import com.taiji.erp.common.core.util.R;
import com.taiji.erp.common.security.util.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * 纸张编码 表
 *
 * @author yangcw
 * @date 2021-05-22 16:09:13
 */
@Service
public class BasePaperServiceImpl extends ServiceImpl<BasePaperMapper, BasePaper> implements BasePaperService {

    @Autowired
    private BasePaperMapper basePaperMapper;

    @Override
    public R<?> save(BasePaperVo basePaperVo) {

        BasePaper basePaper=new BasePaper();
        BeanUtils.copyProperties(basePaperVo,basePaper);
        //设置纸张名称，纸张编码
        basePaper.setPaperName(generateName(basePaper));
        basePaper.setPaperCode(generateCode());
        //设置优先级
        if(Optional.ofNullable(basePaper.getPriority()).orElse(0)!=0){
            this.updatePriority(null,basePaper.getPriority());
        }else {
            basePaper.setPriority(this.count(new LambdaQueryWrapper<BasePaper>().eq(BasePaper::getDeleteFlag,0))+1);
        }
        //设置基本信息
        basePaper.setDeleteFlag(0);
        basePaper.setCreateDate(LocalDateTime.now());
        basePaper.setCreateUserId(SecurityUtils.getUser().getId());
        basePaper.setState(0);

        return R.ok(this.save(basePaper));
    }

    @Override
    public R<?> update(BasePaperVo basePaperVo) {

        BasePaper basePaper=new BasePaper();
        BeanUtils.copyProperties(basePaperVo,basePaper);

        this.updateById(basePaper);
        //更新后设置名字
        BasePaper paper=this.getById(basePaperVo.getId());
        paper.setPaperName(generateName(paper));

        return R.ok(this.updateById(paper));
    }

    @Override
    public void updatePriority(Integer source, Integer target) {
        basePaperMapper.updatePriority(source,target);
    }

    private String generateCode(){

        String code;
        Integer size=this.count()+1;
        while (true){
            code=String.format("%04d",size);
            if(this.count(new LambdaQueryWrapper<BasePaper>().eq(BasePaper::getPaperCode,code).eq(BasePaper::getDeleteFlag,0))==0){
                return code;
            }
            size++;
        }
    }

    private String generateName(BasePaper basePaper){

        StringBuilder name=new StringBuilder();
        if(basePaper.getPaperWeight()!=null){
            name.append(basePaper.getPaperWeight());
        }
        if(basePaper.getPaperSize()!=null){
            name.append(basePaper.getPaperSize());
        }
        name.append(Optional.ofNullable(basePaper.getPaperBrand()).orElse(""));
        String origin="";
        if(basePaper.getPaperOrigin().equals("domestic")){
            origin="国产";
        }
        if(basePaper.getPaperOrigin().equals("import")){
            origin="进口";
        }
        name.append(origin);

        return name.toString();
    }


}
