package cn.cesgroup.cesweb.service.system.service;

import java.util.List;
import java.util.Set;

import com.baomidou.mybatisplus.extension.service.IService;

import cn.cesgroup.cesweb.api.system.dto.MenuTree;
import cn.cesgroup.cesweb.api.system.entity.SysMenu;
import cn.cesgroup.cesweb.api.system.model.MenuModel;
import cn.cesgroup.cesweb.api.system.model.MenuSortModel;
import cn.cesgroup.cesweb.common.core.util.Response;

/**
 * 菜单权限表 服务类
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 13, 2020 1:37:05 PM
 * @version 1.0.2020
 */
public interface SysMenuService extends IService<SysMenu> {

	/**
	 * 通过角色编号查询URL 权限
	 * @param roleId 角色ID
	 * @return 菜单列表
	 */
	List<MenuModel> findMenuByRoleId(String roleId);

	/**
	 * 级联删除菜单
	 * @param id 菜单ID
	 * @return 成功、失败
	 */
	Response<Boolean> removeMenuById(String id);

	/**
	 * 更新菜单信息
	 * @param sysMenu 菜单信息
	 * @return 成功、失败
	 */
	Boolean updateMenuById(SysMenu sysMenu);

	/**
	 * 更新菜单排序.
	 * @param MenuSortModel
	 * @author chanlong(陈龙)
	 * @date Jul 28, 2020 4:11:36 PM
	 */
	Boolean updateSortById(final MenuSortModel model);
	
	/**
	 * 构建树
	 * @param lazy 是否是懒加载
	 * @param parentId 父节点ID
	 * @return
	 */
	List<MenuTree> treeMenu(boolean lazy, String parentId);

	/**
	 * 查询菜单
	 * @param voSet
	 * @param parentId
	 * @return
	 */
	List<MenuTree> filterMenu(Set<MenuModel> voSet, String type, String parentId);

}
