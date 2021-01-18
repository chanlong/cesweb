/**
 * <p>Copyright:Copyright(c) 2020</p>
 * <p>Company:Professional</p>
 * <p>Package:cn.cesgroup.cesweb.service.tenant</p>
 * <p>File:TentantApplication.java</p>
 * <p>类更新历史信息</p>
 * @todo chanlong(陈龙) 创建于 2020年12月14日 下午5:27:04
 */
package cn.cesgroup.cesweb.service.tenant;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

import cn.cesgroup.cesweb.cloud.feign.annotation.EnableFeign;
import cn.cesgroup.cesweb.common.security.annotation.EnableResourceServer;
import cn.cesgroup.cesweb.common.swagger.annotation.EnableSwagger2;

/**
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date 2020年12月14日 下午5:27:04
 * @version 1.0.2020
 */
@EnableFeign
@EnableSwagger2
@EnableResourceServer
@SpringCloudApplication
public class TentantApplication {

	public static void main(String[] args) {
		SpringApplication.run(TentantApplication.class, args);
	}
}
