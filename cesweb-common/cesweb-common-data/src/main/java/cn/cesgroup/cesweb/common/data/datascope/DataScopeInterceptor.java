package cn.cesgroup.cesweb.common.data.datascope;

import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import com.baomidou.mybatisplus.core.toolkit.PluginUtils;
import com.baomidou.mybatisplus.extension.handlers.AbstractSqlParserHandler;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

/**
 * mybatis 数据权限拦截器
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 10, 2020 3:28:02 PM
 * @version 1.0.2020
 */
@AllArgsConstructor
@Intercepts({
		@Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class, Integer.class }) })
public class DataScopeInterceptor extends AbstractSqlParserHandler implements Interceptor {

	private final DataScopeHandle dataScopeHandle;

	@Override
	@SneakyThrows
	public Object intercept(Invocation invocation) {
		StatementHandler statementHandler = PluginUtils.realTarget(invocation.getTarget());
		MetaObject metaObject = SystemMetaObject.forObject(statementHandler);
		this.sqlParser(metaObject);
		
		// 先判断是不是SELECT操作
		MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
		if (!SqlCommandType.SELECT.equals(mappedStatement.getSqlCommandType())) {
			return invocation.proceed();
		}

		BoundSql boundSql = (BoundSql) metaObject.getValue("delegate.boundSql");
		String originalSql = boundSql.getSql();
		Object parameterObject = boundSql.getParameterObject();

		// 查找参数中包含DataScope类型的参数
		DataScope dataScope = findDataScopeObject(parameterObject);
		if (dataScope == null) {
			return invocation.proceed();
		}

		String scopeName = dataScope.getScopeName();
		List<String> deptIds = dataScope.getDeptIds();
		// 优先获取赋值数据
		if (CollUtil.isEmpty(deptIds) && dataScopeHandle.calcScope(deptIds)) {
			return invocation.proceed();
		}

		if (deptIds.isEmpty()) {
			originalSql = String.format("SELECT %s FROM (%s) temp_data_scope WHERE 1 = 2", dataScope.getFunc().getType(), originalSql);
		}
		else {
			String join = CollectionUtil.join(deptIds, ",");
			originalSql = String.format("SELECT %s FROM (%s) temp_data_scope WHERE temp_data_scope.%s IN (%s)", dataScope.getFunc().getType(), originalSql, scopeName, join);
		}

		metaObject.setValue("delegate.boundSql.sql", originalSql);
		return invocation.proceed();
	}

	/**
	 * 生成拦截对象的代理
	 * @param target 目标对象
	 * @return 代理对象
	 */
	@Override
	public Object plugin(Object target) {
		if (target instanceof StatementHandler) {
			return Plugin.wrap(target, this);
		}
		return target;
	}

	/**
	 * mybatis配置的属性
	 * @param properties mybatis配置的属性
	 */
	@Override
	public void setProperties(Properties properties) {

	}

	/**
	 * 查找参数是否包括DataScope对象
	 * @param parameterObj 参数列表
	 * @return DataScope
	 */
	private DataScope findDataScopeObject(Object parameterObj) {
		if (parameterObj instanceof DataScope) {
			return (DataScope) parameterObj;
		}
		else if (parameterObj instanceof Map) {
			for (Object val : ((Map<?, ?>) parameterObj).values()) {
				if (val instanceof DataScope) {
					return (DataScope) val;
				}
			}
		}
		return null;
	}

}
