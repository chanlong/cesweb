package cn.cesgroup.cesweb.api.system.dto;

import java.io.Serializable;

import cn.cesgroup.cesweb.api.system.entity.SysUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 10, 2020 10:13:35 AM
 * @version 1.0.2020
 */
@Data
@ApiModel(value = "用户信息")
public class UserInfo implements Serializable {

	/** serialVersionUID(long):. */
	private static final long serialVersionUID = 1L;

	/**
	 * 用户基本信息
	 */
	@ApiModelProperty(value = "用户基本信息")
	private SysUser sysUser;

	/**
	 * 权限标识集合
	 */
	@ApiModelProperty(value = "权限标识集合")
	private String[] permissions;

	/**
	 * 角色集合
	 */
	@ApiModelProperty(value = "角色标识集合")
	private String[] roles;

}
