package cn.cesgroup.cesweb.monitor.admin.service;

import java.util.Map;

/**
 * redis 监控
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 8, 2020 9:13:07 AM
 * @version 1.0.2020
 */
public interface RedisService {

	/**
	 * 获取内存信息
	 * @return
	 */
	Map<String, Object> getInfo();

}
