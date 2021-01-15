package cn.cesgroup.cesweb.common.data.tenant;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import cn.cesgroup.cesweb.common.core.constant.CommonConstants;
import cn.hutool.core.util.StrUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 13, 2020 4:30:12 PM
 * @version 1.0.2020
 */
@Slf4j
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class TenantContextHolderFilter extends GenericFilterBean {

	@Override
	@SneakyThrows
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;

		String tenantId = request.getHeader(CommonConstants.TENANT_ID);
		log.debug("获取header中的租户ID为:{}", tenantId);

		if (StrUtil.isNotBlank(tenantId)) {
			TenantContextHolder.setTenantId(tenantId);
		} else {
			TenantContextHolder.setTenantId(CommonConstants.TENANT_ID_1);
		}

		if (filterChain != null) filterChain.doFilter(request, response);
		TenantContextHolder.clear();
	}

}
