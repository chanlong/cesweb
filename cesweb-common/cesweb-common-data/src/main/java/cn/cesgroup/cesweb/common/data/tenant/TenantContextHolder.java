package cn.cesgroup.cesweb.common.data.tenant;

import com.alibaba.ttl.TransmittableThreadLocal;
import lombok.experimental.UtilityClass;

/**
 * 租户工具类
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 13, 2020 3:17:03 PM
 * @version 1.0.2020
 */
@UtilityClass
public class TenantContextHolder {

	private final ThreadLocal<String> THREAD_LOCAL_TENANT = new TransmittableThreadLocal<>();

	/**
	 * TTL 设置租户ID<br/>
	 * <b>谨慎使用此方法,避免嵌套调用。尽量使用 {@code TenantBroker} </b>
	 * @param tenantId
	 * @see TenantBroker
	 */
	public void setTenantId(String tenantId) {
		THREAD_LOCAL_TENANT.set(tenantId);
	}

	/**
	 * 获取TTL中的租户ID
	 * @return
	 */
	public String getTenantId() {
		return THREAD_LOCAL_TENANT.get();
	}

	public void clear() {
		THREAD_LOCAL_TENANT.remove();
	}

}
