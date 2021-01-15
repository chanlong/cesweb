package cn.cesgroup.cesweb.common.security.component;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import cn.cesgroup.cesweb.common.security.handler.RestResponseErrorHandler;

/**
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 14, 2020 8:15:58 AM
 * @version 1.0.2020
 */
@ComponentScan("cn.cesgroup.cesweb.common.security")
public class ResourceServerAutoConfiguration implements ApplicationContextAware {

	private ApplicationContext applicationContext;

	@Bean
	@Primary
	@LoadBalanced
	public RestTemplate lbRestTemplate() {
		// 获取上下文配置的ClientHttpRequestInterceptor 实现
		Map<String, ClientHttpRequestInterceptor> beansOfType = applicationContext.getBeansOfType(ClientHttpRequestInterceptor.class);
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setInterceptors(new ArrayList<>(beansOfType.values()));
		restTemplate.setErrorHandler(new RestResponseErrorHandler());
		return restTemplate;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

}
