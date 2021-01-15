package cn.cesgroup.cesweb.api.system.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 部门关系表
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 10, 2020 10:05:35 AM
 * @version 1.0.2020
 */
@Data
@ApiModel(value = "部门关系")
@EqualsAndHashCode(callSuper = true)
public class SysDeptRelation extends Model<SysDeptRelation> {

	private static final long serialVersionUID = 1L;

	/**
	 * 祖先节点
	 */
	@ApiModelProperty(value = "祖先节点")
	private String ancestor;

	/**
	 * 后代节点
	 */
	@ApiModelProperty(value = "后代节点")
	private String descendant;

}
