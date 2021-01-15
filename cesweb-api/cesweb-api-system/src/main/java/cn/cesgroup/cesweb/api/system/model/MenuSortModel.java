/**
 * <p>Copyright:Copyright(c) 2020</p>
 * <p>Company:Professional</p>
 * <p>Package:cn.cesgroup.cesweb.api.system.model</p>
 * <p>File:MenuSortModel.java</p>
 * <p>类更新历史信息</p>
 * @todo chanlong(陈龙) 创建于 Jul 28, 2020 5:21:47 PM
 */
package cn.cesgroup.cesweb.api.system.model;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>描述: 菜单排序</p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 28, 2020 5:21:47 PM
 * @version 1.0.2020
 */
@Data
@ApiModel(value = "菜单排序对象")
public class MenuSortModel implements Serializable {
	
	/** serialVersionUID(long):. */
	private static final long serialVersionUID = 1L;
	
	/** 菜单父亲ID */
	@ApiModelProperty(value = "菜单父id")
	private String pid;
	
	/** 当前菜单排序号 */
	@ApiModelProperty(value = "当前菜单排序号")
	private Integer sort;
	
	/** 菜单原索引 */
	@ApiModelProperty(value = "菜单原索引")
	private Integer oldIndex;
	
	/** 菜单新索引 */
	@ApiModelProperty(value = "菜单新索引")
	private Integer newIndex;

}
