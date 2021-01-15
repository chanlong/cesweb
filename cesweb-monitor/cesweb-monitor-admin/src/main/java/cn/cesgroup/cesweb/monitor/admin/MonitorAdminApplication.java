package cn.cesgroup.cesweb.monitor.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

/**
 * 监控中心
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 8, 2020 9:16:33 AM
 * @version 1.0.2020
 */
@EnableAdminServer
@SpringCloudApplication
public class MonitorAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(MonitorAdminApplication.class, args);
	}

}
