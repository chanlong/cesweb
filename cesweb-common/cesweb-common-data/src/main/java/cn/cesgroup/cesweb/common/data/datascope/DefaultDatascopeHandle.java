package cn.cesgroup.cesweb.common.data.datascope;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;

import cn.cesgroup.cesweb.api.system.entity.SysDeptRelation;
import cn.cesgroup.cesweb.api.system.entity.SysRole;
import cn.cesgroup.cesweb.api.system.feign.RemoteDataScopeService;
import cn.cesgroup.cesweb.common.core.constant.SecurityConstants;
import cn.cesgroup.cesweb.common.security.service.OAuth2User;
import cn.cesgroup.cesweb.common.security.util.SecurityUtils;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;

/**
 * 默认data scope 判断处理器
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 10, 2020 3:30:23 PM
 * @version 1.0.2020
 */
public class DefaultDatascopeHandle implements DataScopeHandle {

	@Autowired
	private RemoteDataScopeService dataScopeService;

	/**
	 * 计算用户数据权限
	 * @param deptList
	 * @return
	 */
	@Override
	public Boolean calcScope(List<String> deptList) {
		OAuth2User user = SecurityUtils.getUser();
		List<String> roleIdList = user.getAuthorities()
									  .stream()
									  .map(GrantedAuthority::getAuthority)
									  .filter(authority -> authority.startsWith(SecurityConstants.ROLE))
									  .map(authority -> authority.split(StrUtil.UNDERLINE)[1]).collect(Collectors.toList());
		// 当前用户的角色为空
		if (CollectionUtil.isEmpty(roleIdList)) {
			return false;
		}
		SysRole role = dataScopeService.getRoleList(roleIdList).getData().stream().min(Comparator.comparingInt(SysRole::getDsType)).orElse(null);
		// 角色有可能已经删除了
		if (role == null) {
			return false;
		}
		Integer dsType = role.getDsType();
		// 查询全部
		if (DataScopeTypeEnum.ALL.getType() == dsType) {
			return true;
		}
		// 自定义
		if (DataScopeTypeEnum.CUSTOM.getType() == dsType) {
			String dsScope = role.getDsScope();
			deptList.addAll(Arrays.stream(dsScope.split(StrUtil.COMMA)).map(String::valueOf).collect(Collectors.toList()));
		}
		// 查询本级及其下级
		if (DataScopeTypeEnum.OWN_CHILD_LEVEL.getType() == dsType) {
			List<String> deptIdList = dataScopeService.getDescendantList(user.getDeptId())
													  .getData()
													  .stream()
													  .map(SysDeptRelation::getDescendant)
													  .collect(Collectors.toList());
			deptList.addAll(deptIdList);
		}
		// 只查询本级
		if (DataScopeTypeEnum.OWN_LEVEL.getType() == dsType) {
			deptList.add(user.getDeptId());
		}
		return false;
	}

}
