package cn.cesgroup.cesweb.service.generate.entity;

import lombok.Data;

import java.util.List;

/**
 * 表属性： https://blog.csdn.net/lkforce/article/details/79557482
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 13, 2020 12:10:26 PM
 * @version 1.0.2020
 */
@Data
public class TableEntity {

	/**
	 * 名称
	 */
	private String tableName;

	/**
	 * 备注
	 */
	private String comments;

	/**
	 * 主键
	 */
	private ColumnEntity pk;

	/**
	 * 列名
	 */
	private List<ColumnEntity> columns;

	/**
	 * 驼峰类型
	 */
	private String caseClassName;

	/**
	 * 普通类型
	 */
	private String lowerClassName;

}
