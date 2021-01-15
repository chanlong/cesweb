package cn.cesgroup.cesweb.service.generate;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

import cn.cesgroup.cesweb.cloud.feign.annotation.EnableFeign;
import cn.cesgroup.cesweb.common.datasource.annotation.EnableDynamicDataSource;
import cn.cesgroup.cesweb.common.security.annotation.EnableResourceServer;
import cn.cesgroup.cesweb.common.swagger.annotation.EnableSwagger2;

/**
 * 代码生成模块
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 13, 2020 11:52:43 AM
 * @version 1.0.2020
 */
@EnableFeign
@EnableSwagger2
@EnableResourceServer
@EnableDynamicDataSource
@SpringCloudApplication
public class DevelopeApplication {

	public static void main(String[] args) {
		SpringApplication.run(DevelopeApplication.class, args);
	}

}
