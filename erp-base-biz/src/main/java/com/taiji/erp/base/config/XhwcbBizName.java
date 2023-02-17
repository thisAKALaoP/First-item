package com.taiji.erp.base.config;

import cn.hutool.core.date.DateUtil;
import com.taiji.erp.common.sequence.range.BizName;

public class XhwcbBizName implements BizName {

    @Override
    public String create() {
        return DateUtil.today();
    }
}
