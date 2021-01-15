package cn.cesgroup.cesweb.api.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 10, 2020 10:14:19 AM
 * @version 1.0.2020
 */
@Data
@ApiModel(value = "树形节点")
public class TreeNode implements Serializable {

	/** serialVersionUID(long):. */
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "当前节点id")
	protected String id;

	@ApiModelProperty(value = "父节点id")
	protected String parentId;

	@ApiModelProperty(value = "子节点列表")
	protected List<TreeNode> children = new ArrayList<TreeNode>();

	public void add(TreeNode node) {
		children.add(node);
	}

}
