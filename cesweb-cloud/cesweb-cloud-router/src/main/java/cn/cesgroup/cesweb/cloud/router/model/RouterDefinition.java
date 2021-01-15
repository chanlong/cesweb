package cn.cesgroup.cesweb.cloud.router.model;

import java.io.Serializable;

import org.springframework.cloud.gateway.route.RouteDefinition;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 扩展 {@link org.springframework.cloud.gateway.route.RouteDefinition}
 * <p>描述: 增加路由名称属性</p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 7, 2020 2:24:26 PM
 * @version 1.0.2020
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class RouterDefinition extends RouteDefinition implements Serializable {

	/** serialVersionUID(long):. */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 路由名称
	 */
	private String routeName;

}
