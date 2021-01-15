package cn.cesgroup.cesweb.api.system.dto;

import java.util.List;

import cn.cesgroup.cesweb.api.system.entity.SysUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 10, 2020 10:14:04 AM
 * @version 1.0.2020
 */
@Data
@ApiModel(value = "系统用户传输对象")
@EqualsAndHashCode(callSuper = true)
public class UserDTO extends SysUser {

	/** serialVersionUID(long):. */
	private static final long serialVersionUID = 1L;

	/**
	 * 角色ID
	 */
	@ApiModelProperty(value = "角色id集合")
	private List<String> role;

	/**
	 * 部门id
	 */
	@ApiModelProperty(value = "部门id")
	private String deptId;

	/**
	 * 新密码
	 */
	@ApiModelProperty(value = "新密码")
	private String newpassword1;

}
