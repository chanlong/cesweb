package cn.cesgroup.cesweb.api.system.dto;

import cn.cesgroup.cesweb.api.system.model.MenuModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 菜单树对象
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 10, 2020 10:14:31 AM
 * @version 1.0.2020
 */
@Data
@ApiModel(value = "菜单树")
@EqualsAndHashCode(callSuper = true)
public class MenuTree extends TreeNode {

	/** serialVersionUID(long):. */
	private static final long serialVersionUID = 1L;

	/**
	 * 菜单图标
	 */
	@ApiModelProperty(value = "菜单图标")
	private String icon;

	/**
	 * 菜单名称
	 */
	@ApiModelProperty(value = "菜单名称")
	private String name;

	private boolean spread = false;

	/**
	 * 前端路由标识路径
	 */
	@ApiModelProperty(value = "前端路由标识路径")
	private String path;

	/**
	 * 路由缓冲
	 */
	@ApiModelProperty(value = "路由缓冲")
	private String keepAlive;

	/**
	 * 权限编码
	 */
	@ApiModelProperty(value = "权限编码")
	private String permission;

	/**
	 * 菜单类型 （0菜单 1按钮）
	 */
	@ApiModelProperty(value = "菜单类型,0:菜单 1:按钮")
	private String type;

	/**
	 * 菜单标签
	 */
	@ApiModelProperty(value = "菜单标签")
	private String label;

	/**
	 * 排序值
	 */
	@ApiModelProperty(value = "排序值")
	private Integer sort;

	/**
	 * 是否包含子节点
	 *
	 * @since 3.7
	 */
	private Boolean hasChildren;

	public MenuTree() {
	}

	public MenuTree(String id, String name, String parentId) {
		this.id = id;
		this.name = name;
		this.label = name;
		this.parentId = parentId;
	}

	public MenuTree(String id, String name, MenuTree parent) {
		this.id = id;
		this.name = name;
		this.label = name;
		this.parentId = parent.getId();
	}

	public MenuTree(MenuModel menuVo) {
		this.id = menuVo.getMenuId();
		this.parentId = menuVo.getParentId();
		this.icon = menuVo.getIcon();
		this.name = menuVo.getName();
		this.path = menuVo.getPath();
		this.type = menuVo.getType();
		this.permission = menuVo.getPermission();
		this.label = menuVo.getName();
		this.sort = menuVo.getSort();
		this.keepAlive = menuVo.getKeepAlive();
	}

}
