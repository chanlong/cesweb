package cn.cesgroup.cesweb.cloud.router.rule;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.http.server.reactive.ServerHttpRequest;

/**
 * 灰度路由
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 7, 2020 2:44:25 PM
 * @version 1.0.2020
 */
public interface GrayLoadBalancer {

	/**
	 * 根据serviceId 筛选可用服务
	 * @param serviceId 服务ID
	 * @param request 当前请求
	 * @return
	 */
	ServiceInstance choose(String serviceId, ServerHttpRequest request);

}
