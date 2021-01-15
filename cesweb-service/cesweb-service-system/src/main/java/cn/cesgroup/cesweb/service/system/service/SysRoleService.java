package cn.cesgroup.cesweb.service.system.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

import cn.cesgroup.cesweb.api.system.entity.SysRole;

/**
 * 服务类
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 13, 2020 1:35:19 PM
 * @version 1.0.2020
 */
public interface SysRoleService extends IService<SysRole> {

	/**
	 * 通过用户ID，查询角色信息
	 * @param userId
	 */
	List<SysRole> findRolesByUserId(String userId);

	/**
	 * 通过角色ID，删除角色
	 * @param id
	 */
	Boolean removeRoleById(String id);

}
