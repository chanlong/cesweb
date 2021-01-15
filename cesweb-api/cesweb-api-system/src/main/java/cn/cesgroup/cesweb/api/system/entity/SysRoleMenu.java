package cn.cesgroup.cesweb.api.system.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色菜单关联表
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 10, 2020 10:09:57 AM
 * @version 1.0.2020
 */
@Data
@ApiModel(value = "角色菜单")
@EqualsAndHashCode(callSuper = true)
public class SysRoleMenu extends Model<SysRoleMenu> {

	private static final long serialVersionUID = 1L;

	/**
	 * 角色ID
	 */
	@ApiModelProperty(value = "角色id")
	private String roleId;

	/**
	 * 菜单ID
	 */
	@ApiModelProperty(value = "菜单id")
	private String menuId;

}
