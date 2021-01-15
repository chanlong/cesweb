package cn.cesgroup.cesweb.cloud.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

import cn.cesgroup.cesweb.cloud.router.annotation.EnableDynamicRoute;

/**
 * 网关服务
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 7, 2020 4:30:23 PM
 * @version 1.0.2020
 */
@EnableDynamicRoute
@SpringCloudApplication
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

}
