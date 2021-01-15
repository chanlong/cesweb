package cn.cesgroup.cesweb.common.core.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>描述: 流程状态</p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 7, 2020 1:56:27 PM
 * @version 1.0.2020
 */
@Getter
@AllArgsConstructor
public enum ProcessStatusEnum {

	/**
	 * 图片资源
	 */
	ACTIVE("active", "图片资源"),

	/**
	 * xml资源
	 */
	SUSPEND("suspend", "xml资源");

	/**
	 * 类型
	 */
	private final String status;

	/**
	 * 描述
	 */
	private final String description;

}
