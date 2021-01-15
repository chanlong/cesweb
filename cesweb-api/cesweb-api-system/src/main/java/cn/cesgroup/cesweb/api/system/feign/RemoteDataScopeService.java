package cn.cesgroup.cesweb.api.system.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import cn.cesgroup.cesweb.api.system.entity.SysDeptRelation;
import cn.cesgroup.cesweb.api.system.entity.SysRole;
import cn.cesgroup.cesweb.common.core.constant.ServiceNameConstants;
import cn.cesgroup.cesweb.common.core.util.Response;

/**
 * 远程数据权限调用接口
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 10, 2020 9:53:46 AM
 * @version 1.0.2020
 */
@FeignClient(contextId = "remoteDataScopeService", value = ServiceNameConstants.SYSTEM_SERVICE)
public interface RemoteDataScopeService {

	/**
	 * 通过角色ID 查询角色列表
	 * @param roleIdList 角色ID
	 * @return
	 */
	@PostMapping("/role/getRoleList")
	Response<List<SysRole>> getRoleList(@RequestBody List<String> roleIdList);

	/**
	 * 获取子级部门
	 * @param deptId 部门ID
	 * @return
	 */
	@GetMapping("/dept/getDescendantList/{deptId}")
	Response<List<SysDeptRelation>> getDescendantList(@PathVariable("deptId") String deptId);

}
