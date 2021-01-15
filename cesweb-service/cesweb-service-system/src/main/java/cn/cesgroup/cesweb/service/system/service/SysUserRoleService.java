package cn.cesgroup.cesweb.service.system.service;

import com.baomidou.mybatisplus.extension.service.IService;

import cn.cesgroup.cesweb.api.system.entity.SysUserRole;

/**
 * 用户角色表 服务类
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 13, 2020 1:34:03 PM
 * @version 1.0.2020
 */
public interface SysUserRoleService extends IService<SysUserRole> {

	/**
	 * 根据用户Id删除该用户的角色关系
	 * @param userId 用户ID
	 * @return boolean
	 * @author 寻欢·李
	 * @date 2017年12月7日 16:31:38
	 */
	Boolean deleteByUserId(String userId);

}
