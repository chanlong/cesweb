package cn.cesgroup.cesweb.common.core.constant;

/**
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 7, 2020 2:07:08 PM
 * @version 1.0.2020
 */
public interface ServiceNameConstants {

	/** 分布式事务协调服务 */
	String TX_MANAGER = "cesweb-monitor-trans";
	
	/** 认证中心 */
	String AUTH_SERVICE = "cesweb-service-auth";
	
	/** 系统管理模块 */
	String SYSTEM_SERVICE = "cesweb-service-system";
	
	/** 租户管理模块 */
	String TENANT_SERVICE = "cesweb-service-tenant";
}
