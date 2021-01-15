package cn.cesgroup.cesweb.service.system.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.cesgroup.cesweb.common.core.util.Response;
import cn.cesgroup.cesweb.common.log.annotation.SysLog;
import cn.cesgroup.cesweb.service.system.service.SysRouteConfService;
import cn.hutool.json.JSONArray;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;

/**
 * 路由
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 13, 2020 4:28:25 PM
 * @version 1.0.2020
 */
@RestController
@AllArgsConstructor
@RequestMapping("/route")
@Api(value = "route", tags = "动态路由管理模块")
public class SysRouteConfController {

	private final SysRouteConfService sysRouteConfService;

	/**
	 * 获取当前定义的路由信息
	 * @return
	 */
	@GetMapping
	public Response<?> listRoutes() {
		return Response.ok(sysRouteConfService.list());
	}

	/**
	 * 修改路由
	 * @param routes 路由定义
	 * @return
	 */
	@SysLog("修改路由")
	@PutMapping
	public Response<?> updateRoutes(@RequestBody JSONArray routes) {
		return Response.ok(sysRouteConfService.updateRoutes(routes));
	}

}
