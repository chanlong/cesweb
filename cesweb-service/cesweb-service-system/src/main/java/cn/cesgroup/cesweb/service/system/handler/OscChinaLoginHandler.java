package cn.cesgroup.cesweb.service.system.handler;

import java.util.HashMap;
import java.util.Map;

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
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 开源中国登录
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 13, 2020 3:57:22 PM
 * @version 1.0.2020
 */
@Slf4j
@Component("OSC")
@AllArgsConstructor
public class OscChinaLoginHandler extends AbstractLoginHandler {

	private final SysSocialDetailsMapper sysSocialDetailsMapper;

	private final SysUserService sysUserService;

	/**
	 * 开源中国传入code
	 * <p>
	 * 通过code 调用qq 获取唯一标识
	 * @param code
	 * @return
	 */
	@Override
	public String identify(String code) {
		SysSocialDetails condition = new SysSocialDetails();
		condition.setType(LoginTypeEnum.OSC.getType());
		SysSocialDetails socialDetails = sysSocialDetailsMapper.selectOne(new QueryWrapper<>(condition));

		Map<String, Object> params = new HashMap<>(8);

		params.put("client_id", socialDetails.getAppId());
		params.put("client_secret", socialDetails.getAppSecret());
		params.put("grant_type", "authorization_code");
		params.put("redirect_uri", socialDetails.getRedirectUrl());
		params.put("code", code);
		params.put("dataType", "json");

		String result = HttpUtil.post(SecurityConstants.OSC_AUTHORIZATION_CODE_URL, params);
		log.debug("开源中国响应报文:{}", result);

		String accessToken = JSONUtil.parseObj(result).getStr("access_token");

		String url = String.format(SecurityConstants.OSC_USER_INFO_URL, accessToken);
		String resp = HttpUtil.get(url);
		log.debug("开源中国获取个人信息返回报文{}", resp);

		JSONObject userInfo = JSONUtil.parseObj(resp);
		// 开源中国唯一标识
		String id = userInfo.getStr("id");
		return id;
	}

	/**
	 * identify 获取用户信息
	 * @param identify 开源中国表示
	 * @return
	 */
	@Override
	public UserInfo info(String identify) {

		SysUser user = sysUserService.getOne(Wrappers.<SysUser>query().lambda().eq(SysUser::getOscId, identify));

		if (user == null) {
			log.info("开源中国未绑定:{}", identify);
			return null;
		}
		return sysUserService.findUserInfo(user);
	}

}
