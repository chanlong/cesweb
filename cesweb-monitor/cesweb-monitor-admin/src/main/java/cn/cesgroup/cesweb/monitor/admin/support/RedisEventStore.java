package cn.cesgroup.cesweb.monitor.admin.support;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import cn.cesgroup.cesweb.common.core.constant.CacheConstants;
import cn.hutool.core.util.StrUtil;
import de.codecentric.boot.admin.server.domain.events.InstanceEvent;
import de.codecentric.boot.admin.server.eventstore.InMemoryEventStore;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * redis store event default 100
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 8, 2020 9:11:06 AM
 * @version 1.0.2020
 */
@Slf4j
@Configuration
public class RedisEventStore extends InMemoryEventStore {

	@Autowired
	private RedisTemplate<String, ?> redisTemplate;

	@Override
	public Mono<Void> append(List<InstanceEvent> events) {
		events.forEach(event -> {
			String key = event.getInstance().getValue() + StrUtil.UNDERLINE + event.getTimestamp().toString();
			log.info("保存实例事件的KEY：{},EVENT: {}", key, event.getType());
			redisTemplate.setKeySerializer(new StringRedisSerializer());
			redisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(InstanceEvent.class));
			redisTemplate.opsForHash().put(CacheConstants.EVENT_KEY, key, event);
		});
		return super.append(events);
	}

}
