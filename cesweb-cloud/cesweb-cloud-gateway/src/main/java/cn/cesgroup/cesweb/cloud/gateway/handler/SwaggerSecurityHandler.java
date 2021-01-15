package cn.cesgroup.cesweb.cloud.gateway.handler;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;

/**
 * <p>描述: SwaggerSecurityHandler</p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 7, 2020 4:33:33 PM
 * @version 1.0.2020
 */
@Component
public class SwaggerSecurityHandler implements HandlerFunction<ServerResponse> {

	@Autowired(required = false)
	private SecurityConfiguration securityConfiguration;

	/**
	 * Handle the given request.
	 * @param request the request to handler
	 * @return the response
	 */
	@Override
	public Mono<ServerResponse> handle(ServerRequest request) {
		return ServerResponse.status(HttpStatus.OK)
							 .contentType(MediaType.APPLICATION_JSON)
							 .body(BodyInserters.fromValue(Optional.ofNullable(securityConfiguration)
									 							   .orElse(SecurityConfigurationBuilder.builder().build())));
	}

}
