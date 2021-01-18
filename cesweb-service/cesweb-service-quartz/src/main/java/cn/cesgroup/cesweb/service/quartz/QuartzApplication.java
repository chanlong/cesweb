package cn.cesgroup.cesweb.service.quartz;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

import cn.cesgroup.cesweb.cloud.feign.annotation.EnableFeign;
import cn.cesgroup.cesweb.common.security.annotation.EnableResourceServer;
import cn.cesgroup.cesweb.common.swagger.annotation.EnableSwagger2;

/**
 * 定时任务模块
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Aug 19, 2020 10:17:32 AM
 * @version 1.0.2020
 */
@EnableFeign
@EnableSwagger2
@EnableResourceServer
@SpringCloudApplication
public class QuartzApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuartzApplication.class, args);
	}

}
