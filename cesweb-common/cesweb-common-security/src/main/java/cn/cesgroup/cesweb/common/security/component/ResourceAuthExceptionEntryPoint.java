package cn.cesgroup.cesweb.common.security.component;

import java.io.PrintWriter;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import cn.cesgroup.cesweb.common.core.constant.CommonConstants;
import cn.cesgroup.cesweb.common.core.util.Response;
import cn.hutool.http.HttpStatus;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

/**
 * 客户端异常处理 {@link org.springframework.security.core.AuthenticationException } 不同细化异常处理
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 10, 2020 2:27:11 PM
 * @version 1.0.2020
 */
@Component
@AllArgsConstructor
public class ResourceAuthExceptionEntryPoint implements AuthenticationEntryPoint {

	private final ObjectMapper objectMapper;

	@Override
	@SneakyThrows
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) {
		response.setCharacterEncoding(CommonConstants.UTF8);
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		Response<String> result = new Response<>();
		result.setMsg(authException.getMessage());
		result.setData(authException.getMessage());
		result.setCode(CommonConstants.FAIL);

		if (authException instanceof CredentialsExpiredException || authException instanceof InsufficientAuthenticationException) {
			String msg = SpringSecurityMessageSource.getAccessor().getMessage("AbstractUserDetailsAuthenticationProvider.credentialsExpired", authException.getMessage(), Locale.CHINA);
			result.setMsg(msg);
		}

		if (authException instanceof UsernameNotFoundException) {
			String msg = SpringSecurityMessageSource.getAccessor().getMessage("AbstractUserDetailsAuthenticationProvider.noopBindAccount", authException.getMessage(), Locale.CHINA);
			result.setMsg(msg);
		}

		response.setStatus(HttpStatus.HTTP_UNAUTHORIZED);
		PrintWriter printWriter = response.getWriter();
		printWriter.append(objectMapper.writeValueAsString(result));
	}

}
