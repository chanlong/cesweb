package cn.cesgroup.cesweb.common.security.exception;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import cn.cesgroup.cesweb.common.security.component.OAuth2ExceptionSerializer;

/**
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 10, 2020 12:53:53 PM
 * @version 1.0.2020
 */
@JsonSerialize(using = OAuth2ExceptionSerializer.class)
public class ForbiddenException extends OAuth2Exception {

	/** serialVersionUID(long):. */
	private static final long serialVersionUID = 1L;

	public ForbiddenException(String msg) {
		super(msg);
	}

	public ForbiddenException(String msg, Throwable t) {
		super(msg, t);
	}

	@Override
	public String getOAuth2ErrorCode() {
		return "access_denied";
	}

	@Override
	public int getHttpErrorCode() {
		return HttpStatus.FORBIDDEN.value();
	}

}
