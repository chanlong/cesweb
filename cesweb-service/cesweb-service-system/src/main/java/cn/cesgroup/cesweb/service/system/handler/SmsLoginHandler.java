package cn.cesgroup.cesweb.service.system.handler;

import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;

import cn.cesgroup.cesweb.api.system.dto.UserInfo;
import cn.cesgroup.cesweb.api.system.entity.SysUser;
import cn.cesgroup.cesweb.service.system.service.SysUserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 手机短信登录
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 13, 2020 3:57:44 PM
 * @version 1.0.2020
 */
@Slf4j
@Component("SMS")
@AllArgsConstructor
public class SmsLoginHandler extends AbstractLoginHandler {

	private final SysUserService sysUserService;

	/**
	 * 验证码登录传入为手机号 不用不处理
	 * @param mobile
	 */
	@Override
	public String identify(String mobile) {
		return mobile;
	}

	/**
	 * 通过mobile 获取用户信息
	 * @param identify
	 */
	@Override
	public UserInfo info(String identify) {
		SysUser user = sysUserService.getOne(Wrappers.<SysUser>query().lambda().eq(SysUser::getPhone, identify));

		if (user == null) {
			log.info("手机号未注册:{}", identify);
			return null;
		}
		return sysUserService.findUserInfo(user);
	}

}
