package cn.cesgroup.cesweb.common.job.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 10, 2020 4:02:45 PM
 * @version 1.0.2020
 */
@Data
@Component
@ConfigurationProperties(prefix = "xxl.job")
public class XxlJobProperties {

	private XxlAdminProperties admin = new XxlAdminProperties();

	private XxlExecutorProperties executor = new XxlExecutorProperties();

}