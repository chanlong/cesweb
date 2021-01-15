package cn.cesgroup.cesweb.common.data.mybatis;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantSqlParser;

import cn.cesgroup.cesweb.common.data.datascope.DataScopeHandle;
import cn.cesgroup.cesweb.common.data.datascope.DataScopeInterceptor;
import cn.cesgroup.cesweb.common.data.datascope.DataScopeSqlInjector;
import cn.cesgroup.cesweb.common.data.datascope.DefaultDatascopeHandle;
import cn.cesgroup.cesweb.common.data.tenant.TenantHandler;

/**
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 10, 2020 3:33:50 PM
 * @version 1.0.2020
 */
@Configuration
@ConditionalOnBean(DataSource.class)
@AutoConfigureAfter(DataSourceAutoConfiguration.class)
public class MybatisPlusConfiguration {
	
	/**
	 * 创建租户维护处理器对象
	 * @return 处理后的租户维护处理器
	 */
	@Bean
	@ConditionalOnMissingBean
	public TenantHandler tenantHandler() {
		return new TenantHandler();
	}

	/**
	 * 分页插件
	 * @param tenantHandler 租户处理器
	 * @return PaginationInterceptor
	 */
	@Bean
	@ConditionalOnMissingBean
	@ConditionalOnProperty(name = "mybatisPlus.tenantEnable", havingValue = "true", matchIfMissing = true)
	public PaginationInterceptor paginationInterceptor(TenantHandler tenantHandler) {
		PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
		List<ISqlParser> sqlParserList = new ArrayList<>();
		TenantSqlParser tenantSqlParser = new TenantSqlParser();
		tenantSqlParser.setTenantHandler(tenantHandler);
		sqlParserList.add(tenantSqlParser);
		paginationInterceptor.setSqlParserList(sqlParserList);
		return paginationInterceptor;
	}

	/**
	 * pigx 默认数据权限处理
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean
	public DataScopeHandle dataScopeHandle() {
		return new DefaultDatascopeHandle();
	}

	/**
	 * 数据权限插件
	 * @return DataScopeInterceptor
	 */
	@Bean
	@ConditionalOnMissingBean
	@ConditionalOnBean(DataScopeHandle.class)
	public DataScopeInterceptor dataScopeInterceptor() {
		return new DataScopeInterceptor(dataScopeHandle());
	}

	@Bean
	@ConditionalOnBean(DataScopeHandle.class)
	public DataScopeSqlInjector dataScopeSqlInjector() {
		return new DataScopeSqlInjector();
	}

}
