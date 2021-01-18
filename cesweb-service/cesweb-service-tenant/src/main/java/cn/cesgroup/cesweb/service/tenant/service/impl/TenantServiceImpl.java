package cn.cesgroup.cesweb.service.tenant.service.impl;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cn.cesgroup.cesweb.api.tenant.entity.Tenant;
import cn.cesgroup.cesweb.common.core.constant.CacheConstants;
import cn.cesgroup.cesweb.common.core.constant.CommonConstants;
import cn.cesgroup.cesweb.service.tenant.mapper.TenantMapper;
import cn.cesgroup.cesweb.service.tenant.service.TenantService;
import lombok.AllArgsConstructor;

/**
 * 租户 服务实现类
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 13, 2020 3:14:35 PM
 * @version 1.0.2020
 */
@Service
@AllArgsConstructor
public class TenantServiceImpl extends ServiceImpl<TenantMapper, Tenant> implements TenantService {

	// private static final PasswordEncoder ENCODER = new BCryptPasswordEncoder();

	/**
	 * 获取正常状态租户
	 * <p>
	 * 1. 状态正常 2. 开始时间小于等于当前时间 3. 结束时间大于等于当前时间
	 * @return
	 */
	@Override
	@Cacheable(value = CacheConstants.TENANT_DETAILS)
	public List<Tenant> getNormalTenant() {
		return baseMapper.selectList(Wrappers.<Tenant>lambdaQuery().eq(Tenant::getStatus, CommonConstants.STATUS_NORMAL));
	}

	/**
	 * 保存租户
	 * <p>
	 * 1. 保存租户 
	 * 2. 初始化权限相关表	- sys_user 
	 * 					- sys_role 
	 * 					- sys_menu 
	 * 					- sys_user_role 
	 * 					- sys_role_menu 
	 * 					- sys_dict 
	 * 					- sys_dict_item 
	 * 					- sys_client_details 
	 * 					- sys_public_params
	 * @param Tenant 租户实体
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	@CacheEvict(value = CacheConstants.TENANT_DETAILS)
	public Boolean saveTenant(Tenant tenant) {
		this.save(tenant);
		
		return false;
	}
}
