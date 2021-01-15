package cn.cesgroup.cesweb.common.security.social;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * qq登录配置信息
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 10, 2020 1:46:03 PM
 * @version 1.0.2020
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "social.qq")
public class QqSocialConfig {

	private String appid;

	private String secret;

}
