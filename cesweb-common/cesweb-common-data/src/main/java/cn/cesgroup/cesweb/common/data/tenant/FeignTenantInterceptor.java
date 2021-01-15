package cn.cesgroup.cesweb.common.data.tenant;

import cn.cesgroup.cesweb.common.core.constant.CommonConstants;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 13, 2020 4:31:04 PM
 * @version 1.0.2020
 */
@Slf4j
public class FeignTenantInterceptor implements RequestInterceptor {

	@Override
	public void apply(RequestTemplate requestTemplate) {
		if (TenantContextHolder.getTenantId() == null) {
			log.debug("TTL 中的 租户ID为空，feign拦截器 >> 跳过");
			return;
		}
		requestTemplate.header(CommonConstants.TENANT_ID, TenantContextHolder.getTenantId().toString());
	}

}
