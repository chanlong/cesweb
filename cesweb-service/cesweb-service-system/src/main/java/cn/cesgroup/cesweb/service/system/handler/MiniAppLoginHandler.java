package cn.cesgroup.cesweb.service.system.handler;

import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

import cn.cesgroup.cesweb.api.system.dto.UserInfo;
import cn.cesgroup.cesweb.api.system.entity.SysSocialDetails;
import cn.cesgroup.cesweb.api.system.entity.SysUser;
import cn.cesgroup.cesweb.common.core.constant.SecurityConstants;
import cn.cesgroup.cesweb.common.core.constant.enums.LoginTypeEnum;
import cn.cesgroup.cesweb.service.system.mapper.SysSocialDetailsMapper;
import cn.cesgroup.cesweb.service.system.service.SysUserService;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 微信小程序
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 13, 2020 3:57:04 PM
 * @version 1.0.2020
 */
@Slf4j
@Component("MINI")
@AllArgsConstructor
public class MiniAppLoginHandler extends AbstractLoginHandler {

	private final SysUserService sysUserService;

	private final SysSocialDetailsMapper sysSocialDetailsMapper;

	/**
	 * 小程序登录传入code
	 * <p>
	 * 通过code 调用qq 获取唯一标识
	 * @param code
	 * @return
	 */
	@Override
	public String identify(String code) {
		SysSocialDetails condition = new SysSocialDetails();
		condition.setType(LoginTypeEnum.MINI_APP.getType());
		SysSocialDetails socialDetails = sysSocialDetailsMapper.selectOne(new QueryWrapper<>(condition));

		String url = String.format(SecurityConstants.MINI_APP_AUTHORIZATION_CODE_URL, socialDetails.getAppId(),
				socialDetails.getAppSecret(), code);
		String result = HttpUtil.get(url);
		log.debug("微信小程序响应报文:{}", result);

		Object obj = JSONUtil.parseObj(result).get("openid");
		return obj.toString();
	}

	/**
	 * openId 获取用户信息
	 * @param openId
	 * @return
	 */
	@Override
	public UserInfo info(String openId) {
		SysUser user = sysUserService.getOne(Wrappers.<SysUser>query().lambda().eq(SysUser::getMiniOpenid, openId));

		if (user == null) {
			log.info("微信小程序未绑定:{}", openId);
			return null;
		}
		return sysUserService.findUserInfo(user);
	}

}
