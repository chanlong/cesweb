package cn.cesgroup.cesweb.common.security.listener;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import cn.cesgroup.cesweb.common.security.handler.AuthenticationSuccessHandler;
import cn.cesgroup.cesweb.common.security.service.OAuth2User;
import cn.hutool.core.collection.CollUtil;

/**
 * 认证成功事件监听器
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 10, 2020 1:50:28 PM
 * @version 1.0.2020
 */
public class AuthenticationSuccessEventListener implements ApplicationListener<AuthenticationSuccessEvent> {

	@Autowired(required = false)
	private AuthenticationSuccessHandler successHandler;

	/**
	 * Handle an application event.
	 * @param event the event to respond to
	 */
	@Override
	public void onApplicationEvent(AuthenticationSuccessEvent event) {
		Authentication authentication = (Authentication) event.getSource();
		if (successHandler != null && isUserAuthentication(authentication)) {
			ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
			HttpServletRequest request = requestAttributes.getRequest();
			HttpServletResponse response = requestAttributes.getResponse();

			successHandler.handle(authentication, request, response);
		}
	}

	private boolean isUserAuthentication(Authentication authentication) {
		return authentication.getPrincipal() instanceof OAuth2User || CollUtil.isNotEmpty(authentication.getAuthorities());
	}

}
