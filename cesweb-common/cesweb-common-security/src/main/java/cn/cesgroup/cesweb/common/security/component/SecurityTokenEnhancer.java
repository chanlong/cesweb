package cn.cesgroup.cesweb.common.security.component;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import cn.cesgroup.cesweb.common.core.constant.SecurityConstants;
import cn.cesgroup.cesweb.common.security.service.OAuth2User;

/**
 * token增强，客户端模式不增强。
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 10, 2020 2:21:53 PM
 * @version 1.0.2020
 */
public class SecurityTokenEnhancer implements TokenEnhancer {

	/**
	 * Provides an opportunity for customization of an access token (e.g. through its
	 * additional information map) during the process of creating a new token for use by a
	 * client.
	 * @param accessToken the current access token with its expiration and refresh token
	 * @param authentication the current authentication including client and user details
	 * @return a new token enhanced with additional information
	 */
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		if (SecurityConstants.CLIENT_CREDENTIALS.equals(authentication.getOAuth2Request().getGrantType())) {
			return accessToken;
		}

		final Map<String, Object> additionalInfo = new HashMap<>(8);
		OAuth2User oAuth2User = (OAuth2User) authentication.getUserAuthentication().getPrincipal();
		additionalInfo.put(SecurityConstants.DETAILS_USER, oAuth2User);
		additionalInfo.put(SecurityConstants.DETAILS_LICENSE, SecurityConstants.CES_LICENSE);
		additionalInfo.put(SecurityConstants.ACTIVE, Boolean.TRUE);
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
		return accessToken;
	}

}
