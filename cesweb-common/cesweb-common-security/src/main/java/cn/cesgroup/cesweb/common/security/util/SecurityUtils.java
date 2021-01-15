package cn.cesgroup.cesweb.common.security.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import cn.cesgroup.cesweb.common.core.constant.SecurityConstants;
import cn.cesgroup.cesweb.common.security.service.OAuth2User;
import cn.hutool.core.util.StrUtil;
import lombok.experimental.UtilityClass;

/**
 * 安全工具类
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 10, 2020 1:39:35 PM
 * @version 1.0.2020
 */
@UtilityClass
public class SecurityUtils {

	/**
	 * 获取Authentication
	 */
	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	/**
	 * 获取用户
	 * @param authentication
	 * @return PigxUser
	 * <p>
	 */
	public OAuth2User getUser(Authentication authentication) {
		Object principal = authentication.getPrincipal();
		if (principal instanceof OAuth2User) {
			return (OAuth2User) principal;
		}
		return null;
	}

	/**
	 * 获取用户
	 */
	public OAuth2User getUser() {
		Authentication authentication = getAuthentication();
		return getUser(authentication);
	}

	/**
	 * 获取用户角色信息
	 * @return 角色集合
	 */
	public List<String> getRoles() {
		Authentication authentication = getAuthentication();
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

		List<String> roleIds = new ArrayList<>();
		authorities.stream().filter(granted -> StrUtil.startWith(granted.getAuthority(), SecurityConstants.ROLE)).forEach(granted -> {
			roleIds.add(StrUtil.removePrefix(granted.getAuthority(), SecurityConstants.ROLE));
		});
		return roleIds;
	}

}
