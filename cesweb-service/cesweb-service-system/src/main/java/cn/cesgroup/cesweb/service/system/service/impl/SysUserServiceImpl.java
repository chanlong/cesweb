package cn.cesgroup.cesweb.service.system.service.impl;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cn.cesgroup.cesweb.api.system.dto.UserDTO;
import cn.cesgroup.cesweb.api.system.dto.UserInfo;
import cn.cesgroup.cesweb.api.system.entity.SysDept;
import cn.cesgroup.cesweb.api.system.entity.SysDeptRelation;
import cn.cesgroup.cesweb.api.system.entity.SysRole;
import cn.cesgroup.cesweb.api.system.entity.SysUser;
import cn.cesgroup.cesweb.api.system.entity.SysUserRole;
import cn.cesgroup.cesweb.api.system.model.MenuModel;
import cn.cesgroup.cesweb.api.system.model.UserModel;
import cn.cesgroup.cesweb.common.core.constant.CacheConstants;
import cn.cesgroup.cesweb.common.core.constant.CommonConstants;
import cn.cesgroup.cesweb.common.core.util.Response;
import cn.cesgroup.cesweb.common.data.datascope.DataScope;
import cn.cesgroup.cesweb.common.security.util.SecurityUtils;
import cn.cesgroup.cesweb.service.system.mapper.SysUserMapper;
import cn.cesgroup.cesweb.service.system.service.SysDeptRelationService;
import cn.cesgroup.cesweb.service.system.service.SysDeptService;
import cn.cesgroup.cesweb.service.system.service.SysMenuService;
import cn.cesgroup.cesweb.service.system.service.SysRoleService;
import cn.cesgroup.cesweb.service.system.service.SysUserRoleService;
import cn.cesgroup.cesweb.service.system.service.SysUserService;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 用户接口
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 13, 2020 3:01:10 PM
 * @version 1.0.2020
 */
