package cn.cesgroup.cesweb.service.workflow.model.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Aug 21, 2020 11:14:01 AM
 * @version 1.0.2020
 */
@Data
public class StartTaskForm {

	@NotEmpty
	public String procDefKey;

	public String entityId;

	@NotEmpty
	public String title;

	public String comment;

}
