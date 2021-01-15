package cn.cesgroup.cesweb.common.security.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import cn.cesgroup.cesweb.common.security.component.OAuth2ExceptionSerializer;

/**
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 10, 2020 12:54:11 PM
 * @version 1.0.2020
 */
@JsonSerialize(using = OAuth2ExceptionSerializer.class)
public class InvalidException extends OAuth2Exception {

	/** serialVersionUID(long):. */
	private static final long serialVersionUID = 1L;

	public InvalidException(String msg, Throwable t) {
		super(msg);
	}

	@Override
	public String getOAuth2ErrorCode() {
		return "invalid_exception";
	}

	@Override
	public int getHttpErrorCode() {
		return 426;
	}

}
