package cn.cesgroup.cesweb.service.auth.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cn.cesgroup.cesweb.api.system.dto.UserInfo;
import cn.cesgroup.cesweb.api.system.entity.SysUser;
import cn.cesgroup.cesweb.api.system.feign.RemoteUserService;
import cn.cesgroup.cesweb.common.core.constant.CacheConstants;
import cn.cesgroup.cesweb.common.core.constant.CommonConstants;
import cn.cesgroup.cesweb.common.core.constant.SecurityConstants;
import cn.cesgroup.cesweb.common.core.util.Response;
import cn.cesgroup.cesweb.common.security.service.OAuth2User;
import cn.cesgroup.cesweb.common.security.service.OAuth2UserDetailsService;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

/**
 * 用户详细信息
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 14, 2020 9:45:14 AM
 * @version 1.0.2020
 */
@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements OAuth2UserDetailsService {

	private final RemoteUserService remoteUserService;

	private final CacheManager cacheManager;

	/**
	 * 用户密码登录
	 * @param username 用户名
	 * @throws UsernameNotFoundException
	 */
	@Override
	@SneakyThrows
	public UserDetails loadUserByUsername(String username) {
		Cache cache = cacheManager.getCache(CacheConstants.USER_DETAILS);
		if (cache != null && cache.get(username) != null) {
			return (OAuth2User) cache.get(username).get();
		}

		Response<UserInfo> result = remoteUserService.info(username, SecurityConstants.FROM_IN);
		UserDetails userDetails = getUserDetails(result);
		cache.put(username, userDetails);
		return userDetails;
	}

	/**
	 * 根据社交登录code 登录
	 * @param inStr TYPE@CODE
	 * @return UserDetails
	 * @throws UsernameNotFoundException
	 */
	@Override
	@SneakyThrows
	public UserDetails loadUserBySocial(String inStr) {
		return getUserDetails(remoteUserService.social(inStr, SecurityConstants.FROM_IN));
	}

	/**
	 * 构建userdetails
	 * @param result 用户信息
	 */
	private UserDetails getUserDetails(Response<UserInfo> result) {
		if (result == null || result.getData() == null) {
			throw new UsernameNotFoundException("用户不存在");
		}

		UserInfo info = result.getData();
		Set<String> dbAuthsSet = new HashSet<>();
		if (ArrayUtil.isNotEmpty(info.getRoles())) {
			// 获取角色
			Arrays.stream(info.getRoles()).forEach(roleId -> dbAuthsSet.add(SecurityConstants.ROLE + roleId));
			// 获取资源
			dbAuthsSet.addAll(Arrays.asList(info.getPermissions()));
		}
		Collection<? extends GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(dbAuthsSet.toArray(new String[0]));
		SysUser user = info.getSysUser();
		boolean enabled = StrUtil.equals(user.getLockFlag(), CommonConstants.STATUS_NORMAL);
		
		// 构造security用户
		return new OAuth2User(user.getUserId(), 
							  user.getDeptId(), 
							  user.getPhone(), 
							  user.getAvatar(), 
							  user.getTenantId(), 
							  user.getUsername(), 
							  SecurityConstants.BCRYPT + user.getPassword(), 
							  enabled, 
							  true, 
							  true, 
							  !CommonConstants.STATUS_LOCK.equals(user.getLockFlag()), 
							  authorities);
	}

}
