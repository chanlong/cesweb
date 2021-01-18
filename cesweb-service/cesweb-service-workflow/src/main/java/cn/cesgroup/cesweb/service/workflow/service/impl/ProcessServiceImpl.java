package cn.cesgroup.cesweb.service.workflow.service.impl;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cn.cesgroup.cesweb.common.core.constant.PaginationConstants;
import cn.cesgroup.cesweb.common.core.constant.enums.ProcessStatusEnum;
import cn.cesgroup.cesweb.common.core.constant.enums.ResourceTypeEnum;
import cn.cesgroup.cesweb.common.data.tenant.TenantContextHolder;
import cn.cesgroup.cesweb.service.workflow.dto.ProcessDefDTO;
import cn.cesgroup.cesweb.service.workflow.service.ProcessService;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;

/**
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Aug 21, 2020 10:26:43 AM
 * @version 1.0.2020
 */
@Service
@AllArgsConstructor
public class ProcessServiceImpl implements ProcessService {

	private final RuntimeService runtimeService;

	private final RepositoryService repositoryService;

	/**
	 * 分页流程列表
	 * @param params
	 */
	@Override
	public IPage<ProcessDefDTO> getProcessByPage(Map<String, Object> params) {
		ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery()
														.processDefinitionTenantId(String.valueOf(TenantContextHolder.getTenantId())).latestVersion();

		String category = MapUtil.getStr(params, "category");
		if (StrUtil.isNotBlank(category)) {
			query.processDefinitionCategory(category);
		}

		int page = MapUtil.getInt(params, PaginationConstants.CURRENT);
		int limit = MapUtil.getInt(params, PaginationConstants.SIZE);

		IPage<ProcessDefDTO> result = new Page<ProcessDefDTO>(page, limit);
		result.setTotal(query.count());

		List<ProcessDefDTO> deploymentList = query.listPage((page - 1) * limit, limit).stream().map(processDefinition -> {
			Deployment deployment = repositoryService.createDeploymentQuery()
													 .deploymentId(processDefinition.getDeploymentId())
													 .singleResult();
			return ProcessDefDTO.toProcessDefDTO(processDefinition, deployment);
		}).collect(Collectors.toList());
		result.setRecords(deploymentList);
		return result;
	}

	/**
	 * 读取xml/image资源
	 * @param procDefId
	 * @param proInsId
	 * @param resType
	 */
	@Override
	public InputStream readResource(String procDefId, String proInsId, String resType) {
		if (StrUtil.isBlank(procDefId)) {
			ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(proInsId).singleResult();
			procDefId = processInstance.getProcessDefinitionId();
		}
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(procDefId).singleResult();

		String resourceName = "";
		if (ResourceTypeEnum.IMAGE.getType().equals(resType)) {
			resourceName = processDefinition.getDiagramResourceName();
		}
		else if (ResourceTypeEnum.XML.getType().equals(resType)) {
			resourceName = processDefinition.getResourceName();
		}

		InputStream resourceAsStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(), resourceName);
		return resourceAsStream;
	}

	/**
	 * 更新状态
	 * @param status
	 * @param procDefId
	 */
	@Override
	public Boolean updateStatus(String status, String procDefId) {
		if (ProcessStatusEnum.ACTIVE.getStatus().equals(status)) {
			repositoryService.activateProcessDefinitionById(procDefId, true, null);
		}
		else if (ProcessStatusEnum.SUSPEND.getStatus().equals(status)) {
			repositoryService.suspendProcessDefinitionById(procDefId, true, null);
		}
		return Boolean.TRUE;
	}

	/**
	 * 删除部署的流程，级联删除流程实例
	 * @param deploymentId
	 */
	@Override
	public Boolean removeProcIns(String deploymentId) {
		repositoryService.deleteDeployment(deploymentId, true);
		return Boolean.TRUE;
	}

	/**
	 * 启动流程、更新请假单状态
	 * @param leaveId
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean saveStartProcess(String processKey, String businessKey, Callback callback) {
		runtimeService.startProcessInstanceByKeyAndTenantId(processKey, businessKey, String.valueOf(TenantContextHolder.getTenantId()));
		callback.apply();
		return Boolean.TRUE;
	}
}
