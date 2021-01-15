package cn.cesgroup.cesweb.common.core.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>描述: 流程任务状态</p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 7, 2020 1:57:56 PM
 * @version 1.0.2020
 */
@Getter
@AllArgsConstructor
public enum TaskStatusEnum {

	/**
	 * 未提交
	 */
	UNSUBMIT("0", "未提交"),

	/**
	 * 审核中
	 */
	CHECK("1", "审核中"),

	/**
	 * 已完成
	 */
	COMPLETED("2", "已完成"),
	
	/**
	 * 撤回
	 */
	REVOKE("3", "撤回"),

	/**
	 * 驳回
	 */
	OVERRULE("9", "驳回");
	

	/**
	 * 类型
	 */
	private final String status;

	/**
	 * 描述
	 */
	private final String description;

}
