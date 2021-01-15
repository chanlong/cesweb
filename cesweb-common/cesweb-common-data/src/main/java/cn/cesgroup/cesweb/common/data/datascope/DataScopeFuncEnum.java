package cn.cesgroup.cesweb.common.data.datascope;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 数据权限函数类型
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 10, 2020 3:27:33 PM
 * @version 1.0.2020
 */
@Getter
@AllArgsConstructor
public enum DataScopeFuncEnum {

	/**
	 * 查询全部数据 SELECT * FROM (originSql) temp_data_scope WHERE temp_data_scope.dept_id IN
	 * (1)
	 */
	ALL("*", "全部"),

	/**
	 * 查询函数COUNT SELECT COUNT(1) FROM (originSql) temp_data_scope WHERE
	 * temp_data_scope.dept_id IN (1)
	 */
	COUNT("COUNT(1)", "自定义");

	/**
	 * 类型
	 */
	private final String type;

	/**
	 * 描述
	 */
	private final String description;

}
