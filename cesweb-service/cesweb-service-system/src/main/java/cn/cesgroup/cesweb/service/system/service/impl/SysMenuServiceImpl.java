package cn.cesgroup.cesweb.service.system.service.impl;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cn.cesgroup.cesweb.api.system.dto.MenuTree;
import cn.cesgroup.cesweb.api.system.entity.SysMenu;
import cn.cesgroup.cesweb.api.system.entity.SysRoleMenu;
import cn.cesgroup.cesweb.api.system.model.MenuModel;
import cn.cesgroup.cesweb.api.system.model.MenuSortModel;
import cn.cesgroup.cesweb.api.system.util.TreeUtil;
import cn.cesgroup.cesweb.common.core.constant.CacheConstants;
import cn.cesgroup.cesweb.common.core.constant.CommonConstants;
import cn.cesgroup.cesweb.common.core.constant.enums.MenuTypeEnum;
import cn.cesgroup.cesweb.common.core.util.Response;
import cn.cesgroup.cesweb.service.system.mapper.SysMenuMapper;
import cn.cesgroup.cesweb.service.system.mapper.SysRoleMenuMapper;
import cn.cesgroup.cesweb.service.system.service.SysMenuService;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONUtil;
import lombok.AllArgsConstructor;

/**
 * 菜单权限表 服务实现类
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 13, 2020 3:33:41 PM
 * @version 1.0.2020
 */
@Service
@AllArgsConstructor
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

	private final SysRoleMenuMapper sysRoleMenuMapper;

	@Override
	@Cacheable(value = CacheConstants.MENU_DETAILS, key = "#roleId  + '_menu'", unless = "#result.isEmpty()")
	public List<MenuModel> findMenuByRoleId(String roleId) {
		return baseMapper.listMenusByRoleId(roleId);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	@CacheEvict(value = CacheConstants.MENU_DETAILS, allEntries = true)
	public Response<Boolean> removeMenuById(String id) {
		// 查询父节点为当前节点的节点
		List<SysMenu> menuList = this.list(Wrappers.<SysMenu>query().lambda().eq(SysMenu::getParentId, id));
		if (CollUtil.isNotEmpty(menuList)) {
			return Response.failed("菜单含有下级不能删除");
		}

		sysRoleMenuMapper.delete(Wrappers.<SysRoleMenu>query().lambda().eq(SysRoleMenu::getMenuId, id));
		// 删除当前菜单及其子菜单
		return Response.ok(this.removeById(id));
	}

	@Override
	@CacheEvict(value = CacheConstants.MENU_DETAILS, allEntries = true)
	public Boolean updateMenuById(SysMenu sysMenu) {
		return this.updateById(sysMenu);
	}

	@Override
	@CacheEvict(value = CacheConstants.MENU_DETAILS, allEntries = true)
	public Boolean updateSortById(final MenuSortModel model) {
		// 查询指定父节点的节点
		List<SysMenu> menuList = this.list(Wrappers.<SysMenu>query().lambda().eq(SysMenu::getParentId, model.getPid()));
		menuList.stream().filter(menu -> menu.getSort() <= model.getSort()).reduce((a, b) -> {
			//a.setSort(sort);
			return a;
		});
		Stream.iterate(0, i -> i++).limit(menuList.size()).filter(i -> {
			return i >= model.getNewIndex() && i <= model.getOldIndex();
		}).forEach(i -> {
			SysMenu menu = menuList.get(i);
			System.out.println(JSONUtil.toJsonStr(menu));
		});
		return null;
	}
	
	/**
	 * 构建树查询 1. 不是懒加载情况，查询全部 2. 是懒加载，根据parentId 查询 2.1 父节点为空，则查询ID -1
	 * @param lazy 是否是懒加载
	 * @param parentId 父节点ID
	 */
	@Override
	public List<MenuTree> treeMenu(boolean lazy, String parentId) {
		if (!lazy) {
			return TreeUtil.buildTree( baseMapper.selectList(Wrappers.<SysMenu>lambdaQuery().orderByAsc(SysMenu::getSort)), CommonConstants.MENU_TREE_ROOT_ID);
		}

		String parent = parentId == null ? CommonConstants.MENU_TREE_ROOT_ID : parentId;
		return TreeUtil.buildTree(baseMapper.selectList(Wrappers.<SysMenu>lambdaQuery().eq(SysMenu::getParentId, parent).orderByAsc(SysMenu::getSort)), parent);
	}

	/**
	 * 查询菜单
	 * @param all 全部菜单
	 * @param type 类型
	 * @param parentId 父节点ID
	 */
	@Override
	public List<MenuTree> filterMenu(Set<MenuModel> all, String type, String parentId) {
		List<MenuTree> menuTreeList = all.stream().filter(menuTypePredicate(type)).map(MenuTree::new).sorted(Comparator.comparingInt(MenuTree::getSort)).collect(Collectors.toList());
		String parent = parentId == null ? CommonConstants.MENU_TREE_ROOT_ID : parentId;
		return TreeUtil.build(menuTreeList, parent);
	}

	/**
	 * menu 类型断言
	 * @param type 类型
	 * @return Predicate
	 */
	private Predicate<MenuModel> menuTypePredicate(String type) {
		return vo -> {
			if (MenuTypeEnum.TOP_MENU.getDescription().equals(type)) {
				return MenuTypeEnum.TOP_MENU.getType().equals(vo.getType());
			} else if (MenuTypeEnum.LEFT_MENU.getDescription().equals(type)) {
				return MenuTypeEnum.LEFT_MENU.getType().equals(vo.getType());
			} else {
				return !MenuTypeEnum.BUTTON.getType().equals(vo.getType());
			}
		};
	}

}
