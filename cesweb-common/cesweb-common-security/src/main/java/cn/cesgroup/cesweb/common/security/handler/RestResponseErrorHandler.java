package cn.cesgroup.cesweb.common.security.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;

import java.io.IOException;

/**
 * 重写默认的 响应失败处理器，400 不作为异常
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 10, 2020 1:54:19 PM
 * @version 1.0.2020
 */
public class RestResponseErrorHandler extends DefaultResponseErrorHandler {

	@Override
	public void handleError(ClientHttpResponse response) throws IOException {
		if (response.getRawStatusCode() != HttpStatus.BAD_REQUEST.value()) {
			super.handleError(response);
		}
	}

}
