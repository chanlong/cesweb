package cn.cesgroup.cesweb.common.core.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>描述: 字典类型</p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 7, 2020 1:54:55 PM
 * @version 1.0.2020
 */
@Getter
@AllArgsConstructor
public enum DictTypeEnum {

	/**
	 * 字典类型-系统内置（不可修改）
	 */
	SYSTEM("1", "系统内置"),

	/**
	 * 字典类型-业务类型
	 */
	BIZ("0", "业务类");

	/**
	 * 类型
	 */
	private String type;

	/**
	 * 描述
	 */
	private String description;

}
