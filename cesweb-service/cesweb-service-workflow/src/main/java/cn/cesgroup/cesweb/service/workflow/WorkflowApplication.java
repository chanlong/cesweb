package cn.cesgroup.cesweb.service.workflow;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

import cn.cesgroup.cesweb.cloud.feign.annotation.EnableFeign;
import cn.cesgroup.cesweb.common.security.annotation.EnableResourceServer;
import cn.cesgroup.cesweb.common.swagger.annotation.EnableSwagger2;

/**
 * 工作流管理模块
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Aug 21, 2020 9:38:44 AM
 * @version 1.0.2020
 */
@EnableFeign
@EnableSwagger2
@EnableResourceServer
@SpringCloudApplication
public class WorkflowApplication {

	public static void main(String[] args) {
		SpringApplication.run(WorkflowApplication.class, args);
	}

}
