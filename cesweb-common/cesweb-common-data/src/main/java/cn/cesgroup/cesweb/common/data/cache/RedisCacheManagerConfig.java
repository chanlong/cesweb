package cn.cesgroup.cesweb.common.data.cache;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizers;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * CacheManagerCustomizers配置
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 10, 2020 3:25:38 PM
 * @version 1.0.2020
 */
@Configuration
@ConditionalOnMissingBean(CacheManagerCustomizers.class)
public class RedisCacheManagerConfig {

	@Bean
	public CacheManagerCustomizers cacheManagerCustomizers(ObjectProvider<List<CacheManagerCustomizer<?>>> customizers) {
		return new CacheManagerCustomizers(customizers.getIfAvailable());
	}

}
