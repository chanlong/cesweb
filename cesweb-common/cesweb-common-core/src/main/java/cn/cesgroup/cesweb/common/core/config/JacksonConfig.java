package cn.cesgroup.cesweb.common.core.config;

import java.util.Locale;
import java.util.TimeZone;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

import cn.cesgroup.cesweb.common.core.jackson.JavaTimeModule;
import cn.hutool.core.date.DatePattern;

/**
 * JacksonConfig 配置时间转换规则
 * <p>描述: {@link cn.cesgroup.cesweb.common.core.jackson.JavaTimeModule}、默认时区等</p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 7, 2020 1:51:04 PM
 * @version 1.0.2020
 */
@Configuration
@ConditionalOnClass(ObjectMapper.class)
@AutoConfigureBefore(JacksonAutoConfiguration.class)
public class JacksonConfig {

	private static final String ASIA_SHANGHAI = "Asia/Shanghai";

	@Bean
	@ConditionalOnMissingBean
	public Jackson2ObjectMapperBuilderCustomizer customizer() {
		return builder -> {
			builder.locale(Locale.CHINA);
			builder.timeZone(TimeZone.getTimeZone(ASIA_SHANGHAI));
			builder.simpleDateFormat(DatePattern.NORM_DATETIME_PATTERN);
			builder.modules(new JavaTimeModule());
		};
	}

}
