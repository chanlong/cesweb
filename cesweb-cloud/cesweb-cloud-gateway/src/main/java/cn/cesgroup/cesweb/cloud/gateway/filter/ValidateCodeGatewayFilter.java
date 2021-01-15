package cn.cesgroup.cesweb.cloud.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;

import com.anji.captcha.model.vo.CaptchaVO;
import com.anji.captcha.service.CaptchaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.cesgroup.cesweb.cloud.gateway.config.FilterIgnorePropertiesConfig;
import cn.cesgroup.cesweb.common.core.constant.CacheConstants;
import cn.cesgroup.cesweb.common.core.constant.CommonConstants;
import cn.cesgroup.cesweb.common.core.constant.SecurityConstants;
import cn.cesgroup.cesweb.common.core.constant.enums.LoginTypeEnum;
import cn.cesgroup.cesweb.common.core.exception.ValidateCodeException;
import cn.cesgroup.cesweb.common.core.util.Response;
import cn.cesgroup.cesweb.common.core.util.SpringContextHolder;
import cn.cesgroup.cesweb.common.core.util.WebUtils;
import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * 登录逻辑验证码处理
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 7, 2020 4:50:44 PM
 * @version 1.0.2020
 */
@Slf4j
@Component
@AllArgsConstructor
public class ValidateCodeGatewayFilter extends AbstractGatewayFilterFactory<Object> {

	private final ObjectMapper objectMapper;

	private final RedisTemplate<String, ?> redisTemplate;

	private final FilterIgnorePropertiesConfig filterIgnorePropertiesConfig;

	@Override
	public GatewayFilter apply(Object config) {
		return (exchange, chain) -> {
			ServerHttpRequest request = exchange.getRequest();

			// 不是登录请求，直接向下执行
			if (!StrUtil.containsAnyIgnoreCase(request.getURI().getPath(), SecurityConstants.OAUTH_TOKEN_URL, SecurityConstants.SMS_TOKEN_URL, SecurityConstants.SOCIAL_TOKEN_URL)) {
				return chain.filter(exchange);
			}

			// 刷新token，直接向下执行
			String grantType = request.getQueryParams().getFirst("grant_type");
			if (StrUtil.equals(SecurityConstants.REFRESH_TOKEN, grantType)) {
				return chain.filter(exchange);
			}

			// 终端设置不校验， 直接向下执行
			try {
				String header = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
				String clientId = WebUtils.extractClientId(header).orElse(null);
				if (StrUtil.isNotBlank(clientId) && filterIgnorePropertiesConfig.getClients().contains(clientId)) {
					return chain.filter(exchange);
				}

				// 如果是社交登录，判断是否包含SMS
				if (StrUtil.containsAnyIgnoreCase(request.getURI().getPath(), SecurityConstants.SOCIAL_TOKEN_URL)) {
					String mobile = request.getQueryParams().getFirst("mobile");
					if (StrUtil.containsAny(mobile, LoginTypeEnum.SMS.getType())) {
						throw new ValidateCodeException("验证码不合法");
					}
					else {
						return chain.filter(exchange);
					}
				}

				// 校验验证码
				checkCode(request);
			}
			catch (Exception e) {
				ServerHttpResponse response = exchange.getResponse();
				response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
				response.setStatusCode(HttpStatus.PRECONDITION_REQUIRED);
				try {
					return response.writeWith(Mono.just(
							response.bufferFactory().wrap(objectMapper.writeValueAsBytes(Response.failed(e.getMessage())))));
				}
				catch (JsonProcessingException e1) {
					log.error("对象输出异常", e1);
				}
			}

			return chain.filter(exchange);
		};
	}

	/**
	 * 检查code
	 * @param request
	 */
	@SneakyThrows
	private void checkCode(ServerHttpRequest request) {
		String code = request.getQueryParams().getFirst("code");

		if (StrUtil.isBlank(code)) {
			throw new ValidateCodeException("验证码不能为空");
		}

		String randomStr = request.getQueryParams().getFirst("randomStr");

		// 若是滑块登录
		if (CommonConstants.IMAGE_CODE_TYPE.equalsIgnoreCase(randomStr)) {
			CaptchaService captchaService = SpringContextHolder.getBean(CaptchaService.class);
			CaptchaVO vo = new CaptchaVO();
			vo.setCaptchaVerification(code);
			if (!captchaService.verification(vo).isSuccess()) {
				throw new ValidateCodeException("验证码不能为空");
			}

			return;
		}

		// https://gitee.com/log4j/pig/issues/IWA0D
		String mobile = request.getQueryParams().getFirst("mobile");
		if (StrUtil.isNotBlank(mobile)) {
			randomStr = mobile;
		}

		String key = CacheConstants.DEFAULT_CODE_KEY + randomStr;
		redisTemplate.setKeySerializer(new StringRedisSerializer());

		if (!redisTemplate.hasKey(key)) {
			throw new ValidateCodeException("验证码不合法");
		}

		Object codeObj = redisTemplate.opsForValue().get(key);

		if (codeObj == null) {
			throw new ValidateCodeException("验证码不合法");
		}

		String saveCode = codeObj.toString();
		if (StrUtil.isBlank(saveCode)) {
			redisTemplate.delete(key);
			throw new ValidateCodeException("验证码不合法");
		}

		if (!StrUtil.equals(saveCode, code)) {
			redisTemplate.delete(key);
			throw new ValidateCodeException("验证码不合法");
		}

		redisTemplate.delete(key);
	}

}
