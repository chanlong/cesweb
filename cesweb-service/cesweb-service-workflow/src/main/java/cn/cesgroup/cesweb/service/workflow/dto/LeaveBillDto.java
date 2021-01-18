package cn.cesgroup.cesweb.service.workflow.dto;

import java.util.Date;
import java.util.List;

import cn.cesgroup.cesweb.service.workflow.entity.LeaveBill;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Aug 21, 2020 12:26:28 PM
 * @version 1.0.2020
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class LeaveBillDto extends LeaveBill {

	/** serialVersionUID(long):. */
	private static final long serialVersionUID = 1L;

	/**
	 * 任务提交时间
	 */
	private Date time;
	
	/**
	 * 任务ID
	 */
	private String taskId;

	/**
	 * 批准信息
	 */
	private String comment;

	private String taskFlag;
	
	/**
	 * 连线信息
	 */
	private List<String> flagList;

}
