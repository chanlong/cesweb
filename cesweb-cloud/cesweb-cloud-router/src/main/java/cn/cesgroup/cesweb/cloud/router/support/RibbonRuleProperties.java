package cn.cesgroup.cesweb.cloud.router.support;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import java.util.ArrayList;
import java.util.List;

/**
 * Ribbon 配置
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 7, 2020 2:42:12 PM
 * @version 1.0.2020
 */
@Getter
@Setter
@RefreshScope
@ConfigurationProperties("ribbon.rule")
public class RibbonRuleProperties {

	/**
	 * 是否开启，默认：true
	 */
	private boolean enabled = Boolean.TRUE;

	/**
	 * 是否开启灰度路由，默认:false
	 */
	private boolean grayEnabled;

	/**
	 * 优先的ip列表，支持通配符，例如：10.20.0.8*、10.20.0.*
	 */
	private List<String> priorIpPattern = new ArrayList<>();

}
