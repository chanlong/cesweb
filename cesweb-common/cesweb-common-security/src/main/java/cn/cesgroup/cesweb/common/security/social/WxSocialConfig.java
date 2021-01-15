package cn.cesgroup.cesweb.common.security.social;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 微信登录配置
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 10, 2020 1:45:44 PM
 * @version 1.0.2020
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "social.wx")
public class WxSocialConfig {

	private String appid;

	private String secret;

}
