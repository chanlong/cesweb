package cn.cesgroup.cesweb.service.generate.entity;

import lombok.Data;

/**
 * 生成配置
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 13, 2020 12:11:52 PM
 * @version 1.0.2020
 */
@Data
public class GenConfig {

	/**
	 * 数据源name
	 */
	private String dsName;

	/**
	 * 包名
	 */
	private String packageName;

	/**
	 * 作者
	 */
	private String author;

	/**
	 * 模块名称
	 */
	private String moduleName;

	/**
	 * 表前缀
	 */
	private String tablePrefix;

	/**
	 * 表名称
	 */
	private String tableName;

	/**
	 * 表备注
	 */
	private String comments;

	/**
	 * 代码风格 0 - avue 1 - element
	 */
	private String style;

}
