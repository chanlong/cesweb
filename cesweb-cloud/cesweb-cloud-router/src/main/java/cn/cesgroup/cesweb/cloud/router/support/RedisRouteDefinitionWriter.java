package cn.cesgroup.cesweb.cloud.router.support;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import cn.cesgroup.cesweb.cloud.router.model.RouterDefinition;
import cn.cesgroup.cesweb.common.core.constant.CacheConstants;
import cn.hutool.core.collection.CollUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * redis 保存路由信息，优先级比配置文件高
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 7, 2020 2:29:24 PM
 * @version 1.0.2020
 */
@Slf4j
@Component
@AllArgsConstructor
public class RedisRouteDefinitionWriter implements RouteDefinitionRepository {

	private final RedisTemplate<String, ?> redisTemplate;

	@Override
	public Mono<Void> save(Mono<RouteDefinition> route) {
		return route.flatMap(r -> {
			RouterDefinition vo = new RouterDefinition();
			BeanUtils.copyProperties(r, vo);
			log.info("保存路由信息{}", vo);
			redisTemplate.setKeySerializer(new StringRedisSerializer());
			redisTemplate.opsForHash().put(CacheConstants.ROUTE_KEY, r.getId(), vo);
			redisTemplate.convertAndSend(CacheConstants.ROUTE_JVM_RELOAD_TOPIC, "新增路由信息,网关缓存更新");
			return Mono.empty();
		});
	}

	@Override
	public Mono<Void> delete(Mono<String> routeId) {
		routeId.subscribe(id -> {
			log.info("删除路由信息{}", id);
			redisTemplate.setKeySerializer(new StringRedisSerializer());
			redisTemplate.opsForHash().delete(CacheConstants.ROUTE_KEY, id);
		});
		redisTemplate.convertAndSend(CacheConstants.ROUTE_JVM_RELOAD_TOPIC, "删除路由信息,网关缓存更新");
		return Mono.empty();
	}

	/**
	 * 动态路由入口
	 * <p>
	 * 1. 先从内存中获取 2. 为空加载Redis中数据 3. 更新内存
	 * @return
	 */
	@Override
	public Flux<RouteDefinition> getRouteDefinitions() {
		List<RouterDefinition> routeList = RouteCacheHolder.getRouteList();
		if (CollUtil.isNotEmpty(routeList)) {
			log.debug("内存 中路由定义条数： {}， {}", routeList.size(), routeList);
			return Flux.fromIterable(routeList);
		}

		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(RouterDefinition.class));
		
		HashOperations<String, String, RouterDefinition> operations = redisTemplate.opsForHash();
		List<RouterDefinition> values = operations.values(CacheConstants.ROUTE_KEY);
		
		log.debug("redis 中路由定义条数： {}， {}", values.size(), values);

		RouteCacheHolder.setRouteList(values);
		return Flux.fromIterable(values);
	}
}
