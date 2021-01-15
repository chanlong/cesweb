package cn.cesgroup.cesweb.service.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

import cn.cesgroup.cesweb.cloud.feign.annotation.EnableFeign;

/**
 * 认证授权中心
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 14, 2020 8:56:21 AM
 * @version 1.0.2020
 */
@EnableFeign
@SpringCloudApplication
public class AuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class, args);
	}

}
