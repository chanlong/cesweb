package cn.cesgroup.cesweb.service.auth.endpoint;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.cache.CacheManager;
import org.springframework.data.redis.core.ConvertingCursor;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cn.cesgroup.cesweb.common.core.constant.CacheConstants;
import cn.cesgroup.cesweb.common.core.constant.PaginationConstants;
import cn.cesgroup.cesweb.common.core.constant.SecurityConstants;
import cn.cesgroup.cesweb.common.core.util.Response;
import cn.cesgroup.cesweb.common.data.tenant.TenantContextHolder;
import cn.cesgroup.cesweb.common.security.annotation.Inner;
import cn.cesgroup.cesweb.common.security.util.SecurityUtils;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 删除token端点
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 14, 2020 9:35:58 AM
 * @version 1.0.2020
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/token")
public class AuthTokenEndpoint {

	private static final String CES_OAUTH_ACCESS = SecurityConstants.CES_PREFIX + SecurityConstants.OAUTH_PREFIX + "auth_to_access:";
	
	private final TokenStore tokenStore;

	private final CacheManager cacheManager;
	
	private final ClientDetailsService clientDetailsService;
	
	private final RedisTemplate<String, String> redisTemplate;

	/**
	 * 认证页面
	 * @param modelAndView
	 * @param error 表单登录失败处理回调的错误信息
	 * @return ModelAndView
	 */
	@GetMapping("/login")
	public ModelAndView require(ModelAndView modelAndView, @RequestParam(required = false) String error) {
		modelAndView.setViewName("ftl/login");
		modelAndView.addObject("error", error);
		return modelAndView;
	}

	/**
	 * 确认授权页面
	 * @param request
	 * @param session
	 * @param modelAndView
	 */
	@SuppressWarnings("unchecked")
	@GetMapping("/confirm_access")
	public ModelAndView confirm(HttpServletRequest request, HttpSession session, ModelAndView modelAndView) {
		Map<String, Object> scopeList = (Map<String, Object>) request.getAttribute("scopes");
		modelAndView.addObject("scopeList", scopeList != null ? scopeList.keySet() : "");

		Object auth = session.getAttribute("authorizationRequest");
		if (auth != null) {
			AuthorizationRequest authorizationRequest = (AuthorizationRequest) auth;
			ClientDetails clientDetails = clientDetailsService.loadClientByClientId(authorizationRequest.getClientId());
			modelAndView.addObject("app", clientDetails.getAdditionalInformation());
			modelAndView.addObject("user", SecurityUtils.getUser());
		}

		modelAndView.setViewName("ftl/confirm");
		return modelAndView;
	}

	/**
	 * 退出token
	 * @param authHeader Authorization
	 */
	@DeleteMapping("/logout")
	public Response<Boolean> logout(@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authHeader) {
		if (StrUtil.isBlank(authHeader)) {
			return Response.ok(Boolean.FALSE, "退出失败，token 为空");
		}

		String tokenValue = authHeader.replace(OAuth2AccessToken.BEARER_TYPE, StrUtil.EMPTY).trim();
		return delToken(tokenValue);
	}

	/**
	 * 令牌管理调用
	 * @param token token
	 * @return
	 */
	@Inner
	@DeleteMapping("/{token}")
	public Response<Boolean> delToken(@PathVariable("token") String token) {
		OAuth2AccessToken accessToken = tokenStore.readAccessToken(token);
		if (accessToken == null || StrUtil.isBlank(accessToken.getValue())) {
			return Response.ok(Boolean.TRUE, "退出失败，token 无效");
		}

		OAuth2Authentication auth2Authentication = tokenStore.readAuthentication(accessToken);
		// 清空用户信息
		cacheManager.getCache(CacheConstants.USER_DETAILS).evict(auth2Authentication.getName());

		// 清空access token
		tokenStore.removeAccessToken(accessToken);

		// 清空 refresh token
		OAuth2RefreshToken refreshToken = accessToken.getRefreshToken();
		tokenStore.removeRefreshToken(refreshToken);
		return Response.ok();
	}

	/**
	 * 查询token
	 * @param params 分页参数
	 */
	@Inner
	@PostMapping("/page")
	public Response<?> tokenList(@RequestBody Map<String, Object> params) {
		// 根据分页参数获取对应数据
		String key = String.format("%s*:%s", CES_OAUTH_ACCESS, TenantContextHolder.getTenantId());
		List<String> pages = findKeysForPage(key, MapUtil.getInt(params, PaginationConstants.CURRENT), MapUtil.getInt(params, PaginationConstants.SIZE));
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
		Page<String> result = new Page<>(MapUtil.getInt(params, PaginationConstants.CURRENT), MapUtil.getInt(params, PaginationConstants.SIZE));
		result.setRecords(redisTemplate.opsForValue().multiGet(pages));
		result.setTotal(redisTemplate.keys(key).size());
		return Response.ok(result);
	}

	@SuppressWarnings("unchecked")
	private List<String> findKeysForPage(String patternKey, int pageNum, int pageSize) {
		ScanOptions options = ScanOptions.scanOptions().count(1000L).match(patternKey).build();
		RedisSerializer<String> redisSerializer = (RedisSerializer<String>) redisTemplate.getKeySerializer();
		Cursor<?> cursor = (Cursor<?>) redisTemplate.executeWithStickyConnection(redisConnection -> new ConvertingCursor<>(redisConnection.scan(options), redisSerializer::deserialize));
		List<String> result = new ArrayList<>();
		int tmpIndex = 0;
		int startIndex = (pageNum - 1) * pageSize;
		int end = pageNum * pageSize;

		assert cursor != null;
		while (cursor.hasNext()) {
			if (tmpIndex >= startIndex && tmpIndex < end) {
				result.add(cursor.next().toString());
				tmpIndex++;
				continue;
			}
			if (tmpIndex >= end) {
				break;
			}
			tmpIndex++;
			cursor.next();
		}

		try {
			cursor.close();
		}
		catch (IOException e) {
			log.error("关闭cursor 失败");
		}
		return result;
	}

}
