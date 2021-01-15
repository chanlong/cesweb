package cn.cesgroup.cesweb.common.core.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>描述: 社交登录类型</p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 7, 2020 1:55:24 PM
 * @version 1.0.2020
 */
@Getter
@AllArgsConstructor
public enum LoginTypeEnum {

	/**
	 * 账号密码登录
	 */
	PWD("PWD", "账号密码登录"),

	/**
	 * 验证码登录
	 */
	SMS("SMS", "验证码登录"),

	/**
	 * QQ登录
	 */
	QQ("QQ", "QQ登录"),

	/**
	 * 微信登录
	 */
	WECHAT("WX", "微信登录"),

	/**
	 * 微信小程序
	 */
	MINI_APP("MINI", "微信小程序"),

	/**
	 * 码云登录
	 */
	GITEE("GITEE", "码云登录"),

	/**
	 * 开源中国登录
	 */
	OSC("OSC", "开源中国登录");

	/**
	 * 类型
	 */
	private String type;

	/**
	 * 描述
	 */
	private String description;

}