@Slf4j
@Service
@AllArgsConstructor
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

	private static final PasswordEncoder ENCODER = new BCryptPasswordEncoder();

	private final SysMenuService sysMenuService;

	private final SysRoleService sysRoleService;

	private final SysDeptService sysDeptService;

	private final SysUserRoleService sysUserRoleService;

	private final SysDeptRelationService sysDeptRelationService;

	/**
	 * 保存用户信息
	 * 
	 * @param userDto DTO 对象
	 * @return success/fail
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean saveUser(UserDTO userDto) {
		SysUser sysUser = new SysUser();
		BeanUtils.copyProperties(userDto, sysUser);
		sysUser.setDelFlag(CommonConstants.STATUS_NORMAL);
		sysUser.setPassword(ENCODER.encode(userDto.getPassword()));
		baseMapper.insert(sysUser);
		List<SysUserRole> userRoleList = userDto.getRole().stream().map(roleId -> {
			SysUserRole userRole = new SysUserRole();
			userRole.setUserId(sysUser.getUserId());
			userRole.setRoleId(roleId);
			return userRole;
		}).collect(Collectors.toList());
		return sysUserRoleService.saveBatch(userRoleList);
	}

	/**
	 * 通过查用户的全部信息
	 * 
	 * @param sysUser 用户
	 * @return
	 */
	@Override
	public UserInfo findUserInfo(SysUser sysUser) {
		UserInfo userInfo = new UserInfo();
		userInfo.setSysUser(sysUser);
		// 设置角色列表 （ID）
		List<String> roleIds = sysRoleService.findRolesByUserId(sysUser.getUserId())
											 .stream()
											 .map(SysRole::getRoleId)
											 .collect(Collectors.toList());
		userInfo.setRoles(ArrayUtil.toArray(roleIds, String.class));

		// 设置权限列表（menu.permission）
		Set<String> permissions = new HashSet<>();
		roleIds.forEach(roleId -> {
			List<String> permissionList = sysMenuService.findMenuByRoleId(roleId)
														.stream()
														.filter(menuVo -> StringUtils.isNotEmpty(menuVo.getPermission()))
														.map(MenuModel::getPermission).collect(Collectors.toList());
			permissions.addAll(permissionList);
		});
		userInfo.setPermissions(ArrayUtil.toArray(permissions, String.class));
		return userInfo;
	}

	/**
	 * 分页查询用户信息（含有角色信息）
	 * 
	 * @param page    分页对象
	 * @param userDTO 参数列表
	 * @return
	 */
	@Override
	public IPage<List<UserModel>> getUsersWithRolePage(Page<SysUser> page, UserDTO userDTO) {
		return baseMapper.getUserModelsPage(page, userDTO, new DataScope());
	}

	/**
	 * 通过ID查询用户信息
	 * 
	 * @param id 用户ID
	 * @return 用户信息
	 */
	@Override
	public UserModel selectUserVoById(String id) {
		return baseMapper.getUserModelById(id);
	}

	/**
	 * 删除用户
	 * 
	 * @param sysUser 用户
	 * @return Boolean
	 */
	@Override
	@CacheEvict(value = CacheConstants.USER_DETAILS, key = "#sysUser.username")
	public Boolean deleteUserById(SysUser sysUser) {
		sysUserRoleService.deleteByUserId(sysUser.getUserId());
		this.removeById(sysUser.getUserId());
		return Boolean.TRUE;
	}

	@Override
	@CacheEvict(value = CacheConstants.USER_DETAILS, key = "#userDto.username")
	public Response<Boolean> updateUserInfo(UserDTO userDto) {
		UserModel userModel = baseMapper.getUserModelByUsername(userDto.getUsername());
		SysUser sysUser = new SysUser();
		if (StrUtil.isNotBlank(userDto.getPassword()) && StrUtil.isNotBlank(userDto.getNewpassword1())) {
			if (ENCODER.matches(userDto.getPassword(), userModel.getPassword())) {
				sysUser.setPassword(ENCODER.encode(userDto.getNewpassword1()));
			} else {
				log.info("原密码错误，修改密码失败:{}", userDto.getUsername());
				return Response.failed("原密码错误，修改失败");
			}
		}
		sysUser.setPhone(userDto.getPhone());
		sysUser.setUserId(userModel.getUserId());
		sysUser.setAvatar(userDto.getAvatar());
		return Response.ok(this.updateById(sysUser));
	}

	@Override
	@CacheEvict(value = CacheConstants.USER_DETAILS, key = "#userDto.username")
	public Boolean updateUser(UserDTO userDto) {
		SysUser sysUser = new SysUser();
		BeanUtils.copyProperties(userDto, sysUser);
		sysUser.setUpdateTime(LocalDateTime.now());

		if (StrUtil.isNotBlank(userDto.getPassword())) {
			sysUser.setPassword(ENCODER.encode(userDto.getPassword()));
		}
		this.updateById(sysUser);

		sysUserRoleService
				.remove(Wrappers.<SysUserRole>update().lambda().eq(SysUserRole::getUserId, userDto.getUserId()));
		userDto.getRole().forEach(roleId -> {
			SysUserRole userRole = new SysUserRole();
			userRole.setUserId(sysUser.getUserId());
			userRole.setRoleId(roleId);
			userRole.insert();
		});
		return Boolean.TRUE;
	}

	/**
	 * 查询上级部门的用户信息
	 * 
	 * @param username 用户名
	 * @return R
	 */
	@Override
	public List<SysUser> listAncestorUsers(String username) {
		SysUser sysUser = this.getOne(Wrappers.<SysUser>query().lambda().eq(SysUser::getUsername, username));

		SysDept sysDept = sysDeptService.getById(sysUser.getDeptId());
		if (sysDept == null) {
			return null;
		}

		String parentId = sysDept.getParentId();
		return this.list(Wrappers.<SysUser>query().lambda().eq(SysUser::getDeptId, parentId));
	}

	/**
	 * 获取当前用户的子部门信息
	 * 
	 * @return 子部门列表
	 */
	public List<String> getChildDepts() {
		String deptId = SecurityUtils.getUser().getDeptId();
		// 获取当前部门的子部门
		return sysDeptRelationService
				.list(Wrappers.<SysDeptRelation>query().lambda().eq(SysDeptRelation::getAncestor, deptId)).stream()
				.map(SysDeptRelation::getDescendant).collect(Collectors.toList());
	}

}
