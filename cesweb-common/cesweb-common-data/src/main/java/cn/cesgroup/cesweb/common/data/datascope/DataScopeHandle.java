package cn.cesgroup.cesweb.common.data.datascope;

import java.util.List;

/**
 * data scope 判断处理器,抽象服务扩展
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 10, 2020 3:27:45 PM
 * @version 1.0.2020
 */
public interface DataScopeHandle {

	/**
	 * 计算用户数据权限
	 * @param deptList 部门ID，如果为空表示没有任何数据权限。
	 * @return 返回true表示无需进行数据过滤处理，返回false表示需要进行数据过滤
	 */
	Boolean calcScope(List<String> deptList);

}
