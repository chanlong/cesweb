package cn.cesgroup.cesweb.service.system.service;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import cn.cesgroup.cesweb.api.system.dto.UserDTO;
import cn.cesgroup.cesweb.api.system.dto.UserInfo;
import cn.cesgroup.cesweb.api.system.entity.SysUser;
import cn.cesgroup.cesweb.api.system.model.UserModel;
import cn.cesgroup.cesweb.common.core.util.Response;

/**
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 13, 2020 1:32:06 PM
 * @version 1.0.2020
 */
public interface SysUserService extends IService<SysUser> {

	/**
	 * 查询用户信息
	 * @param sysUser 用户
	 * @return userInfo
	 */
	UserInfo findUserInfo(SysUser sysUser);

	/**
	 * 分页查询用户信息（含有角色信息）
	 * @param page 分页对象
	 * @param userDTO 参数列表
	 */
	IPage<List<UserModel>> getUsersWithRolePage(Page<SysUser> page, UserDTO userDTO);

	/**
	 * 删除用户
	 * @param sysUser 用户
	 * @return boolean
	 */
	Boolean deleteUserById(SysUser sysUser);

	/**
	 * 更新当前用户基本信息
	 * @param userDto 用户信息
	 * @return Boolean
	 */
	Response<Boolean> updateUserInfo(UserDTO userDto);

	/**
	 * 更新指定用户信息
	 * @param userDto 用户信息
	 * @return boolean
	 */
	Boolean updateUser(UserDTO userDto);

	/**
	 * 通过ID查询用户信息
	 * @param id 用户ID
	 * @return 用户信息
	 */
	UserModel selectUserVoById(String id);

	/**
	 * 查询上级部门的用户信息
	 * @param username 用户名
	 * @return List
	 */
	List<SysUser> listAncestorUsers(String username);

	/**
	 * 保存用户信息
	 * @param userDto DTO 对象
	 * @return success/fail
	 */
	Boolean saveUser(UserDTO userDto);

}
