package cn.cesgroup.cesweb.service.system.controller;

import java.util.Map;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.cesgroup.cesweb.api.system.feign.RemoteTokenService;
import cn.cesgroup.cesweb.common.core.constant.SecurityConstants;
import cn.cesgroup.cesweb.common.core.util.Response;
import cn.cesgroup.cesweb.common.log.annotation.SysLog;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;

/**
 * getTokenPage 管理
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 13, 2020 4:23:33 PM
 * @version 1.0.2020
 */
@RestController
@AllArgsConstructor
@RequestMapping("/token")
@Api(value = "token", tags = "令牌管理模块")
public class SysTokenController {

	private final RemoteTokenService remoteTokenService;

	/**
	 * 分页token 信息
	 * @param params 参数集
	 * @return token集合
	 */
	@GetMapping("/page")
	public Response<?> getTokenPage(@RequestParam Map<String, Object> params) {
		return remoteTokenService.getTokenPage(params, SecurityConstants.FROM_IN);
	}

	/**
	 * 删除
	 * @param token getTokenPage
	 * @return success/false
	 */
	@SysLog("删除用户token")
	@DeleteMapping("/{token}")
	@PreAuthorize("@pms.hasPermission('sys_token_del')")
	public Response<?> removeById(@PathVariable String token) {
		return remoteTokenService.removeTokenById(token, SecurityConstants.FROM_IN);
	}

}
