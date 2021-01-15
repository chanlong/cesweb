package cn.cesgroup.cesweb.common.data.tenant;

import java.io.IOException;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import cn.cesgroup.cesweb.common.core.constant.CommonConstants;

/**
 * 传递 RestTemplate 请求的租户ID
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 13, 2020 4:32:49 PM
 * @version 1.0.2020
 */
public class TenantRequestInterceptor implements ClientHttpRequestInterceptor {

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
		request.getHeaders().set(CommonConstants.TENANT_ID, TenantContextHolder.getTenantId());
		return execution.execute(request, body);
	}

}
