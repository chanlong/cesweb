package cn.cesgroup.cesweb.api.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 部门树对象
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 10, 2020 10:15:42 AM
 * @version 1.0.2020
 */
@Data
@ApiModel(value = "部门树")
@EqualsAndHashCode(callSuper = true)
public class DeptTree extends TreeNode {

	/** serialVersionUID(long):. */
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "部门名称")
	private String name;

	/**
	 * 是否显示被锁定
	 */
	private Boolean isLock = true;

}
