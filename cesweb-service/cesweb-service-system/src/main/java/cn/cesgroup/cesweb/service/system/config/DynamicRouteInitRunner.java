package cn.cesgroup.cesweb.service.system.config;

import java.net.URI;

import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.scheduling.annotation.Async;

import cn.cesgroup.cesweb.cloud.router.model.RouterDefinition;
import cn.cesgroup.cesweb.cloud.router.support.DynamicRouteInitEvent;
import cn.cesgroup.cesweb.common.core.constant.CacheConstants;
import cn.cesgroup.cesweb.service.system.service.SysRouteConfService;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 容器启动后保存配置文件里面的路由信息到Redis
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 13, 2020 1:33:11 PM
 * @version 1.0.2020
 */
@Slf4j
@Configuration
@AllArgsConstructor
public class DynamicRouteInitRunner {

	private final RedisTemplate<String, ?> redisTemplate;

	private final SysRouteConfService routeConfService;

	@Async
	@Order
	@EventListener({ WebServerInitializedEvent.class, DynamicRouteInitEvent.class })
	public void initRoute() {
		Boolean result = redisTemplate.delete(CacheConstants.ROUTE_KEY);
		log.info("初始化网关路由 {} ", result);

		routeConfService.list().forEach(route -> {
			RouterDefinition vo = new RouterDefinition();
			vo.setRouteName(route.getRouteName());
			vo.setId(route.getRouteId());
			vo.setUri(URI.create(route.getUri()));
			vo.setOrder(route.getOrder());

			JSONArray filterObj = JSONUtil.parseArray(route.getFilters());
			vo.setFilters(filterObj.toList(FilterDefinition.class));
			JSONArray predicateObj = JSONUtil.parseArray(route.getPredicates());
			vo.setPredicates(predicateObj.toList(PredicateDefinition.class));

			log.info("加载路由ID：{},{}", route.getRouteId(), vo);
			redisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(RouterDefinition.class));
			redisTemplate.opsForHash().put(CacheConstants.ROUTE_KEY, route.getRouteId(), vo);
		});
		log.debug("初始化网关路由结束 ");
	}

	/**
	 * redis 监听配置,监听 gateway_redis_route_reload_topic,重新加载Redis
	 * @param redisConnectionFactory redis 配置
	 * @return
	 */
	@Bean
	public RedisMessageListenerContainer redisContainer(RedisConnectionFactory redisConnectionFactory) {
		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		container.setConnectionFactory(redisConnectionFactory);
		container.addMessageListener((message, bytes) -> {
			log.warn("接收到重新Redis 重新加载路由事件");
			initRoute();
		}, new ChannelTopic(CacheConstants.ROUTE_REDIS_RELOAD_TOPIC));
		return container;
	}

}
