package cn.cesgroup.cesweb.api.system.feign;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cn.cesgroup.cesweb.common.core.constant.SecurityConstants;
import cn.cesgroup.cesweb.common.core.constant.ServiceNameConstants;
import cn.cesgroup.cesweb.common.core.util.Response;

/**
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 10, 2020 9:58:21 AM
 * @version 1.0.2020
 */
@FeignClient(contextId = "remoteTokenService", value = ServiceNameConstants.AUTH_SERVICE)
public interface RemoteTokenService {

	/**
	 * 分页查询token 信息
	 * @param from 内部调用标志
	 * @param params 分页参数
	 * @param from 内部调用标志
	 * @return page
	 */
	@PostMapping("/token/page")
	<T> Response<Page<T>> getTokenPage(@RequestBody Map<String, Object> params, @RequestHeader(SecurityConstants.FROM) String from);

	/**
	 * 删除token
	 * @param from 内部调用标志
	 * @param token token
	 * @param from 内部调用标志
	 * @return
	 */
	@DeleteMapping("/token/{token}")
	Response<Boolean> removeTokenById(@PathVariable("token") String token, @RequestHeader(SecurityConstants.FROM) String from);

}
