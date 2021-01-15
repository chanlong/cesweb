package cn.cesgroup.cesweb.common.data.tenant;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.NullValue;

/**
 * 租户维护处理器
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 10, 2020 3:35:18 PM
 * @version 1.0.2020
 */
@Slf4j
public class TenantHandler implements com.baomidou.mybatisplus.extension.plugins.tenant.TenantHandler {

	@Autowired
	private TenantConfigProperties properties;

	/**
	 * 获取租户值
	 * <p>
	 * TODO 校验租户状态
	 * @return 租户值
	 */
	@Override
	public Expression getTenantId(boolean where) {
		String tenantId = TenantContextHolder.getTenantId();
		log.debug("当前租户为 >> {}", tenantId);

		if (tenantId == null) {
			return new NullValue();
		}
		return new LongValue(tenantId);
	}

	/**
	 * 获取租户字段名
	 * @return 租户字段名
	 */
	@Override
	public String getTenantIdColumn() {
		return properties.getColumn();
	}

	/**
	 * 根据表名判断是否进行过滤
	 * @param tableName 表名
	 * @return 是否进行过滤
	 */
	@Override
	public boolean doTableFilter(String tableName) {
		String tenantId = TenantContextHolder.getTenantId();
		// 租户中ID 为空，查询全部，不进行过滤
		if (tenantId == null) {
			return Boolean.TRUE;
		}

		return !properties.getTables().contains(tableName);
	}
}
