package com.taiji.erp.base;


import com.taiji.erp.common.job.annotation.EnableTaijiXxlJob;
import com.taiji.erp.common.security.annotation.EnableTaijiFeignClients;
import com.taiji.erp.common.security.annotation.EnableTaijiResourceServer;
import com.taiji.erp.common.swagger.annotation.EnableTaijiSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

//@EnableTaijiXxlJob
@EnableTaijiFeignClients
@EnableTaijiResourceServer
@EnableTaijiSwagger2
@SpringCloudApplication
public class ErpBaseApplication {
    public static void main(String[] args) {
        SpringApplication.run(ErpBaseApplication.class, args);
    }

}
