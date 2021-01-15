package cn.cesgroup.cesweb.service.system.service.impl;

import java.util.Map;

import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cn.cesgroup.cesweb.api.system.dto.UserInfo;
import cn.cesgroup.cesweb.api.system.entity.SysSocialDetails;
import cn.cesgroup.cesweb.api.system.entity.SysUser;
import cn.cesgroup.cesweb.common.core.constant.CacheConstants;
import cn.cesgroup.cesweb.common.core.constant.enums.LoginTypeEnum;
import cn.cesgroup.cesweb.common.security.util.SecurityUtils;
import cn.cesgroup.cesweb.service.system.handler.LoginHandler;
import cn.cesgroup.cesweb.service.system.mapper.SysSocialDetailsMapper;
import cn.cesgroup.cesweb.service.system.mapper.SysUserMapper;
import cn.cesgroup.cesweb.service.system.service.SysSocialDetailsService;
import lombok.AllArgsConstructor;

/**
 * 服务实现类
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 13, 2020 3:24:57 PM
 * @version 1.0.2020
 */
@AllArgsConstructor
@Service("sysSocialDetailsService")
public class SysSocialDetailsServiceImpl extends ServiceImpl<SysSocialDetailsMapper, SysSocialDetails> implements SysSocialDetailsService {

	private final Map<String, LoginHandler> loginHandlerMap;

	private final CacheManager cacheManager;

	private final SysUserMapper sysUserMapper;

	/**
	 * 绑定社交账号
	 * @param type type
	 * @param code code
	 * @return
	 */
	@Override
	public Boolean bindSocial(String type, String code) {
		String identify = loginHandlerMap.get(type).identify(code);
		SysUser sysUser = sysUserMapper.selectById(SecurityUtils.getUser().getId());

		if (LoginTypeEnum.GITEE.getType().equals(type)) {
			sysUser.setGiteeLogin(identify);
		}
		else if (LoginTypeEnum.OSC.getType().equals(type)) {
			sysUser.setOscId(identify);
		}
		else if (LoginTypeEnum.WECHAT.getType().equals(type)) {
			sysUser.setWxOpenid(identify);
		}
		else if (LoginTypeEnum.QQ.getType().equals(type)) {
			sysUser.setQqOpenid(identify);
		}
		else if (LoginTypeEnum.MINI_APP.getType().equals(type)) {
			sysUser.setMiniOpenid(identify);
		}

		sysUserMapper.updateById(sysUser);
		// 更新緩存
		cacheManager.getCache(CacheConstants.USER_DETAILS).evict(sysUser.getUsername());
		return Boolean.TRUE;
	}

	/**
	 * 根据入参查询用户信息
	 * @param inStr TYPE@code
	 * @return
	 */
	@Override
	public UserInfo getUserInfo(String inStr) {
		String[] inStrs = inStr.split(StringPool.AT);
		String type = inStrs[0];
		String loginStr = inStrs[1];
		return loginHandlerMap.get(type).handle(loginStr);
	}

}
