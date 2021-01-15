package cn.cesgroup.cesweb.common.core.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>描述: 资源类型</p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 7, 2020 1:56:45 PM
 * @version 1.0.2020
 */
@Getter
@AllArgsConstructor
public enum ResourceTypeEnum {

	/**
	 * 图片资源
	 */
	IMAGE("image", "图片资源"),

	/**
	 * xml资源
	 */
	XML("xml", "xml资源");

	/**
	 * 类型
	 */
	private final String type;

	/**
	 * 描述
	 */
	private final String description;

}
