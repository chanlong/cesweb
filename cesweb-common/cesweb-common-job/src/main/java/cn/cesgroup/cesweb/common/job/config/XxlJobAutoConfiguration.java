package cn.cesgroup.cesweb.common.job.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;

import cn.cesgroup.cesweb.common.job.properties.XxlJobProperties;

/**
 * xxl 初始化
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 10, 2020 4:00:56 PM
 * @version 1.0.2020
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan("cn.cesgroup.cesweb.common.job.properties")
public class XxlJobAutoConfiguration {

	@Bean
	public XxlJobSpringExecutor xxlJobSpringExecutor(XxlJobProperties xxlJobProperties) {
		XxlJobSpringExecutor xxlJobSpringExecutor = new XxlJobSpringExecutor();
		xxlJobSpringExecutor.setAdminAddresses(xxlJobProperties.getAdmin().getAddresses());
		xxlJobSpringExecutor.setAppname(xxlJobProperties.getExecutor().getAppname());
		xxlJobSpringExecutor.setAddress(xxlJobProperties.getExecutor().getAddress());
		xxlJobSpringExecutor.setIp(xxlJobProperties.getExecutor().getIp());
		xxlJobSpringExecutor.setPort(xxlJobProperties.getExecutor().getPort());
		xxlJobSpringExecutor.setAccessToken(xxlJobProperties.getExecutor().getAccessToken());
		xxlJobSpringExecutor.setLogPath(xxlJobProperties.getExecutor().getLogPath());
		xxlJobSpringExecutor.setLogRetentionDays(xxlJobProperties.getExecutor().getLogRetentionDays());
		return xxlJobSpringExecutor;
	}

}
