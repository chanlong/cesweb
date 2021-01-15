package cn.cesgroup.cesweb.service.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import cn.cesgroup.cesweb.common.core.constant.SecurityConstants;
import cn.cesgroup.cesweb.common.data.tenant.TenantContextHolder;
import cn.cesgroup.cesweb.common.security.component.WebResponseExceptionTranslator;
import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

/**
 * 认证服务器配置
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 14, 2020 9:29:29 AM
 * @version 1.0.2020
 */
@Configuration
@AllArgsConstructor
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	private final AuthenticationManager authenticationManagerBean;

	private final RedisConnectionFactory redisConnectionFactory;
	
	private final ClientDetailsService clientDetailsServiceImpl;

	private final UserDetailsService oAuth2UserDetailsService;

	private final TokenEnhancer securityTokenEnhancer;

	@Override
	@SneakyThrows
	public void configure(ClientDetailsServiceConfigurer clients) {
		clients.withClientDetails(clientDetailsServiceImpl);
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
		oauthServer.allowFormAuthenticationForClients().checkTokenAccess("isAuthenticated()");
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
		endpoints.allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
				 .tokenStore(tokenStore())
				 .tokenEnhancer(securityTokenEnhancer)
				 .userDetailsService(oAuth2UserDetailsService)
				 .authenticationManager(authenticationManagerBean)
				 .reuseRefreshTokens(false)
				 .pathMapping("/oauth/confirm_access", "/token/confirm_access")
				 .exceptionTranslator(new WebResponseExceptionTranslator());
	}

	@Bean
	public TokenStore tokenStore() {
		RedisTokenStore tokenStore = new RedisTokenStore(redisConnectionFactory);
		tokenStore.setPrefix(SecurityConstants.CES_PREFIX + SecurityConstants.OAUTH_PREFIX);
		tokenStore.setAuthenticationKeyGenerator(new DefaultAuthenticationKeyGenerator() {
			@Override
			public String extractKey(OAuth2Authentication authentication) {
				return super.extractKey(authentication) + StrUtil.COLON + TenantContextHolder.getTenantId();
			}
		});
		return tokenStore;
	}

}
