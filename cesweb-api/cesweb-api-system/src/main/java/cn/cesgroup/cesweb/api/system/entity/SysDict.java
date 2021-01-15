package cn.cesgroup.cesweb.api.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 数据字典表
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 10, 2020 10:06:07 AM
 * @version 1.0.2020
 */
@Data
@ApiModel(value = "字典类型")
@EqualsAndHashCode(callSuper = true)
public class SysDict extends Model<SysDict> {

	private static final long serialVersionUID = 1L;

	/**
	 * 编号
	 */
	@TableId(type = IdType.ASSIGN_UUID)
	@ApiModelProperty(value = "字典编号")
	private String id;

	/**
	 * 类型
	 */
	@ApiModelProperty(value = "字典类型")
	private String type;

	/**
	 * 描述
	 */
	@ApiModelProperty(value = "字典描述")
	private String description;

	/**
	 * 创建时间
	 */
	@ApiModelProperty(value = "创建时间")
	private LocalDateTime createTime;

	/**
	 * 更新时间
	 */
	@ApiModelProperty(value = "更新时间")
	private LocalDateTime updateTime;

	/**
	 * 是否是系统内置
	 */
	@TableField(value = "`system`")
	@ApiModelProperty(value = "是否系统内置")
	private String system;

	/**
	 * 备注信息
	 */
	@ApiModelProperty(value = "备注信息")
	private String remarks;

	/**
	 * 删除标记
	 */
	@TableLogic
	@ApiModelProperty(value = "删除标记,1:已删除,0:正常")
	private String delFlag;

}
