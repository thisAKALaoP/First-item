
package com.taiji.erp.base.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taiji.erp.admin.api.entity.SysOrg;
import com.taiji.erp.admin.api.feign.SysOrgClient;
import com.taiji.erp.base.api.entity.BaseCtp;
import com.taiji.erp.base.api.entity.BaseNewspaper;
import com.taiji.erp.base.api.vo.BaseCtpVo;
import com.taiji.erp.base.mapper.BaseCtpMapper;
import com.taiji.erp.base.service.BaseCtpService;
import com.taiji.erp.base.service.BaseNewspaperService;
import com.taiji.erp.common.core.util.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.swing.text.DateFormatter;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ctp 版价格及套数设置
 *
 * @author yangcw
 * @date 2021-05-31 10:43:24
 */
@Service
@Slf4j
public class BaseCtpServiceImpl extends ServiceImpl<BaseCtpMapper, BaseCtp> implements BaseCtpService {

    @Autowired
    private BaseCtpMapper baseCtpMapper;
    @Autowired
    private SysOrgClient sysOrgClient;
    @Autowired
    private BaseNewspaperService baseNewspaperService;

    @Override
    public R<?> page(Page page, BaseCtpVo baseCtpVo) {

        List<BaseCtpVo> ctpVoList=new ArrayList<>();
        Integer total=0;
        if(StringUtils.isEmpty(baseCtpVo.getYdName())){
            //如果印点名称为空
            ctpVoList=baseCtpMapper.search(page,baseCtpVo);
            if(ctpVoList.size()>0){
                total=ctpVoList.get(0).getTotal();
            }
        }
        else {
            //印点名称不为空
            if(StringUtils.isEmpty(baseCtpVo.getNewspaperName())&&StringUtils.isEmpty(baseCtpVo.getStartMonth())&&StringUtils.isEmpty(baseCtpVo.getEndMonth())){
                //如果其他参数为空，则直接掉印点名称查询的接口
                R<Page<SysOrg>> r=sysOrgClient.selectByName(baseCtpVo.getYdName(),null,2,(int)page.getCurrent(),(int )page.getSize());
                if(r.getCode()!=0){
                    log.info("获取印点列表失败，失败原因：{}",r.getMsg());
                    return R.failed("印点获取列表失败");
                }
                IPage iPage= JSON.parseObject(JSON.toJSONString(r.getData()),Page.class);
                List<SysOrg> orgList=JSON.parseArray(JSON.toJSONString(iPage.getRecords()),SysOrg.class);
                //然后根据组织列表获取印点id集合
                if(orgList.size()>0){
                    List<Integer> orgIdList=orgList.stream().map(SysOrg::getOrgId).collect(Collectors.toList());
                    List<BaseCtp> baseCtpList=this.list(new LambdaQueryWrapper<BaseCtp>()
                            .eq(BaseCtp::getDeleteFlag,0)
                            .eq(BaseCtp::getYdId,orgIdList));
                    //转换设置印点名称和报刊名称
                    ctpVoList= baseCtpList.stream().collect(ArrayList::new,(voList, ctp)->{
                        BaseCtpVo ctpVo=new BaseCtpVo();
                        BeanUtils.copyProperties(ctp,ctpVo);
                        //设置印点名称
                        ctpVo.setYdName(orgList.stream()
                                .filter((org)->org.getOrgId().intValue()==ctp.getYdId()).findFirst()
                                .orElseGet(()->{
                                    SysOrg sysOrg=new SysOrg();
                                    sysOrg.setOrgName("");
                                    return sysOrg;
                                }).getOrgName());
                        //设置报刊名称
                        BaseNewspaper baseNewspaper=baseNewspaperService.getById(ctp.getNewspaperId());
                        if(baseNewspaper!=null){
                            ctpVo.setNewspaperName(baseNewspaper.getNewspaperName());
                        }
                        voList.add(ctpVo);
                    },(m,n)->{});
                }
                total= Math.toIntExact(iPage.getTotal());
            }
            else {
                //其他参数不为空，则先根据其他参数查出列表，然后根据印点名称手动分页
                ctpVoList=baseCtpMapper.search(baseCtpVo);
                if(ctpVoList.size()>0) {
                    List<Integer> orgIdList = ctpVoList.stream().map(BaseCtpVo::getYdId).collect(Collectors.toList());
                    R<List<SysOrg>> r = sysOrgClient.selectByIds(orgIdList);
                    if (r.getCode() != 0) {
                        log.info("获取印点列表失败，原因：{}", r.getMsg());
                        return R.failed("获取印点信息失败");
                    }
                    //获取印点数据并过滤
                    List<SysOrg> orgList = JSON.parseArray(JSON.toJSONString(r.getData()), SysOrg.class);
                    orgList=orgList.stream().filter((org)->org.getOrgName().contains(baseCtpVo.getYdName())).collect(Collectors.toList());
                    //手动分页
                    if(orgList.size()>0){
                        //拿到手动分页后的数据
                        orgIdList.clear();
                        if(orgList.size()>(page.getCurrent()-1)*page.getSize()){
                            int size=0;
                            if(orgList.size()<page.getCurrent()*page.getSize())
                                size=orgList.size();
                            else {
                                size= (int) (page.getCurrent()*page.getSize());
                            }
                            for (int i = (int) ((page.getCurrent()-1)*page.getSize()); i < size; i++) {
                                orgIdList.add(orgList.get(i).getOrgId());
                            }
                        }
                        //根据手动分页后的印点数据过滤
                        ctpVoList=ctpVoList.stream().filter((vo)->orgIdList.contains(vo.getYdId())).collect(Collectors.toList());
                    }
                    total=orgList.size();
                }
            }
        }
        IPage result=new Page();
        result.setCurrent(page.getCurrent());
        result.setSize(page.getSize());
        result.setTotal(total);
        result.setRecords(ctpVoList);
        return R.ok(result);
    }
}
