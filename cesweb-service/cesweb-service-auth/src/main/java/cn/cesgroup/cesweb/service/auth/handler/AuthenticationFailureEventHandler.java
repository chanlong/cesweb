package cn.cesgroup.cesweb.service.auth.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import cn.cesgroup.cesweb.api.system.entity.SysLog;
import cn.cesgroup.cesweb.api.system.feign.RemoteLogService;
import cn.cesgroup.cesweb.common.core.constant.CommonConstants;
import cn.cesgroup.cesweb.common.core.constant.SecurityConstants;
import cn.cesgroup.cesweb.common.core.util.WebUtils;
import cn.cesgroup.cesweb.common.log.util.SysLogUtils;
import cn.cesgroup.cesweb.common.security.handler.AuthenticationFailureHandler;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 14, 2020 9:40:45 AM
 * @version 1.0.2020
 */
@Slf4j
@Component
@AllArgsConstructor
public class AuthenticationFailureEventHandler implements AuthenticationFailureHandler {

	private final RemoteLogService logService;

	/**
	 * 异步处理，登录失败方法
	 * <p>
	 * @param authenticationException 登录的authentication 对象
	 * @param authentication 登录的authenticationException 对象
	 * @param request 请求
	 * @param response 响应
	 */
	@Async
	@Override
	@SneakyThrows
	public void handle(AuthenticationException authenticationException, Authentication authentication, HttpServletRequest request, HttpServletResponse response) {
		String username = authentication.getName();
		SysLog sysLog = SysLogUtils.getSysLog(request, username);
		sysLog.setTitle(username + "用户登录");
		sysLog.setType(CommonConstants.STATUS_LOCK);
		sysLog.setParams(username);
		sysLog.setException(authenticationException.getLocalizedMessage());
		String header = request.getHeader(HttpHeaders.AUTHORIZATION);
		sysLog.setServiceId(WebUtils.extractClientId(header).orElse("N/A"));

		logService.saveLog(sysLog, SecurityConstants.FROM_IN);

		log.info("用户：{} 登录失败，异常：{}", username, authenticationException.getLocalizedMessage());
	}

}
