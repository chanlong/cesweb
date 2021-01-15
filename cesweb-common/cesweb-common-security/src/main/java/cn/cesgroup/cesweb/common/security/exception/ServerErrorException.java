package cn.cesgroup.cesweb.common.security.exception;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import cn.cesgroup.cesweb.common.security.component.OAuth2ExceptionSerializer;

/**
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 10, 2020 12:54:47 PM
 * @version 1.0.2020
 */
@JsonSerialize(using = OAuth2ExceptionSerializer.class)
public class ServerErrorException extends OAuth2Exception {

	/** serialVersionUID(long):. */
	private static final long serialVersionUID = 1L;

	public ServerErrorException(String msg, Throwable t) {
		super(msg);
	}

	@Override
	public String getOAuth2ErrorCode() {
		return "server_error";
	}

	@Override
	public int getHttpErrorCode() {
		return HttpStatus.INTERNAL_SERVER_ERROR.value();
	}

}
