package cn.cesgroup.cesweb.service.tenant.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.CacheEvict;
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

import cn.cesgroup.cesweb.api.tenant.entity.Tenant;
import cn.cesgroup.cesweb.common.core.constant.CacheConstants;
import cn.cesgroup.cesweb.common.core.util.Response;
import cn.cesgroup.cesweb.common.log.annotation.SysLog;
import cn.cesgroup.cesweb.common.security.annotation.Inner;
import cn.cesgroup.cesweb.service.tenant.service.TenantService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;

/**
 * 租户管理
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 13, 2020 4:24:27 PM
 * @version 1.0.2020
 */
@RestController
@AllArgsConstructor
@RequestMapping("/tenant")
@Api(value = "tenant", tags = "租户管理")
public class TenantController {

	private final TenantService tenantService;

	/**
	 * 分页查询
	 * @param page 分页对象
	 * @param Tenant 租户
	 */
	@GetMapping("/page")
	public Response<?> getTenantPage(Page<Tenant> page, Tenant tenant) {
		return Response.ok(tenantService.page(page, Wrappers.query(tenant)));
	}

	/**
	 * 通过id查询租户
	 * @param id id
	 * @return Response
	 */
	@GetMapping("/{id}")
	public Response<?> getById(@PathVariable("id") String id) {
		return Response.ok(tenantService.getById(id));
	}

	/**
	 * 新增租户
	 * @param Tenant 租户
	 * @return Response
	 */
	@SysLog("新增租户")
	@PostMapping
	@PreAuthorize("@pms.hasPermission('admin_tenant_add')")
	@CacheEvict(value = CacheConstants.TENANT_DETAILS, allEntries = true)
	public Response<?> save(@RequestBody Tenant tenant) {
		return Response.ok(tenantService.saveTenant(tenant));
	}

	/**
	 * 修改租户
	 * @param Tenant 租户
	 * @return Response
	 */
	@SysLog("修改租户")
	@PutMapping
	@PreAuthorize("@pms.hasPermission('admin_tenant_edit')")
	@CacheEvict(value = CacheConstants.TENANT_DETAILS, allEntries = true)
	public Response<?> updateById(@RequestBody Tenant tenant) {
		return Response.ok(tenantService.updateById(tenant));
	}

	/**
	 * 通过id删除租户
	 * @param id id
	 * @return Response
	 */
	@SysLog("删除租户")
	@DeleteMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('admin_tenant_del')")
	@CacheEvict(value = CacheConstants.TENANT_DETAILS, allEntries = true)
	public Response<?> removeById(@PathVariable String id) {
		return Response.ok(tenantService.removeById(id));
	}

	/**
	 * 查询全部有效的租户
	 * @return Response
	 */
	@Inner(value = false)
	@GetMapping("/list")
	public Response<?> list() {
		List<Tenant> tenants = tenantService.getNormalTenant().stream()
															  .filter(tenant -> tenant.getValidityBegin().isBefore(LocalDate.now()))
															  .filter(tenant -> tenant.getValidityFinish().isAfter(LocalDate.now())).collect(Collectors.toList());
		return Response.ok(tenants);
	}

}
