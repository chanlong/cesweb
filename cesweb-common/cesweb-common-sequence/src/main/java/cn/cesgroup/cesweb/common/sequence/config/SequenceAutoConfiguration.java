package cn.cesgroup.cesweb.common.sequence.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import cn.cesgroup.cesweb.common.sequence.builder.SnowflakeSequenceBuilder;
import cn.cesgroup.cesweb.common.sequence.generate.Sequence;
import cn.cesgroup.cesweb.common.sequence.properties.SequenceSnowflakeProperties;

/**
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 10, 2020 4:36:08 PM
 * @version 1.0.2020
 */
@Configuration
@ComponentScan("cn.cesgroup.cesweb.common.sequence")
@ConditionalOnMissingBean(Sequence.class)
public class SequenceAutoConfiguration {

	/**
	 * snowflak 算法作为发号器实现
	 * @param properties
	 */
	@Bean
	@ConditionalOnBean(SequenceSnowflakeProperties.class)
	public Sequence snowflakeSequence(SequenceSnowflakeProperties properties) {
		return SnowflakeSequenceBuilder.create().dataCenterId(properties.getDataCenterId()).workerId(properties.getWorkerId()).build();
	}

}