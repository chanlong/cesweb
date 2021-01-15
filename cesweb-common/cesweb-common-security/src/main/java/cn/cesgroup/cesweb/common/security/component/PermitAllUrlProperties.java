package cn.cesgroup.cesweb.common.security.component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import cn.cesgroup.cesweb.common.security.annotation.Inner;
import cn.hutool.core.util.ReUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * 资源服务器对外直接暴露URL,如果设置contex-path 要特殊处理
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 10, 2020 11:11:27 AM
 * @version 1.0.2020
 */
@Configuration
@RequiredArgsConstructor
@ConditionalOnExpression("!'${security.oauth2.client.ignore-urls}'.isEmpty()")
@ConfigurationProperties(prefix = "security.oauth2.client")
public class PermitAllUrlProperties implements InitializingBean {

	private static final Pattern PATTERN = Pattern.compile("\\{(.*?)\\}");

	private final WebApplicationContext applicationContext;

	@Getter
	@Setter
	private List<String> ignoreUrls = new ArrayList<>();

	@Override
	public void afterPropertiesSet() {
		RequestMappingHandlerMapping mapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
		Map<RequestMappingInfo, HandlerMethod> map = mapping.getHandlerMethods();

		map.keySet().forEach(info -> {
			HandlerMethod handlerMethod = map.get(info);

			// 获取方法上边的注解 替代path variable 为 *
			Inner method = AnnotationUtils.findAnnotation(handlerMethod.getMethod(), Inner.class);
			Optional.ofNullable(method).ifPresent(inner -> info.getPatternsCondition().getPatterns().forEach(url -> ignoreUrls.add(ReUtil.replaceAll(url, PATTERN, "*"))));

			// 获取类上边的注解, 替代path variable 为 *
			Inner controller = AnnotationUtils.findAnnotation(handlerMethod.getBeanType(), Inner.class);
			Optional.ofNullable(controller).ifPresent(inner -> info.getPatternsCondition().getPatterns().forEach(url -> ignoreUrls.add(ReUtil.replaceAll(url, PATTERN, "*"))));
		});
	}
}
