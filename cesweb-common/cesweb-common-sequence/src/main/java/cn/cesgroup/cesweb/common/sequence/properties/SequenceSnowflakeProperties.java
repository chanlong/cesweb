package cn.cesgroup.cesweb.common.sequence.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Snowflake 发号器属性
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 10, 2020 4:40:43 PM
 * @version 1.0.2020
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Component
@ConfigurationProperties(prefix = "cesweb.sequence.snowflake")
public class SequenceSnowflakeProperties extends BaseSequenceProperties {

	/**
	 * 数据中心ID，值的范围在[0,31]之间，一般可以设置机房的IDC[必选]
	 */
	private long dataCenterId;

	/**
	 * 工作机器ID，值的范围在[0,31]之间，一般可以设置机器编号[必选]
	 */
	private long workerId;

}
