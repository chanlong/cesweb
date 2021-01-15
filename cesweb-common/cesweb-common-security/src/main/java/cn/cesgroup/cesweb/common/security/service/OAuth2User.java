package cn.cesgroup.cesweb.common.security.service;

import lombok.Getter;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * 扩展用户信息
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 10, 2020 1:46:58 PM
 * @version 1.0.2020
 */
public class OAuth2User extends User {

	private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

	/**
	 * 用户ID
	 */
	@Getter
	private String id;

	/**
	 * 部门ID
	 */
	@Getter
	private String deptId;

	/**
	 * 手机号
	 */
	@Getter
	private String phone;

	/**
	 * 头像
	 */
	@Getter
	private String avatar;

	/**
	 * 租户ID
	 */
	@Getter
	private String tenantId;

	/**
	 * Construct the <code>User</code> with the details required by
	 * {@link DaoAuthenticationProvider}.
	 * @param id 用户ID
	 * @param deptId 部门ID
	 * @param tenantId 租户ID
	 * @param username the username presented to the
	 * <code>DaoAuthenticationProvider</code>
	 * @param password the password that should be presented to the
	 * <code>DaoAuthenticationProvider</code>
	 * @param enabled set to <code>true</code> if the user is enabled
	 * @param accountNonExpired set to <code>true</code> if the account has not expired
	 * @param credentialsNonExpired set to <code>true</code> if the credentials have not expired
	 * @param accountNonLocked set to <code>true</code> if the account is not locked
	 * @param authorities the authorities that should be granted to the caller if they presented the correct username and password and the user is enabled. Not null.
	 * @throws IllegalArgumentException if a <code>null</code> value was passed either as a parameter or as an element in the <code>GrantedAuthority</code> collection
	 */
	public OAuth2User(String id, String deptId, String phone, String avatar, String tenantId, String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.id = id;
		this.phone = phone;
		this.avatar = avatar;
		this.deptId = deptId;
		this.tenantId = tenantId;
	}

}
