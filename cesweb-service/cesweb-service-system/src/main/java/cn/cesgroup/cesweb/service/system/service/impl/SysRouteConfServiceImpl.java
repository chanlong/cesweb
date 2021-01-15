package cn.cesgroup.cesweb.service.system.service.impl;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cn.cesgroup.cesweb.api.system.entity.SysRouteConf;
import cn.cesgroup.cesweb.cloud.router.model.RouterDefinition;
import cn.cesgroup.cesweb.cloud.router.support.DynamicRouteInitEvent;
import cn.cesgroup.cesweb.common.core.constant.CacheConstants;
import cn.cesgroup.cesweb.common.core.constant.CommonConstants;
import cn.cesgroup.cesweb.service.system.mapper.SysRouteConfMapper;
import cn.cesgroup.cesweb.service.system.service.SysRouteConfService;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * 动态路由处理 服务实现类
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 13, 2020 3:26:59 PM
 * @version 1.0.2020
 */
@Slf4j
@AllArgsConstructor
@Service("sysRouteConfService")
public class SysRouteConfServiceImpl extends ServiceImpl<SysRouteConfMapper, SysRouteConf> implements SysRouteConfService {

	private final RedisTemplate<String, ?> redisTemplate;

	private final ApplicationEventPublisher applicationEventPublisher;

	/**
	 * 更新路由信息
	 * @param routes 路由信息
	 */
	@Override
	@SuppressWarnings("unchecked")
	@Transactional(rollbackFor = Exception.class)
	public Mono<Void> updateRoutes(JSONArray routes) {
		// 清空Redis 缓存
		Boolean result = redisTemplate.delete(CacheConstants.ROUTE_KEY);
		log.info("清空网关路由 {} ", result);

		// 遍历修改的routes，保存到Redis
		List<RouterDefinition> routeDefinitionVoList = new ArrayList<>();

		try {
			routes.forEach(value -> {
				log.info("更新路由 ->{}", value);
				RouterDefinition router = new RouterDefinition();
				Map<String, Object> map = (Map<String, Object>) value;

				Object id = map.get("routeId");
				if (id != null) {
					router.setId(String.valueOf(id));
				}

				Object routeName = map.get("routeName");
				if (routeName != null) {
					router.setRouteName(String.valueOf(routeName));
				}

				Object predicates = map.get("predicates");
				if (predicates != null) {
					JSONArray predicatesArray = (JSONArray) predicates;
					List<PredicateDefinition> predicateDefinitionList = predicatesArray.toList(PredicateDefinition.class);
					router.setPredicates(predicateDefinitionList);
				}

				Object filters = map.get("filters");
				if (filters != null) {
					JSONArray filtersArray = (JSONArray) filters;
					List<FilterDefinition> filterDefinitionList = filtersArray.toList(FilterDefinition.class);
					router.setFilters(filterDefinitionList);
				}

				Object uri = map.get("uri");
				if (uri != null) {
					router.setUri(URI.create(String.valueOf(uri)));
				}

				Object order = map.get("order");
				if (order != null) {
					router.setOrder(Integer.parseInt(String.valueOf(order)));
				}
				redisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(RouterDefinition.class));
				redisTemplate.opsForHash().put(CacheConstants.ROUTE_KEY, router.getId(), router);
				routeDefinitionVoList.add(router);
			});

			// 逻辑删除全部
			SysRouteConf condition = new SysRouteConf();
			condition.setDelFlag(CommonConstants.STATUS_NORMAL);
			this.remove(new UpdateWrapper<>(condition));

			// 插入生效路由
			List<SysRouteConf> routeConfList = routeDefinitionVoList.stream().map(vo -> {
				SysRouteConf routeConf = new SysRouteConf();
				routeConf.setRouteId(vo.getId());
				routeConf.setRouteName(vo.getRouteName());
				routeConf.setFilters(JSONUtil.toJsonStr(vo.getFilters()));
				routeConf.setPredicates(JSONUtil.toJsonStr(vo.getPredicates()));
				routeConf.setOrder(vo.getOrder());
				routeConf.setUri(vo.getUri().toString());
				return routeConf;
			}).collect(Collectors.toList());
			this.saveBatch(routeConfList);
			log.debug("更新网关路由结束 ");

			this.applicationEventPublisher.publishEvent(new RefreshRoutesEvent(this));
			redisTemplate.convertAndSend(CacheConstants.ROUTE_JVM_RELOAD_TOPIC, "UPMS路由信息,网关缓存更新");
		}
		catch (Exception e) {
			log.error("路由配置解析失败", e);
			// 回滚路由，重新加载即可
			this.applicationEventPublisher.publishEvent(new DynamicRouteInitEvent(this));
			// 抛出异常
			throw new RuntimeException(e);
		}
		return Mono.empty();
	}

}
