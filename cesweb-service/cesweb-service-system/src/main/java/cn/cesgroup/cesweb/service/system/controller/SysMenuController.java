package cn.cesgroup.cesweb.service.system.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

import cn.cesgroup.cesweb.api.system.dto.MenuTree;
import cn.cesgroup.cesweb.api.system.entity.SysMenu;
import cn.cesgroup.cesweb.api.system.model.MenuModel;
import cn.cesgroup.cesweb.api.system.model.MenuSortModel;
import cn.cesgroup.cesweb.common.core.util.Response;
import cn.cesgroup.cesweb.common.log.annotation.SysLog;
import cn.cesgroup.cesweb.common.security.util.SecurityUtils;
import cn.cesgroup.cesweb.service.system.service.SysMenuService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;

/**
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 13, 2020 4:13:03 PM
 * @version 1.0.2020
 */
@RestController
@AllArgsConstructor
@RequestMapping("/menu")
@Api(value = "menu", tags = "菜单管理模块")
public class SysMenuController {

	private final SysMenuService sysMenuService;

	/**
	 * 返回当前用户的树形菜单集合
	 * @param type 类型
	 * @param parentId 父节点ID
	 * @return 当前用户的树形菜单
	 */
	@GetMapping
	public Response<?> getUserMenu(String type, String parentId) {
		// 获取符合条件的菜单
		Set<MenuModel> all = new HashSet<>();
		SecurityUtils.getRoles().forEach(roleId -> all.addAll(sysMenuService.findMenuByRoleId(roleId)));
		List<MenuTree> tree = sysMenuService.filterMenu(all, type, parentId);
		return Response.ok(tree);
	}

	/**
	 * 返回树形菜单集合
	 * @param lazy 是否是懒加载
	 * @param parentId 父节点ID
	 * @return 树形菜单
	 */
	@GetMapping(value = "/tree")
	public Response<?> getTree(boolean lazy, String parentId) {
		return Response.ok(sysMenuService.treeMenu(lazy, parentId));
	}

	/**
	 * 返回角色的菜单集合
	 * @param roleId 角色ID
	 * @return 属性集合
	 */
	@GetMapping("/tree/{roleId}")
	public Response<?> getRoleTree(@PathVariable String roleId) {
		return Response.ok(sysMenuService.findMenuByRoleId(roleId).stream().map(MenuModel::getMenuId).collect(Collectors.toList()));
	}

	/**
	 * 通过ID查询菜单的详细信息
	 * @param id 菜单ID
	 * @return 菜单详细信息
	 */
	@GetMapping("/{id}")
	public Response<SysMenu> getById(@PathVariable String id) {
		return Response.ok(sysMenuService.getById(id));
	}

	/**
	 * 新增菜单
	 * @param sysMenu 菜单信息
	 * @return success/false
	 */
	@SysLog("新增菜单")
	@PostMapping
	@PreAuthorize("@pms.hasPermission('sys_menu_add')")
	public Response<SysMenu> save(@Valid @RequestBody SysMenu sysMenu) {
		sysMenuService.save(sysMenu);
		return Response.ok(sysMenu);
	}

	/**
	 * 删除菜单
	 * @param id 菜单ID
	 * @return success/false
	 */
	@SysLog("删除菜单")
	@DeleteMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('sys_menu_del')")
	public Response<?> removeById(@PathVariable String id) {
		return sysMenuService.removeMenuById(id);
	}

	/**
	 * 更新菜单
	 * @param sysMenu
	 */
	@SysLog("更新菜单")
	@PutMapping
	@PreAuthorize("@pms.hasPermission('sys_menu_edit')")
	public Response<Boolean> update(@Valid @RequestBody SysMenu sysMenu) {
		return Response.ok(sysMenuService.updateMenuById(sysMenu));
	}
	
	/**
	 * 菜单排序.
	 * @param MenuSortModel
	 * @author chanlong(陈龙)
	 * @date Jul 28, 2020 4:08:22 PM
	 */
	@SysLog("菜单排序")
	@PutMapping("/sort")
	@PreAuthorize("@pms.hasPermission('sys_menu_edit')")
	public Response<Boolean> sort(@Valid @RequestBody MenuSortModel model) {
		return Response.ok(sysMenuService.updateSortById(model));
	} 

}
