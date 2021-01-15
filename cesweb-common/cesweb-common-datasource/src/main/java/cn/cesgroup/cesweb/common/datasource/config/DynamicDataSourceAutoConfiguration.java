package cn.cesgroup.cesweb.common.datasource.config;

import org.jasypt.encryption.StringEncryptor;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baomidou.dynamic.datasource.processor.DsProcessor;
import com.baomidou.dynamic.datasource.provider.DynamicDataSourceProvider;

import cn.cesgroup.cesweb.common.datasource.config.properties.DruidDataSourceProperties;
import cn.cesgroup.cesweb.common.datasource.support.JdbcDynamicDataSourceProvider;
import cn.cesgroup.cesweb.common.datasource.support.LastParamDsProcessor;
import lombok.AllArgsConstructor;

/**
 * 动态数据源切换配置
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 13, 2020 5:24:50 PM
 * @version 1.0.2020
 */
@Configuration
@AllArgsConstructor
@AutoConfigureAfter(DataSourceAutoConfiguration.class)
@EnableConfigurationProperties(DruidDataSourceProperties.class)
public class DynamicDataSourceAutoConfiguration {

	private final StringEncryptor stringEncryptor;

	private final DruidDataSourceProperties properties;

	@Bean
	public DynamicDataSourceProvider dynamicDataSourceProvider() {
		return new JdbcDynamicDataSourceProvider(stringEncryptor, properties);
	}

	@Bean
	public DsProcessor dsProcessor() {
		return new LastParamDsProcessor();
	}

}
