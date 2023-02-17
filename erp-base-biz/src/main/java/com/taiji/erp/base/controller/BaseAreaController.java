

package com.taiji.erp.base.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taiji.erp.base.api.entity.BaseArea;
import com.taiji.erp.base.api.vo.BaseAreaVo;
import com.taiji.erp.base.service.BaseAreaService;
import com.taiji.erp.common.core.util.R;
import com.taiji.erp.common.log.annotation.SysLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;


/**
 * 区域表
 *
 * @author yangcw
 * @date 2021-05-10 16:50:10
 */
@RestController
@AllArgsConstructor
@RequestMapping("/baseArea")
@Api(value = "baseArea", tags = "区域表管理")
public class BaseAreaController {

    private final BaseAreaService baseAreaService;

    /**
     * 列表查询
     * @param baseAreaVo 区域表
     */
    @ApiOperation(value = "列表查询", notes = "列表查询")
    @GetMapping("/list")
    public R<?> getBaseAreaPage(BaseAreaVo baseAreaVo) {
        QueryWrapper<BaseArea> queryWrapper=new QueryWrapper<>();
        if(!StringUtils.isEmpty(baseAreaVo.getAreaName())){
            queryWrapper.eq("area_name",baseAreaVo.getAreaName());
        }
        Integer parentId=null;
        //特殊定制，当传id时查找的是父级列表，当传parentId时查找的是同级列表
        if(baseAreaVo.getId()!=null){
            BaseArea area=baseAreaService.getById(baseAreaVo.getId());
            if(area!=null){
                if(area.getParentId()!=null){
                    BaseArea parentArea=baseAreaService.getById(area.getParentId());
                    parentId=parentArea.getParentId();
                }
            }
        }else if(baseAreaVo.getParentId()!=null){
            parentId=baseAreaVo.getParentId();
        }

        if(parentId!=null&&parentId!=0){
            queryWrapper.eq("parent_id",parentId);
        }else {
            queryWrapper.isNull("parent_id");
        }
        queryWrapper.eq("delete_flag",0)
                .orderByAsc("priority");
        return R.ok(baseAreaService.list(queryWrapper));
    }

    @ApiOperation(value = "分页查询",tags = "分页查询")
    @GetMapping(value = "/page")
    public R<?> page(Page page,BaseAreaVo baseAreaVo){

        BaseArea baseArea=new BaseArea();
        BeanUtils.copyProperties(baseAreaVo,baseArea);
        QueryWrapper<BaseArea> queryWrapper=Wrappers.query(baseArea);
        if(StringUtils.isEmpty(baseAreaVo.getParentId())){
            queryWrapper.isNull("parent_id");
        }
        queryWrapper.orderByAsc("priority");
        return R.ok(baseAreaService.page(page, queryWrapper));

    }

    @ApiOperation(value = "获取中国下面的省份",tags = "获取中国下面的省份")
    @GetMapping(value = "/getChinaProvince")
    public R<?> getChinaProvince(){
        return baseAreaService.getChinaProvinceList();
    }

    /**
     * 通过id查询区域表
     *
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/{id}")
    public R<?> getById(@PathVariable("id") Integer id) {

        return baseAreaService.getDetailById(id);

    }

    /**
     * 新增区域表
     *
     * @param baseAreaVo 区域表
     * @return R
     */
    @ApiOperation(value = "新增区域表", notes = "新增区域表")
    @SysLog("新增区域表")
    @PostMapping
    @PreAuthorize("@pms.hasPermission('base_basearea_add')" )
    public synchronized R<?> save(@RequestBody BaseAreaVo baseAreaVo) {
        //判断是否有同名的区域
        QueryWrapper<BaseArea> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("area_name", baseAreaVo.getAreaName())
                .eq("delete_flag", 0);
        if (baseAreaService.listObjs(queryWrapper).size() > 0) {
            return R.failed("已存在相同区域");
        }
        return R.ok(baseAreaService.saveByVo(baseAreaVo));
    }

    /**
     * 修改区域表
     *
     * @param baseAreaVo 区域表
     * @return R
     */
    @ApiOperation(value = "修改区域表", notes = "修改区域表")
    @SysLog("修改区域表")
    @PutMapping
    @PreAuthorize("@pms.hasPermission('base_basearea_edit')" )
    public synchronized R<?> updateById(@RequestBody BaseAreaVo baseAreaVo) {

        //判断是否有同名的区域
        QueryWrapper<BaseArea> queryWrapper = new QueryWrapper<>();
        queryWrapper.ne("id",baseAreaVo.getId())
                .and(true,(wrapper)-> wrapper.eq("area_name", baseAreaVo.getAreaName())
                        .eq("parent_id",baseAreaVo.getParentId())
                        .eq("delete_flag", 0));
        if (baseAreaService.listObjs(queryWrapper).size() > 0) {
            return R.failed("已存在相同区域");
        }
        return R.ok(baseAreaService.updateByVo(baseAreaVo));
    }

    /**
     * 通过id删除区域表
     *
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id删除区域表", notes = "通过id删除区域表")
    @SysLog("通过id删除区域表")
    @DeleteMapping("/{id}")
    @PreAuthorize("@pms.hasPermission('base_basearea_del')" )
    public R<?> removeById(@PathVariable Integer id) {
        BaseArea baseArea = baseAreaService.getById(id);
        baseAreaService.updatePriority(baseArea.getPriority(), null);
        return R.ok(baseAreaService.removeById(id));
    }
}
