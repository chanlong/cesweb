/**
 * <p>Copyright:Copyright(c) 2020</p>
 * <p>Company:Professional</p>
 * <p>Package:com.alibaba.csp.sentinel.dashboard.rule.nacos.config</p>
 * <p>File:NacosConfig.java</p>
 * <p>类更新历史信息</p>
 * @todo chanlong(陈龙) 创建于 Jul 9, 2020 10:50:17 AM
 */
package com.alibaba.csp.sentinel.dashboard.rule.nacos.config;

import java.util.List;
import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.FlowRuleEntity;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigFactory;
import com.alibaba.nacos.api.config.ConfigService;

/**
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 9, 2020 10:50:17 AM
 * @version 1.0.2020
 */
@Configuration
public class NacosConfig {

	@Bean
	public Converter<List<FlowRuleEntity>, String> flowRuleEntityEncoder() {
		return JSON::toJSONString;
	}

	@Bean
	public Converter<String, List<FlowRuleEntity>> flowRuleEntityDecoder() {
		return s -> JSON.parseArray(s, FlowRuleEntity.class);
	}

	@Bean
	public ConfigService nacosConfigService() throws Exception {
		Properties properties = new Properties();
		properties.put(PropertyKeyConst.SERVER_ADDR, "localhost");
		return ConfigFactory.createConfigService(properties);
	}
}
