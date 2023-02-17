package com.taiji.erp.base.config;

import com.taiji.erp.common.core.util.RedisUtil;
import com.taiji.erp.common.sequence.builder.DbSeqBuilder;
import com.taiji.erp.common.sequence.properties.SequenceDbProperties;
import com.taiji.erp.common.sequence.range.BizName;
import com.taiji.erp.common.sequence.sequence.Sequence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SequenceConfig {



    @Bean
    public Sequence sequence(DataSource dataSource,
                             SequenceDbProperties properties) {
        return DbSeqBuilder
                .create()
                .bizName(new XhwcbBizName())// 定义切片规则
                .dataSource(dataSource) // 注入数据源
                .step(1000)     // 每次获取数据的个数
                .retryTimes(3)   // 重试次数
                .tableName("xhwcb_sequence") // 存储表名信息
                .build();
    }
}
