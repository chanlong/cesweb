package cn.cesgroup.cesweb.service.auth.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import cn.cesgroup.cesweb.api.system.entity.SysLog;
import cn.cesgroup.cesweb.api.system.feign.RemoteLogService;
import cn.cesgroup.cesweb.common.core.constant.SecurityConstants;
import cn.cesgroup.cesweb.common.core.util.WebUtils;
import cn.cesgroup.cesweb.common.log.util.SysLogUtils;
import cn.cesgroup.cesweb.common.security.handler.AuthenticationSuccessHandler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 14, 2020 9:41:12 AM
 * @version 1.0.2020
 */
@Slf4j
@Component
@AllArgsConstructor
public class AuthenticationSuccessEventHandler implements AuthenticationSuccessHandler {

	private final RemoteLogService logService;

	/**
	 * 处理登录成功方法
	 * <p>
	 * 获取到登录的authentication 对象
	 * @param authentication 登录对象
	 * @param request 请求
	 * @param response 返回
	 */
	@Async
	@Override
	public void handle(Authentication authentication, HttpServletRequest request, HttpServletResponse response) {
		String username = authentication.getName();
		SysLog sysLog = SysLogUtils.getSysLog(request, username);
		sysLog.setTitle(username + "用户登录");
		sysLog.setParams(username);
		String header = request.getHeader(HttpHeaders.AUTHORIZATION);
		sysLog.setServiceId(WebUtils.extractClientId(header).orElse("N/A"));

		logService.saveLog(sysLog, SecurityConstants.FROM_IN);
		log.info("用户：{} 登录成功", username);
	}

}
