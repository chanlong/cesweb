package cn.cesgroup.cesweb.cloud.gateway.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

/**
 * <p>描述: SwaggerResourceHandler</p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 7, 2020 4:33:14 PM
 * @version 1.0.2020
 */
@Component
@AllArgsConstructor
public class SwaggerResourceHandler implements HandlerFunction<ServerResponse> {

	private final SwaggerResourcesProvider swaggerResources;

	/**
	 * Handle the given request.
	 * @param request the request to handler
	 * @return the response
	 */
	@Override
	public Mono<ServerResponse> handle(ServerRequest request) {
		return ServerResponse.status(HttpStatus.OK)
							 .contentType(MediaType.APPLICATION_JSON)
							 .body(BodyInserters.fromValue(swaggerResources.get()));
	}

}
