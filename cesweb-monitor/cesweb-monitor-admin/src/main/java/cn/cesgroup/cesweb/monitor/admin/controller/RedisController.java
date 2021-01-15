package cn.cesgroup.cesweb.monitor.admin.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.cesgroup.cesweb.common.core.util.Response;
import cn.cesgroup.cesweb.monitor.admin.service.RedisService;

/**
 * redis 数据
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 8, 2020 9:13:42 AM
 * @version 1.0.2020
 */
@RestController
@RequestMapping("/redis")
public class RedisController {

	@Autowired
	private RedisService redisService;

	/**
	 * 查询redis信息
	 * @return
	 */
	@GetMapping("/info")
	public Response<Map<String, Object>> memory() {
		return Response.ok(redisService.getInfo());
	}

}
