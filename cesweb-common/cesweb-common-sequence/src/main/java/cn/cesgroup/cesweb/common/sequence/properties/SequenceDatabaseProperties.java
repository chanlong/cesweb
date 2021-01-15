package cn.cesgroup.cesweb.common.sequence.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 发号器DB配置属性
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 10, 2020 4:37:46 PM
 * @version 1.0.2020
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Component
@ConfigurationProperties(prefix = "cesweb.sequence.db")
public class SequenceDatabaseProperties extends BaseSequenceProperties {

	/**
	 * 表名称
	 */
	private String tableName = "tb_common_sequence";

	/**
	 * 重试次数
	 */
	private int retryTimes = 1;

}