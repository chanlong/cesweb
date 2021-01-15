package cn.cesgroup.cesweb.common.core.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public abstract class BaseEntity<E extends Model<E>> extends Model<E> {

	/** serialVersionUID(long):. */
	private static final long serialVersionUID = 1L;

	@TableId(type = IdType.ASSIGN_UUID)
	@ApiModelProperty(value = "主键ID")
	private String id;
	
	/** 备注信息 */
	@ApiModelProperty(value = "备注信息")
	private String remark;
	
	/** 0-正常 9-冻结 */
	@ApiModelProperty(value = "状态标识,9:冻结,0:正常")
	private String status;

	/** 删除标记 */
	@TableLogic
	@ApiModelProperty(value = "删除标识,1:已删除,0:正常")
	private String delFlag;
	
	/** 创建者 */
	@ApiModelProperty(value = "创建者")
	private String createBy;
	
	/** 更新者 */
	@ApiModelProperty(value = "更新者")
	private String updateBy;

	/** 创建时间 */
	@ApiModelProperty(value = "创建时间")
	private LocalDateTime createTime;
	
	/** 更新时间 */
	@ApiModelProperty(value = "更新时间")
	private LocalDateTime updateTime;
}
