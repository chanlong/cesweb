package cn.cesgroup.cesweb.common.security.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * token 发放失败处理
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 10, 2020 2:46:29 PM
 * @version 1.0.2020
 */
public interface AuthenticationFailureHandler {

	/**
	 * 业务处理
	 * @param authenticationException 错误信息
	 * @param authentication 认证信息
	 * @param request 请求信息
	 * @param response 响应信息
	 */
	void handle(AuthenticationException authenticationException, Authentication authentication, HttpServletRequest request, HttpServletResponse response);

}
