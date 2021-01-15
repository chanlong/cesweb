package cn.cesgroup.cesweb.service.system.handler;

import cn.cesgroup.cesweb.api.system.dto.UserInfo;

/**
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 13, 2020 3:56:28 PM
 * @version 1.0.2020
 */
public abstract class AbstractLoginHandler implements LoginHandler {

	/***
	 * 数据合法性校验
	 * @param loginStr 通过用户传入获取唯一标识
	 * @return 默认不校验
	 */
	@Override
	public Boolean check(String loginStr) {
		return true;
	}

	/**
	 * 处理方法
	 * @param loginStr 登录参数
	 * @return
	 */
	@Override
	public UserInfo handle(String loginStr) {
		if (!check(loginStr)) {
			return null;
		}

		String identify = identify(loginStr);
		return info(identify);
	}

}
