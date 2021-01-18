package cn.cesgroup.cesweb.service.tenant.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

import cn.cesgroup.cesweb.api.tenant.entity.Tenant;

/**
 * 租户管理
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 13, 2020 1:34:22 PM
 * @version 1.0.2020
 */
public interface TenantService extends IService<Tenant> {

	/**
	 * 获取正常的租户
	 */
	List<Tenant> getNormalTenant();

	/**
	 * 保存租户
	 * @param sysTenant
	 */
	Boolean saveTenant(Tenant tenant);

}
