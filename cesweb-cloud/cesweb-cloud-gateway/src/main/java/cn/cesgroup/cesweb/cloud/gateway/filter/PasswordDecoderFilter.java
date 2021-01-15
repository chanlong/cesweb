package cn.cesgroup.cesweb.cloud.gateway.filter;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.factory.rewrite.CachedBodyOutputMessage;
import org.springframework.cloud.gateway.support.BodyInserterContext;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ReactiveHttpOutputMessage;
import org.springframework.http.codec.HttpMessageReader;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerStrategies;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ServerWebExchange;

import cn.cesgroup.cesweb.common.core.constant.SecurityConstants;
import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 密码解密工具类
 * <p>描述: 参考 ModifyRequestBodyGatewayFilterFactory 实现</p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 7, 2020 4:45:30 PM
 * @version 1.0.2020
 */
@Slf4j
@Component
public class PasswordDecoderFilter extends AbstractGatewayFilterFactory<Object> {

	private final List<HttpMessageReader<?>> messageReaders = HandlerStrategies.withDefaults().messageReaders();

	private static final String PASSWORD = "password";

	private static final String KEY_ALGORITHM = "AES";

	@Value("${security.encode.key:1234567812345678}")
	private String encodeKey;

	@Override
	public GatewayFilter apply(Object config) {
		return (exchange, chain) -> {
			ServerHttpRequest request = exchange.getRequest();
			// 不是登录请求，直接向下执行
			if (!StrUtil.containsAnyIgnoreCase(request.getURI().getPath(), SecurityConstants.OAUTH_TOKEN_URL)) {
				return chain.filter(exchange);
			}

			// 刷新token，直接向下执行
			String grantType = request.getQueryParams().getFirst("grant_type");
			if (StrUtil.equals(SecurityConstants.REFRESH_TOKEN, grantType)) {
				return chain.filter(exchange);
			}

			Class<String> inClass = String.class;
			Class<String> outClass = String.class;
			ServerRequest serverRequest = ServerRequest.create(exchange, messageReaders);

			// 解密生成新的报文
			Mono<String> modifiedBody = serverRequest.bodyToMono(inClass).flatMap(decryptAES());

			BodyInserter<Mono<String>, ReactiveHttpOutputMessage> bodyInserter = BodyInserters.fromPublisher(modifiedBody, outClass);
			HttpHeaders headers = new HttpHeaders();
			headers.putAll(exchange.getRequest().getHeaders());
			headers.remove(HttpHeaders.CONTENT_LENGTH);

			headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE);
			CachedBodyOutputMessage outputMessage = new CachedBodyOutputMessage(exchange, headers);
			return bodyInserter.insert(outputMessage, new BodyInserterContext()).then(Mono.defer(() -> {
				ServerHttpRequest decorator = decorate(exchange, headers, outputMessage);
				return chain.filter(exchange.mutate().request(decorator).build());
			}));
		};
	}

	/**
	 * 原文解密
	 * @return
	 */
	private Function<String, Mono<String>> decryptAES() {
		return param -> {
			// 构建前端对应解密AES 因子
			AES aes = new AES(Mode.CBC, Padding.NoPadding, new SecretKeySpec(encodeKey.getBytes(), KEY_ALGORITHM), new IvParameterSpec(encodeKey.getBytes()));

			// 获取请求密码并解密
			Map<String, String> inParamsMap = HttpUtil.decodeParamMap(param, CharsetUtil.CHARSET_UTF_8);
			if (inParamsMap.containsKey(PASSWORD)) {
				byte[] result = aes.decrypt(Base64.decode(inParamsMap.get(PASSWORD).getBytes(StandardCharsets.UTF_8)));
				String password = new String(result, StandardCharsets.UTF_8);

				// 返回修改后报文字符
				inParamsMap.put(PASSWORD, password.trim());
			}
			else {
				log.error("非法请求数据:{}", param);
			}
			return Mono.just(HttpUtil.toParams(inParamsMap));
		};
	}

	/**
	 * 报文转换
	 * @return
	 */
	private ServerHttpRequestDecorator decorate(ServerWebExchange exchange, HttpHeaders headers, CachedBodyOutputMessage outputMessage) {
		return new ServerHttpRequestDecorator(exchange.getRequest()) {
			@Override
			public HttpHeaders getHeaders() {
				long contentLength = headers.getContentLength();
				HttpHeaders httpHeaders = new HttpHeaders();
				httpHeaders.putAll(super.getHeaders());
				if (contentLength > 0) {
					httpHeaders.setContentLength(contentLength);
				}
				else {
					httpHeaders.set(HttpHeaders.TRANSFER_ENCODING, "chunked");
				}
				return httpHeaders;
			}

			@Override
			public Flux<DataBuffer> getBody() {
				return outputMessage.getBody();
			}
		};
	}

}
