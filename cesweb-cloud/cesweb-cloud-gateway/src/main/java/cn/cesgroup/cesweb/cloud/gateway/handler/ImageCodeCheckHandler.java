package cn.cesgroup.cesweb.cloud.gateway.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.anji.captcha.model.common.ResponseModel;
import com.anji.captcha.model.vo.CaptchaVO;
import com.anji.captcha.service.CaptchaService;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.cesgroup.cesweb.common.core.constant.CommonConstants;
import cn.cesgroup.cesweb.common.core.util.Response;
import cn.cesgroup.cesweb.common.core.util.SpringContextHolder;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import reactor.core.publisher.Mono;

/**
 * 验证码校验逻辑处理类
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 7, 2020 4:31:11 PM
 * @version 1.0.2020
 */
@Component
@AllArgsConstructor
public class ImageCodeCheckHandler implements HandlerFunction<ServerResponse> {

	private final ObjectMapper objectMapper;

	@Override
	@SneakyThrows
	public Mono<ServerResponse> handle(ServerRequest request) {
		CaptchaVO vo = new CaptchaVO();
		vo.setPointJson(request.queryParam("pointJson").get());
		vo.setToken(request.queryParam("token").get());
		vo.setCaptchaType(CommonConstants.IMAGE_CODE_TYPE);

		CaptchaService captchaService = SpringContextHolder.getBean(CaptchaService.class);
		ResponseModel responseModel = captchaService.check(vo);

		return ServerResponse.status(HttpStatus.OK)
							 .contentType(MediaType.APPLICATION_JSON)
							 .body(BodyInserters.fromValue(objectMapper.writeValueAsString(Response.ok(responseModel))));
	}

}
