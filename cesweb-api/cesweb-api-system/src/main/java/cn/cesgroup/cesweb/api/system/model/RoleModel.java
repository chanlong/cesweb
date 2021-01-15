package cn.cesgroup.cesweb.api.system.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * 角色
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 10, 2020 10:04:34 AM
 * @version 1.0.2020
 */
@Data
@ApiModel(value = "前端角色展示对象")
public class RoleModel {

	/**
	 * 角色id
	 */
	private String roleId;

	/**
	 * 菜单列表
	 */
	private String menuIds;

}
