package cn.cesgroup.cesweb.api.system.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import cn.cesgroup.cesweb.common.core.constant.SecurityConstants;
import cn.cesgroup.cesweb.common.core.constant.ServiceNameConstants;
import cn.cesgroup.cesweb.common.core.util.Response;

/**
 * 参数查询接口
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 10, 2020 9:57:05 AM
 * @version 1.0.2020
 */
@FeignClient(contextId = "remoteParamService", value = ServiceNameConstants.SYSTEM_SERVICE)
public interface RemoteParamService {

	/**
	 * 通过key 查询参数配置
	 * @param key key
	 * @param from 声明成内部调用，避免MQ 等无法调用
	 */
	@GetMapping("/param/publicValue/{key}")
	Response<String> getByKey(@PathVariable("key") String key, @RequestHeader(SecurityConstants.FROM) String from);

}
