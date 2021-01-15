package cn.cesgroup.cesweb.cloud.router.support;

import java.util.ArrayList;
import java.util.List;

import cn.cesgroup.cesweb.cloud.router.model.RouterDefinition;
import cn.hutool.cache.Cache;
import cn.hutool.cache.CacheUtil;
import lombok.experimental.UtilityClass;

/**
 * 路由缓存工具类
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 7, 2020 2:29:02 PM
 * @version 1.0.2020
 */
@UtilityClass
public class RouteCacheHolder {

	private Cache<String, RouterDefinition> cache = CacheUtil.newLFUCache(50);

	/**
	 * 获取缓存的全部对象
	 * @return routeList
	 */
	public List<RouterDefinition> getRouteList() {
		List<RouterDefinition> routeList = new ArrayList<>();
		cache.forEach(route -> routeList.add(route));
		return routeList;
	}

	/**
	 * 更新缓存
	 * @param routeList 缓存列表
	 */
	public void setRouteList(List<RouterDefinition> routeList) {
		routeList.forEach(route -> cache.put(route.getId(), route));
	}

	/**
	 * 清空缓存
	 */
	public void removeRouteList() {
		cache.clear();
	}

}
