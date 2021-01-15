package cn.cesgroup.cesweb.common.data.config;

import cn.cesgroup.cesweb.common.data.resolver.SqlFilterArgumentResolver;
import cn.hutool.core.date.DatePattern;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * SpringMvc 规则增强
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 10, 2020 3:39:37 PM
 * @version 1.0.2020
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	/**
	 * 增加请求参数解析器，对请求中的参数注入SQL 检查
	 * @param argumentResolvers
	 */
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(new SqlFilterArgumentResolver());
	}

	/**
	 * 增加GET请求参数中时间类型转换 {@link cn.cesgroup.cesweb.common.core.jackson.JavaTimeModule}
	 * <ul>
	 * <li>HH:mm:ss -> LocalTime</li>
	 * <li>yyyy-MM-dd -> LocalDate</li>
	 * <li>yyyy-MM-dd HH:mm:ss -> LocalDateTime</li>
	 * </ul>
	 * @param registry
	 */
	@Override
	public void addFormatters(FormatterRegistry registry) {
		DateTimeFormatterRegistrar registrar = new DateTimeFormatterRegistrar();
		registrar.setTimeFormatter(DateTimeFormatter.ofPattern(DatePattern.NORM_TIME_PATTERN));
		registrar.setDateFormatter(DateTimeFormatter.ofPattern(DatePattern.NORM_DATE_PATTERN));
		registrar.setDateTimeFormatter(DateTimeFormatter.ofPattern(DatePattern.NORM_DATETIME_PATTERN));
		registrar.registerFormatters(registry);
	}

}
