package cn.cesgroup.cesweb.common.security.component;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.PatternMatchUtils;
import org.springframework.util.StringUtils;

import cn.hutool.core.util.ArrayUtil;

/**
 * 接口权限判断工具
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 10, 2020 10:59:16 AM
 * @version 1.0.2020
 */
@Component("pms")
public class PermissionService {

	/**
	 * 判断接口是否有任意xxx，xxx权限
	 * @param permissions 权限
	 * @return {boolean}
	 */
	public boolean hasPermission(String... permissions) {
		if (ArrayUtil.isEmpty(permissions)) {
			return false;
		}
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {
			return false;
		}
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		return authorities.stream().map(GrantedAuthority::getAuthority).filter(StringUtils::hasText).anyMatch(x -> PatternMatchUtils.simpleMatch(permissions, x));
	}

}
