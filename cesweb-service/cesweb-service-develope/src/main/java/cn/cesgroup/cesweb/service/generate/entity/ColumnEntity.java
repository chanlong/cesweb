package cn.cesgroup.cesweb.service.generate.entity;

import lombok.Data;

/**
 * 列属性： https://blog.csdn.net/lkforce/article/details/79557482
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 13, 2020 12:12:16 PM
 * @version 1.0.2020
 */
@Data
public class ColumnEntity {

	/**
	 * 列表
	 */
	private String columnName;

	/**
	 * 数据类型
	 */
	private String dataType;

	/**
	 * JAVA 数据类型
	 */
	private String javaType;

	/**
	 * 备注
	 */
	private String comments;

	/**
	 * 驼峰属性
	 */
	private String caseAttrName;

	/**
	 * 普通属性
	 */
	private String lowerAttrName;

	/**
	 * 属性类型
	 */
	private String attrType;

	/**
	 * 其他信息
	 */
	private String extra;

	/**
	 * 字段类型
	 */
	private String columnType;

	/**
	 * 是否可以为空
	 */
	private Boolean nullable;

	/**
	 * 是否隐藏
	 */
	private Boolean hidden;

}
