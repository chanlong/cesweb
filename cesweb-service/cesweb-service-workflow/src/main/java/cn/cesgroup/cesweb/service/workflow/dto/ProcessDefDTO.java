package cn.cesgroup.cesweb.service.workflow.dto;

import lombok.Data;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;

/**
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Aug 21, 2020 11:14:12 AM
 * @version 1.0.2020
 */
@Data
public class ProcessDefDTO {

	private String category;

	private String processonDefinitionId;

	private String key;

	private String name;

	private Integer revision;

	private Long deploymentTime;

	private String xmlName;

	private String picName;

	private String deploymentId;

	private Boolean suspend;

	private String description;

	private Integer xAxis;

	private Integer yAxis;

	private Integer width;

	private Integer height;

	/**
	 * 抽取流程定义需要返回的内容
	 * @param processDefinition
	 * @param deployment
	 */
	public static ProcessDefDTO toProcessDefDTO(ProcessDefinition processDefinition, Deployment deployment) {
		ProcessDefDTO dto = new ProcessDefDTO();
		dto.setKey(processDefinition.getKey());
		dto.setName(deployment.getName());
		dto.setXmlName(processDefinition.getResourceName());
		dto.setPicName(processDefinition.getDiagramResourceName());
		dto.setSuspend(processDefinition.isSuspended());
		dto.setCategory(processDefinition.getCategory());
		dto.setRevision(processDefinition.getVersion());
		dto.setDescription(processDefinition.getDescription());
		dto.setDeploymentId(deployment.getId());
		dto.setDeploymentTime(deployment.getDeploymentTime().getTime());
		dto.setProcessonDefinitionId(processDefinition.getId());
		return dto;
	}

}
