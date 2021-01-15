package cn.cesgroup.cesweb.api.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * 角色信息表
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 10, 2020 10:09:38 AM
 * @version 1.0.2020
 */
@Data
@ApiModel(value = "角色")
@EqualsAndHashCode(callSuper = true)
public class SysRole extends Model<SysRole> {

	private static final long serialVersionUID = 1L;

	@TableId(value = "role_id", type = IdType.ASSIGN_UUID)
	@ApiModelProperty(value = "角色编号")
	private String roleId;

	@NotBlank(message = "角色名称不能为空")
	@ApiModelProperty(value = "角色名称")
	private String roleName;

	@NotBlank(message = "角色标识不能为空")
	@ApiModelProperty(value = "角色标识")
	private String roleCode;

	@ApiModelProperty(value = "角色描述")
	private String roleDesc;

	@NotNull(message = "数据权限类型不能为空")
	@ApiModelProperty(value = "数据权限类型")
	private Integer dsType;

	/**
	 * 数据权限作用范围
	 */
	@ApiModelProperty(value = "数据权限作用范围")
	private String dsScope;

	/**
	 * 创建时间
	 */
	@ApiModelProperty(value = "创建时间")
	private LocalDateTime createTime;

	/**
	 * 修改时间
	 */
	@ApiModelProperty(value = "修改时间")
	private LocalDateTime updateTime;

	/**
	 * 删除标识（0-正常,1-删除）
	 */
	@TableLogic
	@ApiModelProperty(value = "删除标记,1:已删除,0:正常")
	private String delFlag;

}
