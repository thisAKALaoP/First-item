

package com.taiji.erp.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.taiji.erp.base.api.entity.BaseArea;
import com.taiji.erp.base.api.vo.BaseAreaVo;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 区域表
 *
 * @author yangcw
 * @date 2021-05-10 16:50:10
 */
public interface BaseAreaMapper extends BaseMapper<BaseArea> {

    void updatePriority(@Param("source") Integer source,@Param("target") Integer target);

    BaseAreaVo getDetailById(Integer id);

    List<BaseAreaVo> getChinaProvinceList();
}
