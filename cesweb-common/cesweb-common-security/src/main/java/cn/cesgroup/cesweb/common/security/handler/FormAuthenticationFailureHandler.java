package cn.cesgroup.cesweb.common.security.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import cn.cesgroup.cesweb.common.core.util.WebUtils;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.http.HttpUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * 表单登录失败处理逻辑
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 10, 2020 2:47:15 PM
 * @version 1.0.2020
 */
@Slf4j
public class FormAuthenticationFailureHandler implements AuthenticationFailureHandler {

	/**
	 * Called when an authentication attempt fails.
	 * @param request the request during which the authentication attempt occurred.
	 * @param response the response.
	 * @param exception the exception which was thrown to reject the authentication
	 */
	@Override
	@SneakyThrows
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) {
		log.debug("表单登录失败:{}", exception.getLocalizedMessage());
		String url = HttpUtil.encodeParams(String.format("/token/login?error=%s", exception.getMessage()), CharsetUtil.CHARSET_UTF_8);
		WebUtils.getResponse().sendRedirect(url);
	}

}