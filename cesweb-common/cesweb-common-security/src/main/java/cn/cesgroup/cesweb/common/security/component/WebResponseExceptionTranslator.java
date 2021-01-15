package cn.cesgroup.cesweb.common.security.component;

import java.util.Locale;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.oauth2.common.DefaultThrowableAnalyzer;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.ClientAuthenticationException;
import org.springframework.security.oauth2.common.exceptions.InsufficientScopeException;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.web.util.ThrowableAnalyzer;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import cn.cesgroup.cesweb.common.security.exception.ForbiddenException;
import cn.cesgroup.cesweb.common.security.exception.InvalidException;
import cn.cesgroup.cesweb.common.security.exception.MethodNotAllowedException;
import cn.cesgroup.cesweb.common.security.exception.ServerErrorException;
import cn.cesgroup.cesweb.common.security.exception.UnauthorizedException;

/**
 * OAuth Server异常处理,重写oauth默认实现
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 10, 2020 2:23:48 PM
 * @version 1.0.2020
 */
public class WebResponseExceptionTranslator implements org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator<OAuth2Exception> {

	private ThrowableAnalyzer throwableAnalyzer = new DefaultThrowableAnalyzer();

	@Override
	public ResponseEntity<OAuth2Exception> translate(Exception e) {

		// Try to extract a SpringSecurityException from the stacktrace
		Throwable[] causeChain = throwableAnalyzer.determineCauseChain(e);

		Exception ase = (AuthenticationException) throwableAnalyzer.getFirstThrowableOfType(AuthenticationException.class, causeChain);
		if (ase != null) {
			return handleOAuth2Exception(new UnauthorizedException(e.getMessage(), e));
		}

		ase = (AccessDeniedException) throwableAnalyzer.getFirstThrowableOfType(AccessDeniedException.class, causeChain);
		if (ase != null) {
			return handleOAuth2Exception(new ForbiddenException(ase.getMessage(), ase));
		}

		ase = (InvalidGrantException) throwableAnalyzer.getFirstThrowableOfType(InvalidGrantException.class, causeChain);
		if (ase != null) {
			String msg = SpringSecurityMessageSource.getAccessor().getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", ase.getMessage(), Locale.CHINA);
			return handleOAuth2Exception(new InvalidException(msg, ase));
		}

		ase = (HttpRequestMethodNotSupportedException) throwableAnalyzer.getFirstThrowableOfType(HttpRequestMethodNotSupportedException.class, causeChain);
		if (ase != null) {
			return handleOAuth2Exception(new MethodNotAllowedException(ase.getMessage(), ase));
		}

		ase = (OAuth2Exception) throwableAnalyzer.getFirstThrowableOfType(OAuth2Exception.class, causeChain);

		if (ase != null) {
			return handleOAuth2Exception((OAuth2Exception) ase);
		}

		return handleOAuth2Exception(new ServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e));

	}

	private ResponseEntity<OAuth2Exception> handleOAuth2Exception(OAuth2Exception e) {

		int status = e.getHttpErrorCode();
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.CACHE_CONTROL, "no-store");
		headers.set(HttpHeaders.PRAGMA, "no-cache");
		if (status == HttpStatus.UNAUTHORIZED.value() || (e instanceof InsufficientScopeException)) {
			headers.set(HttpHeaders.WWW_AUTHENTICATE, String.format("%s %s", OAuth2AccessToken.BEARER_TYPE, e.getSummary()));
		}

		// 客户端异常直接返回客户端,不然无法解析
		if (e instanceof ClientAuthenticationException) {
			return new ResponseEntity<>(e, headers, HttpStatus.valueOf(status));
		}
		
		return new ResponseEntity<>(new cn.cesgroup.cesweb.common.security.exception.OAuth2Exception(e.getMessage(), e.getOAuth2ErrorCode()), headers, HttpStatus.valueOf(status));
	}

}
