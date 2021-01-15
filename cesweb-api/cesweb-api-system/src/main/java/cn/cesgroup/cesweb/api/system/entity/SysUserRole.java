package cn.cesgroup.cesweb.api.system.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户角色关联表
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 10, 2020 10:11:18 AM
 * @version 1.0.2020
 */
@Data
@ApiModel(value = "用户角色")
@EqualsAndHashCode(callSuper = true)
public class SysUserRole extends Model<SysUserRole> {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户ID
	 */
	@ApiModelProperty(value = "用户id")
	private String userId;

	/**
	 * 角色ID
	 */
	@ApiModelProperty(value = "角色id")
	private String roleId;

}
