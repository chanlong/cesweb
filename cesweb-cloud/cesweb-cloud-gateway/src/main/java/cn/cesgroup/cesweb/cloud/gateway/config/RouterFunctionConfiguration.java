package cn.cesgroup.cesweb.cloud.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

import cn.cesgroup.cesweb.cloud.gateway.handler.ImageCodeCheckHandler;
import cn.cesgroup.cesweb.cloud.gateway.handler.ImageCodeCreateHandler;
import cn.cesgroup.cesweb.cloud.gateway.handler.SwaggerResourceHandler;
import cn.cesgroup.cesweb.cloud.gateway.handler.SwaggerSecurityHandler;
import cn.cesgroup.cesweb.cloud.gateway.handler.SwaggerUiHandler;
import lombok.AllArgsConstructor;

/**
 * 路由配置信息
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 7, 2020 4:53:30 PM
 * @version 1.0.2020
 */
@Configuration
@AllArgsConstructor
public class RouterFunctionConfiguration {

	/** Swagger 处理器 */
	private final SwaggerUiHandler swaggerUiHandler;
	
	private final SwaggerSecurityHandler swaggerSecurityHandler;
	
	private final SwaggerResourceHandler swaggerResourceHandler;
	
	/** 图片验证码 处理器 */
	private final ImageCodeCheckHandler imageCodeCheckHandler;

	private final ImageCodeCreateHandler imageCodeCreateHandler;

	@Bean
	public RouterFunction<?> routerFunction() {
		return RouterFunctions
				.route(RequestPredicates.path("/code").and(RequestPredicates.accept(MediaType.TEXT_PLAIN)), imageCodeCreateHandler)
				.andRoute(RequestPredicates.POST("/code/check").and(RequestPredicates.accept(MediaType.ALL)), imageCodeCheckHandler)
				.andRoute(RequestPredicates.GET("/swagger-resources").and(RequestPredicates.accept(MediaType.ALL)), swaggerResourceHandler)
				.andRoute(RequestPredicates.GET("/swagger-resources/configuration/ui").and(RequestPredicates.accept(MediaType.ALL)), swaggerUiHandler)
				.andRoute(RequestPredicates.GET("/swagger-resources/configuration/security").and(RequestPredicates.accept(MediaType.ALL)), swaggerSecurityHandler);

	}

}
