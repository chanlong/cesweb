package cn.cesgroup.cesweb.common.sequence.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 发号器Redis配置属性
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 10, 2020 4:41:07 PM
 * @version 1.0.2020
 */
@Component
@ConfigurationProperties(prefix = "cesweb.sequence.redis")
public class SequenceRedisProperties extends BaseSequenceProperties {

}