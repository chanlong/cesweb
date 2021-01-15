package cn.cesgroup.cesweb.common.data.tenant;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;

/**
 * 租户信息拦截
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 13, 2020 4:31:47 PM
 * @version 1.0.2020
 */
@Configuration
public class TenantConfiguration {

	@Bean
	public RequestInterceptor feignTenantInterceptor() {
		return new FeignTenantInterceptor();
	}

	@Bean
	public ClientHttpRequestInterceptor tenantRequestInterceptor() {
		return new TenantRequestInterceptor();
	}

}
