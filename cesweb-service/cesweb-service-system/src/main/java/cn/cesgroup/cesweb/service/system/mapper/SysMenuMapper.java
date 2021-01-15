package cn.cesgroup.cesweb.service.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import cn.cesgroup.cesweb.api.system.entity.SysMenu;
import cn.cesgroup.cesweb.api.system.model.MenuModel;

/**
 * 菜单权限表 Mapper 接口
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 13, 2020 2:57:04 PM
 * @version 1.0.2020
 */
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {

	/**
	 * 通过角色编号查询菜单
	 * @param roleId 角色ID
	 * @return
	 */
	List<MenuModel> listMenusByRoleId(String roleId);

	/**
	 * 通过角色ID查询权限
	 * @param roleIds Ids
	 * @return
	 */
	List<String> listPermissionsByRoleIds(String roleIds);

}
