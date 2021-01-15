package cn.cesgroup.cesweb.common.security.exception;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import cn.cesgroup.cesweb.common.security.component.OAuth2ExceptionSerializer;
import lombok.Getter;

/**
 * 自定义OAuth2Exception
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 10, 2020 11:19:08 AM
 * @version 1.0.2020
 */
@JsonSerialize(using = OAuth2ExceptionSerializer.class)
public class OAuth2Exception extends org.springframework.security.oauth2.common.exceptions.OAuth2Exception {

	/** serialVersionUID(long):. */
	private static final long serialVersionUID = 1L;
	
	@Getter
	private String errorCode;

	public OAuth2Exception(String msg) {
		super(msg);
	}

	public OAuth2Exception(String msg, Throwable t) {
		super(msg, t);
	}

	public OAuth2Exception(String msg, String errorCode) {
		super(msg);
		this.errorCode = errorCode;
	}

}
