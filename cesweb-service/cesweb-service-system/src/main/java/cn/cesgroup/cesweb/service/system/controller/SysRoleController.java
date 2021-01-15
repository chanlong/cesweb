package cn.cesgroup.cesweb.service.system.controller;

import java.util.List;

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

import cn.cesgroup.cesweb.api.system.entity.SysRole;
import cn.cesgroup.cesweb.api.system.model.RoleModel;
import cn.cesgroup.cesweb.common.core.util.Response;
import cn.cesgroup.cesweb.common.log.annotation.SysLog;
import cn.cesgroup.cesweb.service.system.service.SysRoleMenuService;
import cn.cesgroup.cesweb.service.system.service.SysRoleService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;

/**
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 13, 2020 4:18:29 PM
 * @version 1.0.2020
 */
@RestController
@AllArgsConstructor
@RequestMapping("/role")
@Api(value = "role", tags = "角色管理模块")
public class SysRoleController {

	private final SysRoleService sysRoleService;

	private final SysRoleMenuService sysRoleMenuService;

	/**
	 * 通过ID查询角色信息
	 * @param id ID
	 * @return 角色信息
	 */
	@GetMapping("/{id}")
	public Response<?> getById(@PathVariable String id) {
		return Response.ok(sysRoleService.getById(id));
	}

	/**
	 * 添加角色
	 * @param sysRole 角色信息
	 * @return success、false
	 */
	@SysLog("添加角色")
	@PostMapping
	@PreAuthorize("@pms.hasPermission('sys_role_add')")
	public Response<?> save(@Valid @RequestBody SysRole sysRole) {
		return Response.ok(sysRoleService.save(sysRole));
	}

	/**
	 * 修改角色
	 * @param sysRole 角色信息
	 * @return success/false
	 */
	@SysLog("修改角色")
	@PutMapping
	@PreAuthorize("@pms.hasPermission('sys_role_edit')")
	public Response<?> update(@Valid @RequestBody SysRole sysRole) {
		return Response.ok(sysRoleService.updateById(sysRole));
	}

	/**
	 * 删除角色
	 * @param id
	 */
	@SysLog("删除角色")
	@DeleteMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('sys_role_del')")
	public Response<?> removeById(@PathVariable String id) {
		return Response.ok(sysRoleService.removeRoleById(id));
	}

	/**
	 * 获取角色列表
	 * @return 角色列表
	 */
	@GetMapping("/list")
	public Response<?> listRoles() {
		return Response.ok(sysRoleService.list(Wrappers.emptyWrapper()));
	}

	/**
	 * 分页查询角色信息
	 * @param page 分页对象
	 * @return 分页对象
	 */
	@GetMapping("/page")
	public Response<?> getRolePage(Page<SysRole> page) {
		return Response.ok(sysRoleService.page(page, Wrappers.emptyWrapper()));
	}

	/**
	 * 更新角色菜单
	 * @param roleModel 角色对象
	 * @return success、false
	 */
	@SysLog("更新角色菜单")
	@PutMapping("/menu")
	@PreAuthorize("@pms.hasPermission('sys_role_perm')")
	public Response<?> saveRoleMenus(@RequestBody RoleModel roleModel) {
		SysRole sysRole = sysRoleService.getById(roleModel.getRoleId());
		return Response.ok(sysRoleMenuService.saveRoleMenus(sysRole.getRoleCode(), roleModel.getRoleId(), roleModel.getMenuIds()));
	}

	/**
	 * 通过角色ID 查询角色列表
	 * @param roleIdList 角色ID
	 * @return
	 */
	@PostMapping("/getRoleList")
	public Response<?> getRoleList(@RequestBody List<String> roleIdList) {
		return Response.ok(sysRoleService.listByIds(roleIdList));
	}

}
