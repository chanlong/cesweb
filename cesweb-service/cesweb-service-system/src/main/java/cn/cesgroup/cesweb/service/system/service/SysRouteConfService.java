package cn.cesgroup.cesweb.service.system.service;

import com.baomidou.mybatisplus.extension.service.IService;

import cn.cesgroup.cesweb.api.system.entity.SysRouteConf;
import cn.hutool.json.JSONArray;
import reactor.core.publisher.Mono;

/**
 * 路由
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 13, 2020 1:35:02 PM
 * @version 1.0.2020
 */
public interface SysRouteConfService extends IService<SysRouteConf> {

	/**
	 * 更新路由信息
	 * @param routes 路由信息
	 * @return
	 */
	Mono<Void> updateRoutes(JSONArray routes);

}
