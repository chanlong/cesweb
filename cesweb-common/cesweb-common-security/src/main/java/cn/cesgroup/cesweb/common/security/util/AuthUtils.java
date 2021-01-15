package cn.cesgroup.cesweb.common.security.util;

import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;

import cn.hutool.core.codec.Base64;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

/**
 * 认证授权相关工具类
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 10, 2020 1:38:38 PM
 * @version 1.0.2020
 */
@UtilityClass
public class AuthUtils {

	private final String BASIC_ = "Basic ";

	/**
	 * 从header 请求中的clientId/clientsecect
	 * @param header header中的参数
	 * @throws RuntimeException if the Basic header is not present or is not valid Base64
	 */
	@SneakyThrows
	public String[] extractAndDecodeHeader(String header) {

		byte[] base64Token = header.substring(6).getBytes("UTF-8");
		byte[] decoded;
		try {
			decoded = Base64.decode(base64Token);
		}
		catch (IllegalArgumentException e) {
			throw new RuntimeException("Failed to decode basic authentication token");
		}

		String token = new String(decoded, StandardCharsets.UTF_8);

		int delim = token.indexOf(":");

		if (delim == -1) {
			throw new RuntimeException("Invalid basic authentication token");
		}
		return new String[] { token.substring(0, delim), token.substring(delim + 1) };
	}

	/**
	 * *从header 请求中的clientId/clientsecect
	 * @param request
	 */
	@SneakyThrows
	public String[] extractAndDecodeHeader(HttpServletRequest request) {
		String header = request.getHeader(HttpHeaders.AUTHORIZATION);

		if (header == null || !header.startsWith(BASIC_)) {
			throw new RuntimeException("请求头中client信息为空");
		}

		return extractAndDecodeHeader(header);
	}

}
