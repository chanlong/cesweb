package cn.cesgroup.cesweb.api.system.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import cn.cesgroup.cesweb.api.system.dto.UserInfo;
import cn.cesgroup.cesweb.api.system.entity.SysUser;
import cn.cesgroup.cesweb.common.core.constant.SecurityConstants;
import cn.cesgroup.cesweb.common.core.constant.ServiceNameConstants;
import cn.cesgroup.cesweb.common.core.util.Response;

/**
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 10, 2020 10:00:28 AM
 * @version 1.0.2020
 */
@FeignClient(contextId = "remoteUserService", value = ServiceNameConstants.SYSTEM_SERVICE)
public interface RemoteUserService {

	/**
	 * 通过用户名查询用户、角色信息
	 * @param username 用户名
	 * @param from 调用标志
	 * @return Response
	 */
	@GetMapping("/user/info/{username}")
	Response<UserInfo> info(@PathVariable("username") String username, @RequestHeader(SecurityConstants.FROM) String from);

	/**
	 * 通过社交账号或手机号查询用户、角色信息
	 * @param inStr appid@code
	 * @param from 调用标志
	 * @return Response
	 */
	@GetMapping("/social/info/{inStr}")
	Response<UserInfo> social(@PathVariable("inStr") String inStr, @RequestHeader(SecurityConstants.FROM) String from);

	/**
	 * 查询上级部门的用户信息
	 * @param username 用户名
	 * @return Response
	 */
	@GetMapping("/user/ancestor/{username}")
	Response<List<SysUser>> ancestorUsers(@PathVariable("username") String username);

}
