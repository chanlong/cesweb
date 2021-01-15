package cn.cesgroup.cesweb.service.system;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

import cn.cesgroup.cesweb.cloud.feign.annotation.EnableFeign;
import cn.cesgroup.cesweb.common.security.annotation.EnableResourceServer;
import cn.cesgroup.cesweb.common.swagger.annotation.EnableSwagger2;

/**
 * 系统管理模块
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 13, 2020 1:28:01 PM
 * @version 1.0.2020
 */
@EnableFeign
@EnableSwagger2
@EnableResourceServer
@SpringCloudApplication
public class SystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(SystemApplication.class, args);
	}

}
