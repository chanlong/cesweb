package cn.cesgroup.cesweb.common.security.component;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import lombok.RequiredArgsConstructor;

/**
 * 改造 {@link BearerTokenExtractor} 对公开权限的请求不进行校验
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 10, 2020 12:46:16 PM
 * @version 1.0.2020
 */
@Component
@RequiredArgsConstructor
public class BearerTokenExtractor extends org.springframework.security.oauth2.provider.authentication.BearerTokenExtractor {

	private final PathMatcher pathMatcher = new AntPathMatcher();

	private final PermitAllUrlProperties urlProperties;

	@Override
	public Authentication extract(HttpServletRequest request) {
		boolean match = urlProperties.getIgnoreUrls().stream().anyMatch(url -> pathMatcher.match(url, request.getRequestURI()));
		return match ? null : super.extract(request);
	}

}
