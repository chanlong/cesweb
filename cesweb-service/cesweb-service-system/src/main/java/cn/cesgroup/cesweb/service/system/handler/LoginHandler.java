package cn.cesgroup.cesweb.service.system.handler;

import cn.cesgroup.cesweb.api.system.dto.UserInfo;

/**
 * 登录处理器
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 13, 2020 3:26:13 PM
 * @version 1.0.2020
 */
public interface LoginHandler {

	/***
	 * 数据合法性校验
	 * @param loginStr 通过用户传入获取唯一标识
	 */
	Boolean check(String loginStr);

	/**
	 * 通过用户传入获取唯一标识
	 * @param loginStr
	 */
	String identify(String loginStr);

	/**
	 * 通过openId 获取用户信息
	 * @param identify
	 */
	UserInfo info(String identify);

	/**
	 * 处理方法
	 * @param loginStr 登录参数
	 */
	UserInfo handle(String loginStr);

}
