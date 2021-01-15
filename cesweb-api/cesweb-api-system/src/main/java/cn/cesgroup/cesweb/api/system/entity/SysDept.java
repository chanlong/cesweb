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
 * 部门信息表
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 10, 2020 10:05:06 AM
 * @version 1.0.2020
 */
@Data
@ApiModel(value = "部门")
@EqualsAndHashCode(callSuper = true)
public class SysDept extends Model<SysDept> {

	private static final long serialVersionUID = 1L;

	@TableId(value = "dept_id", type = IdType.ASSIGN_UUID)
	@ApiModelProperty(value = "部门id")
	private String deptId;

	/**
	 * 部门名称
	 */
	@NotBlank(message = "部门名称不能为空")
	@ApiModelProperty(value = "部门名称")
	private String name;

	/**
	 * 排序
	 */
	@NotNull(message = "排序值不能为空")
	@ApiModelProperty(value = "排序值")
	private Integer sort;

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
	 * 父级部门id
	 */
	@ApiModelProperty(value = "父级部门id")
	private String parentId;

	/**
	 * 是否删除 1：已删除 0：正常
	 */
	@TableLogic
	@ApiModelProperty(value = "删除标记,1:已删除,0:正常")
	private String delFlag;

}
