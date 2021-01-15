package cn.cesgroup.cesweb.common.data.datascope;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 数据权限查询参数
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 10, 2020 3:26:14 PM
 * @version 1.0.2020
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DataScope extends HashMap<String, Object> {

	/** serialVersionUID(long):. */
	private static final long serialVersionUID = 1L;

	/**
	 * 限制范围的字段名称
	 */
	private String scopeName = "dept_id";

	/**
	 * 具体的数据范围
	 */
	private List<String> deptIds = new ArrayList<>();

	/**
	 * 是否只查询本部门
	 */
	private Boolean isOnly = false;

	/**
	 * 函数名称，默认 SELECT * ;
	 *
	 * <ul>
	 * <li>COUNT(1)</li>
	 * </ul>
	 */
	private DataScopeFuncEnum func = DataScopeFuncEnum.ALL;

}
