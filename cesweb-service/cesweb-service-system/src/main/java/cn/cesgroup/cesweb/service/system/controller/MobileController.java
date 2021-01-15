package cn.cesgroup.cesweb.service.system.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.cesgroup.cesweb.common.core.util.Response;
import cn.cesgroup.cesweb.common.security.annotation.Inner;
import cn.cesgroup.cesweb.service.system.service.MobileService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;

/**
 * 手机验证码
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 13, 2020 3:58:36 PM
 * @version 1.0.2020
 */
@RestController
@AllArgsConstructor
@RequestMapping("/mobile")
@Api(value = "mobile", tags = "手机管理模块")
public class MobileController {

	private final MobileService mobileService;

	@Inner(value = false)
	@GetMapping("/{mobile}")
	public Response<Boolean> sendSmsCode(@PathVariable String mobile) {
		return mobileService.sendSmsCode(mobile);
	}

}
