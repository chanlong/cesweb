package cn.cesgroup.cesweb.service.system.controller;

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

import cn.cesgroup.cesweb.api.system.entity.SysPublicParam;
import cn.cesgroup.cesweb.common.core.util.Response;
import cn.cesgroup.cesweb.common.log.annotation.SysLog;
import cn.cesgroup.cesweb.common.security.annotation.Inner;
import cn.cesgroup.cesweb.service.system.service.SysPublicParamService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

/**
 * 系统参数配置
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 13, 2020 4:16:13 PM
 * @version 1.0.2020
 */
@RestController
@AllArgsConstructor
@RequestMapping("/param")
@Api(value = "param", tags = "公共参数配置")
public class SysPublicParamController {

	private final SysPublicParamService sysPublicParamService;

	/**
	 * 通过key查询公共参数值
	 * @param publicKey
	 * @return
	 */
	@Inner(value = false)
	@ApiOperation(value = "查询公共参数值", notes = "根据key查询公共参数值")
	@GetMapping("/publicValue/{publicKey}")
	public Response<?> publicKey(@PathVariable("publicKey") String publicKey) {
		return Response.ok(sysPublicParamService.getSysPublicParamKeyToValue(publicKey));
	}

	/**
	 * 分页查询
	 * @param page 分页对象
	 * @param sysPublicParam 公共参数
	 * @return
	 */
	@ApiOperation(value = "分页查询", notes = "分页查询")
	@GetMapping("/page")
	public Response<?> getSysPublicParamPage(Page<SysPublicParam> page, SysPublicParam sysPublicParam) {
		return Response.ok(sysPublicParamService.page(page, Wrappers.query(sysPublicParam)));
	}

	/**
	 * 通过id查询公共参数
	 * @param publicId id
	 * @return R
	 */
	@ApiOperation(value = "通过id查询公共参数", notes = "通过id查询公共参数")
	@GetMapping("/{publicId}")
	public Response<?> getById(@PathVariable("publicId") String publicId) {
		return Response.ok(sysPublicParamService.getById(publicId));
	}

	/**
	 * 新增公共参数
	 * @param sysPublicParam 公共参数
	 * @return R
	 */
	@ApiOperation(value = "新增公共参数", notes = "新增公共参数")
	@SysLog("新增公共参数")
	@PostMapping
	@PreAuthorize("@pms.hasPermission('admin_syspublicparam_add')")
	public Response<?> save(@RequestBody SysPublicParam sysPublicParam) {
		return Response.ok(sysPublicParamService.save(sysPublicParam));
	}

	/**
	 * 修改公共参数
	 * @param sysPublicParam 公共参数
	 * @return R
	 */
	@ApiOperation(value = "修改公共参数", notes = "修改公共参数")
	@SysLog("修改公共参数")
	@PutMapping
	@PreAuthorize("@pms.hasPermission('admin_syspublicparam_edit')")
	public Response<?> updateById(@RequestBody SysPublicParam sysPublicParam) {
		return sysPublicParamService.updateParam(sysPublicParam);
	}

	/**
	 * 通过id删除公共参数
	 * @param publicId id
	 * @return R
	 */
	@ApiOperation(value = "删除公共参数", notes = "删除公共参数")
	@SysLog("删除公共参数")
	@DeleteMapping("/{publicId}")
	@PreAuthorize("@pms.hasPermission('admin_syspublicparam_del')")
	public Response<?> removeById(@PathVariable String publicId) {
		return sysPublicParamService.removeParam(publicId);
	}

}
