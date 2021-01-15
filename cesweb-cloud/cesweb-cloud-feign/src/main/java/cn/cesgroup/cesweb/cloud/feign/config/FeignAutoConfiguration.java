package cn.cesgroup.cesweb.cloud.feign.config;

import org.springframework.boot.actuate.autoconfigure.endpoint.condition.ConditionalOnAvailableEndpoint;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.CloudFeignClientsRegistrar;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import cn.cesgroup.cesweb.cloud.feign.endpoint.FeignClientEndpoint;
import feign.Feign;

/**
 * feign 自动化配置
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 9, 2020 11:50:48 AM
 * @version 1.0.2020
 */
@Configuration
@ConditionalOnClass(Feign.class)
@Import(CloudFeignClientsRegistrar.class)
@AutoConfigureAfter(EnableFeignClients.class)
public class FeignAutoConfiguration {

	@Bean
	@ConditionalOnMissingBean
	@ConditionalOnAvailableEndpoint
	public FeignClientEndpoint feignClientEndpoint(ApplicationContext context) {
		return new FeignClientEndpoint(context);
	}

}
