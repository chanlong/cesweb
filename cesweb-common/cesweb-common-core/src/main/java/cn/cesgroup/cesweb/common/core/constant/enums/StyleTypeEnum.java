package cn.cesgroup.cesweb.common.core.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>描述: 前端类型类型</p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 7, 2020 1:57:39 PM
 * @version 1.0.2020
 */
@Getter
@AllArgsConstructor
public enum StyleTypeEnum {

	/**
	 * 前端类型-avue 风格
	 */
	AVUE("0", "avue 风格"),

	/**
	 * 前端类型-element 风格
	 */
	ELEMENT("1", "element 风格");

	/**
	 * 类型
	 */
	private String style;

	/**
	 * 描述
	 */
	private String description;

}
