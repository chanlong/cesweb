package cn.cesgroup.cesweb.common.data.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import cn.cesgroup.cesweb.common.core.util.FieldUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public abstract class BaseEntity<E extends Model<E>> extends Model<E> {

	/** serialVersionUID(long):. */
	private static final long serialVersionUID = 1L;

	/** 创建者 */
	private String createBy;
	
	/** 创建时间 */
	private LocalDateTime createTime;
	
	/** 更新者 */
	private String updateBy;

	/** 更新时间 */
	private LocalDateTime updateTime;
	
	/** 租户ID */
	private String tenantId;
	
	/** 备注信息 */
	private String remark;
	
	/**
	 * 主键值
	 */
	@Override
	public Serializable pkVal() {
		return FieldUtil.getPropertyWithAnnotation(this, TableId.class);
	}
}
