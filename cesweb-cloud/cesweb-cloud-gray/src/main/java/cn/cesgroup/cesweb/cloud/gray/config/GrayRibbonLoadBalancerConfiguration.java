package cn.cesgroup.cesweb.cloud.gray.config;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import cn.cesgroup.cesweb.cloud.gray.feign.GrayFeignRequestInterceptor;
import cn.cesgroup.cesweb.cloud.gray.rule.GrayRibbonLoadBalancerRule;
import feign.RequestInterceptor;

/**
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 10, 2020 3:50:54 PM
 * @version 1.0.2020
 */
@Configuration
@ConditionalOnProperty(value = "gray.rule.enabled", havingValue = "true")
public class GrayRibbonLoadBalancerConfiguration {

	@Bean
	@ConditionalOnMissingBean
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public GrayRibbonLoadBalancerRule ribbonLoadBalancerRule() {
		return new GrayRibbonLoadBalancerRule();
	}

	@Bean
	public RequestInterceptor grayFeignRequestInterceptor() {
		return new GrayFeignRequestInterceptor();
	}

}
