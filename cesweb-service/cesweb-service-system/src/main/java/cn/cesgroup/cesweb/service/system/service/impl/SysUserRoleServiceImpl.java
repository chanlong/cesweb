package cn.cesgroup.cesweb.service.system.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cn.cesgroup.cesweb.api.system.entity.SysUserRole;
import cn.cesgroup.cesweb.service.system.mapper.SysUserRoleMapper;
import cn.cesgroup.cesweb.service.system.service.SysUserRoleService;

/**
 * 用户角色表 服务实现类
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 13, 2020 3:14:03 PM
 * @version 1.0.2020
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {

	/**
	 * 根据用户Id删除该用户的角色关系
	 * @param userId 用户ID
	 * @return boolean
	 * @author 寻欢·李
	 * @date 2017年12月7日 16:31:38
	 */
	@Override
	public Boolean deleteByUserId(String userId) {
		return baseMapper.deleteByUserId(userId);
	}

}
