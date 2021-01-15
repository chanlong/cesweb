package cn.cesgroup.cesweb.cloud.gateway.config;

import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import com.anji.captcha.service.CaptchaCacheService;

import lombok.AllArgsConstructor;

/**
 * 验证码 缓存提供支持集群
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 7, 2020 4:52:04 PM
 * @version 1.0.2020
 */
@Primary
@Component
@AllArgsConstructor
public class CaptchaCacheServiceProvider implements CaptchaCacheService {

	private final StringRedisTemplate stringRedisTemplate;
	
	@Override
	public void set(String key, String value, long expiresInSeconds) {
		stringRedisTemplate.opsForValue().set(key, value, expiresInSeconds, TimeUnit.SECONDS);
	}

	@Override
	public boolean exists(String key) {
		return stringRedisTemplate.hasKey(key);
	}

	@Override
	public void delete(String key) {
		stringRedisTemplate.delete(key);
	}

	@Override
	public String get(String key) {
		return stringRedisTemplate.opsForValue().get(key);
	}

	@Override
	public String type() {
		return "redis";
	}

}
