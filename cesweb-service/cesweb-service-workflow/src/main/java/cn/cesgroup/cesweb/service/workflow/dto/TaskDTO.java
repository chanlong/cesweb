package cn.cesgroup.cesweb.service.workflow.dto;

import lombok.Data;

import java.util.Date;

/**
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Aug 21, 2020 11:13:48 AM
 * @version 1.0.2020
 */
@Data
public class TaskDTO {

	private String applicant;

	private String taskId;

	private String taskName;

	private String title;

	private String pdName;

	private String version;

	private Date time;

	private String processInstanceId;

	private String status;

	private String nodeKey;

	private String processDefKey;

	private String category;

}
