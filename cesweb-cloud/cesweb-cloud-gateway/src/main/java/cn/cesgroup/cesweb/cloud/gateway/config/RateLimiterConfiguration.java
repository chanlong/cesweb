package cn.cesgroup.cesweb.cloud.gateway.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import reactor.core.publisher.Mono;

/**
 * 路由限流配置
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 7, 2020 4:53:08 PM
 * @version 1.0.2020
 */
@Configuration
public class RateLimiterConfiguration {

	@Bean(value = "remoteAddrKeyResolver")
	public KeyResolver remoteAddrKeyResolver() {
		return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getAddress().getHostAddress());
	}

}
