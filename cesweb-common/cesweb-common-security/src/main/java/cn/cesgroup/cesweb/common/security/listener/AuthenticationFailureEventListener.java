package cn.cesgroup.cesweb.common.security.listener;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import cn.cesgroup.cesweb.common.security.handler.AuthenticationFailureHandler;

/**
 * 认证失败事件监听器
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 10, 2020 1:50:53 PM
 * @version 1.0.2020
 */
public class AuthenticationFailureEventListener implements ApplicationListener<AbstractAuthenticationFailureEvent> {

	@Autowired(required = false)
	private AuthenticationFailureHandler failureHandler;

	/**
	 * Handle an application event.
	 * @param event the event to respond to
	 */
	@Override
	public void onApplicationEvent(AbstractAuthenticationFailureEvent event) {
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = requestAttributes.getRequest();
		HttpServletResponse response = requestAttributes.getResponse();

		AuthenticationException authenticationException = event.getException();
		Authentication authentication = (Authentication) event.getSource();

		// 调用自定义业务实现
		if (failureHandler != null) {
			failureHandler.handle(authenticationException, authentication, request, response);
		}
	}

}
