package cn.cesgroup.cesweb.common.oss.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import cn.cesgroup.cesweb.common.oss.config.properties.OssProperties;
import cn.cesgroup.cesweb.common.oss.http.OssEndpoint;
import cn.cesgroup.cesweb.common.oss.service.OssTemplate;
import lombok.AllArgsConstructor;

/**
 * aws 自动配置类
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 10, 2020 4:25:41 PM
 * @version 1.0.2020
 */
@AllArgsConstructor
@EnableConfigurationProperties({ OssProperties.class })
public class OssAutoConfiguration {

	private final OssProperties properties;

	@Bean
	@ConditionalOnMissingBean(OssTemplate.class)
	@ConditionalOnProperty(name = "oss.enable", havingValue = "true", matchIfMissing = true)
	public OssTemplate ossTemplate() {
		return new OssTemplate(properties);
	}

	@Bean
	@ConditionalOnProperty(name = "oss.info", havingValue = "true")
	public OssEndpoint ossEndpoint(OssTemplate template) {
		return new OssEndpoint(template);
	}

}
