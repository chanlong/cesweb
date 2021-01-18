/**
 * <p>Copyright:Copyright(c) 2020</p>
 * <p>Company:Professional</p>
 * <p>Package:cn.cesgroup.cesweb.api.tenant.feign</p>
 * <p>File:RemotTenantService.java</p>
 * <p>类更新历史信息</p>
 * @todo chanlong(陈龙) 创建于 2020年12月14日 下午1:07:34
 */
package cn.cesgroup.cesweb.api.tenant.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import cn.cesgroup.cesweb.api.tenant.entity.Tenant;
import cn.cesgroup.cesweb.common.core.constant.SecurityConstants;
import cn.cesgroup.cesweb.common.core.constant.ServiceNameConstants;
import cn.cesgroup.cesweb.common.core.util.Response;

/**
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date 2020年12月14日 下午1:07:34
 * @version 1.0.2020
 */
@FeignClient(contextId = "remotTenantService", value = ServiceNameConstants.TENANT_SERVICE)
public interface RemotTenantService {

	/**
	 * 查询全部有效租户
	 * @param from 内部标志
	 */
	@GetMapping("/tenant/list")
	Response<List<Tenant>> list(@RequestHeader(SecurityConstants.FROM) String from);
	
	
}
