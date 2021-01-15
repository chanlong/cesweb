package cn.cesgroup.cesweb.common.core.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>描述: 菜单类型</p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 7, 2020 1:55:50 PM
 * @version 1.0.2020
 */
@Getter
@AllArgsConstructor
public enum MenuTypeEnum {

	/**
	 * 左侧菜单
	 */
	LEFT_MENU("0", "left"),

	/**
	 * 顶部菜单
	 */
	TOP_MENU("2", "top"),

	/**
	 * 按钮
	 */
	BUTTON("1", "button");

	/**
	 * 类型
	 */
	private String type;

	/**
	 * 描述
	 */
	private String description;

}
