package cn.cesgroup.cesweb.service.workflow.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import cn.cesgroup.cesweb.common.data.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 请假流程
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Aug 21, 2020 10:37:27 AM
 * @version 1.0.2020
 */
@Data
@TableName("oa_leave_bill")
@EqualsAndHashCode(callSuper = true)
public class LeaveBill extends BaseEntity<LeaveBill> {

	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@TableId(type = IdType.ASSIGN_UUID)
	private String leaveId;

	/**
	 * 申请人
	 */
	private String username;

	/**
	 * 天数
	 */
	private Integer days;

	/**
	 * 备注
	 */
	private String content;

	/**
	 * 0-未提交,1-未审核,2-批准,9-驳回
	 */
	private String state;

	/**
	 * 提交时间
	 */
	private LocalDateTime leaveTime;

	/**
	 * 提交时间
	 */
	private LocalDateTime createTime;

	/**
	 * 修改时间
	 */
	private LocalDateTime updateTime;

	/**
	 * 删除标志
	 */
	@TableLogic
	private String delFlag;

	/**
	 * 租户ID
	 */
	private String tenantId;
}
