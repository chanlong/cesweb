package cn.cesgroup.cesweb.service.system.controller;

import javax.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cn.cesgroup.cesweb.api.system.dto.UserInfo;
import cn.cesgroup.cesweb.api.system.entity.SysSocialDetails;
import cn.cesgroup.cesweb.common.core.util.Response;
import cn.cesgroup.cesweb.common.log.annotation.SysLog;
import cn.cesgroup.cesweb.common.security.annotation.Inner;
import cn.cesgroup.cesweb.service.system.service.SysSocialDetailsService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;

/**
 * 系统社交登录账号表
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 13, 2020 4:26:44 PM
 * @version 1.0.2020
 */
@RestController
@RequestMapping("/social")
@AllArgsConstructor
@Api(value = "social", tags = "三方账号管理模块")
public class SysSocialDetailsController {

	private final SysSocialDetailsService sysSocialDetailsService;

	/**
	 * 社交登录账户简单分页查询
	 * @param page 分页对象
	 * @param sysSocialDetails 社交登录
	 * @return
	 */
	@GetMapping("/page")
	public Response<?> getSocialDetailsPage(Page<SysSocialDetails> page, SysSocialDetails sysSocialDetails) {
		return Response.ok(sysSocialDetailsService.page(page, Wrappers.query(sysSocialDetails)));
	}

	/**
	 * 信息
	 * @param type 类型
	 * @return R
	 */
	@GetMapping("/{type}")
	public Response<?> getByType(@PathVariable("type") String type) {
		return Response.ok(sysSocialDetailsService.list(Wrappers.<SysSocialDetails>lambdaQuery().eq(SysSocialDetails::getType, type)));
	}

	/**
	 * 保存
	 * @param sysSocialDetails
	 * @return R
	 */
	@SysLog("保存三方信息")
	@PostMapping
	@PreAuthorize("@pms.hasPermission('sys_social_details_add')")
	public Response<Boolean> save(@Valid @RequestBody SysSocialDetails sysSocialDetails) {
		return Response.ok(sysSocialDetailsService.save(sysSocialDetails));
	}

	/**
	 * 修改
	 * @param sysSocialDetails
	 * @return R
	 */
	@SysLog("修改三方信息")
	@PutMapping
	@PreAuthorize("@pms.hasPermission('sys_social_details_edit')")
	public Response<Boolean> updateById(@Valid @RequestBody SysSocialDetails sysSocialDetails) {
		sysSocialDetailsService.updateById(sysSocialDetails);
		return Response.ok(Boolean.TRUE);
	}

	/**
	 * 删除
	 * @param id
	 * @return R
	 */
	@SysLog("删除三方信息")
	@DeleteMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('sys_social_details_del')")
	public Response<Boolean> removeById(@PathVariable String id) {
		return Response.ok(sysSocialDetailsService.removeById(id));
	}

	/**
	 * 通过社交账号、手机号查询用户、角色信息
	 * @param inStr appid@code
	 * @return
	 */
	@Inner
	@GetMapping("/info/{inStr}")
	public Response<UserInfo> getUserInfo(@PathVariable String inStr) {
		return Response.ok(sysSocialDetailsService.getUserInfo(inStr));
	}

	/**
	 * 绑定社交账号
	 * @param state 类型
	 * @param code code
	 * @return
	 */
	@PostMapping("/bind")
	public Response<Boolean> bindSocial(String state, String code) {
		return Response.ok(sysSocialDetailsService.bindSocial(state, code));
	}

}
