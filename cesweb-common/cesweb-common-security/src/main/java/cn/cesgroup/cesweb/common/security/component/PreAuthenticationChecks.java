package cn.cesgroup.cesweb.common.security.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;

/**
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 10, 2020 12:50:34 PM
 * @version 1.0.2020
 */
@Slf4j
public class PreAuthenticationChecks implements UserDetailsChecker {

	private MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();

	@Override
	public void check(UserDetails user) {
		if (!user.isAccountNonLocked()) {
			log.debug("User account is locked");

			throw new LockedException(messages.getMessage("AbstractUserDetailsAuthenticationProvider.locked", "User account is locked"));
		}

		if (!user.isEnabled()) {
			log.debug("User account is disabled");

			throw new DisabledException(messages.getMessage("AbstractUserDetailsAuthenticationProvider.disabled", "User is disabled"));
		}

		if (!user.isAccountNonExpired()) {
			log.debug("User account is expired");

			throw new AccountExpiredException(messages.getMessage("AbstractUserDetailsAuthenticationProvider.expired", "User account has expired"));
		}
	}

}
