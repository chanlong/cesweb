package cn.cesgroup.cesweb.service.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cn.cesgroup.cesweb.api.system.dto.UserDTO;
import cn.cesgroup.cesweb.api.system.entity.SysUser;
import cn.cesgroup.cesweb.api.system.model.UserModel;
import cn.cesgroup.cesweb.common.data.datascope.DataScope;
import cn.cesgroup.cesweb.common.data.datascope.DataScopeMapper;

/**
 * 用户信息表 Mapper 接口
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 13, 2020 2:59:48 PM
 * @version 1.0.2020
 */
@Mapper
public interface SysUserMapper extends DataScopeMapper<SysUser> {

	/**
	 * 通过ID查询用户信息
	 * @param id 用户ID
	 * @return userVo
	 */
	UserModel getUserModelById(String id);
	
	/**
	 * 通过用户名查询用户信息（含有角色信息）
	 * @param username 用户名
	 * @return userVo
	 */
	UserModel getUserModelByUsername(String username);

	/**
	 * 分页查询用户信息（含角色）
	 * @param page 分页
	 * @param userDTO 查询参数
	 * @param dataScope
	 * @return list
	 */
	IPage<List<UserModel>> getUserModelsPage(Page<SysUser> page, @Param("query") UserDTO userDTO, DataScope dataScope);

}
