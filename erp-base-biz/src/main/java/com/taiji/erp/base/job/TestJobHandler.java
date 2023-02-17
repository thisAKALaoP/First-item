package com.taiji.erp.base.job;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;
import com.xxl.job.core.util.ShardingUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @ClassName: TestJobHandler
 * @Description: TODO(一句话描述该类的功能)
 * @Author: yangcw
 * @Date: 2020/2/29 23:13
 */
@Slf4j
@Component
@JobHandler("TestJobHandler")
public class TestJobHandler extends IJobHandler {

    @Override
    public ReturnT<String> execute(String s) throws Exception {
        ShardingUtil.ShardingVO shardingVO = ShardingUtil.getShardingVo();


        XxlJobLogger.log("This is a test job." + shardingVO +s);
        return SUCCESS;
    }
}
