package cn.cesgroup.cesweb.service.system.handler;

import java.util.HashMap;

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
import cn.hutool.core.util.URLUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 码云登录
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 13, 2020 3:56:46 PM
 * @version 1.0.2020
 */
@Slf4j
@Component("GITEE")
@AllArgsConstructor
public class GiteeLoginHandler extends AbstractLoginHandler {

	private final SysSocialDetailsMapper sysSocialDetailsMapper;

	private final SysUserService sysUserService;

	/**
	 * 码云登录传入code
	 * <p>
	 * 通过code 调用qq 获取唯一标识
	 * @param code
	 * @return
	 */
	@Override
	public String identify(String code) {
		SysSocialDetails condition = new SysSocialDetails();
		condition.setType(LoginTypeEnum.GITEE.getType());
		SysSocialDetails socialDetails = sysSocialDetailsMapper.selectOne(new QueryWrapper<>(condition));

		String url = String.format(SecurityConstants.GITEE_AUTHORIZATION_CODE_URL, code, socialDetails.getAppId(),
				URLUtil.encode(socialDetails.getRedirectUrl()), socialDetails.getAppSecret());
		String result = HttpUtil.post(url, new HashMap<>(0));
		log.debug("码云响应报文:{}", result);

		String accessToken = JSONUtil.parseObj(result).getStr("access_token");
		String userUrl = String.format(SecurityConstants.GITEE_USER_INFO_URL, accessToken);
		String resp = HttpUtil.get(userUrl);
		log.debug("码云获取个人信息返回报文{}", resp);

		JSONObject userInfo = JSONUtil.parseObj(resp);
		// 码云唯一标识
		String login = userInfo.getStr("login");
		return login;
	}

	/**
	 * identify 获取用户信息
	 * @param identify identify
	 * @return
	 */
	@Override
	public UserInfo info(String identify) {

		SysUser user = sysUserService.getOne(Wrappers.<SysUser>query().lambda().eq(SysUser::getGiteeLogin, identify));

		if (user == null) {
			log.info("码云未绑定:{}", identify);
			return null;
		}
		return sysUserService.findUserInfo(user);
	}

}
