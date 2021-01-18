package cn.cesgroup.cesweb.service.workflow.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.ActivitiException;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ModelQuery;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import cn.cesgroup.cesweb.common.core.constant.PaginationConstants;
import cn.cesgroup.cesweb.common.core.constant.SecurityConstants;
import cn.cesgroup.cesweb.common.data.tenant.TenantContextHolder;
import cn.cesgroup.cesweb.service.workflow.model.form.ModelForm;
import cn.cesgroup.cesweb.service.workflow.service.ModelService;
import cn.cesgroup.cesweb.service.workflow.util.BpmnConverterUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 流程模型
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Aug 21, 2020 10:01:18 AM
 * @version 1.0.2020
 */
@Slf4j
@Service
@AllArgsConstructor
public class ModelServiceImpl implements ModelService {

	private static final String BPMN20_XML = ".bpmn20.xml";

	private final RepositoryService repositoryService;
	
	private final ObjectMapper objectMapper;

	/**
	 * 创建流程
	 * @param name
	 * @param key
	 * @param desc
	 * @param category
	 */
	@Override
	public Model create(String name, String key, String desc, String category) {
		try {
			ObjectNode editorNode = objectMapper.createObjectNode();
			editorNode.put("id", "canvas");
			editorNode.put("resourceId", "canvas");
			ObjectNode properties = objectMapper.createObjectNode();
			properties.put("process_author", SecurityConstants.CES_LICENSE);
			properties.put("process_id", key);
			properties.put("name", name);
			editorNode.set("properties", properties);
			ObjectNode stencilset = objectMapper.createObjectNode();
			stencilset.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");
			editorNode.set("stencilset", stencilset);

			Model model = repositoryService.newModel();
			model.setKey(key);
			model.setName(name);
			model.setCategory(category);
			model.setVersion(Integer.parseInt(String.valueOf(repositoryService.createModelQuery().modelKey(model.getKey()).count() + 1)));

			ObjectNode modelObjectNode = objectMapper.createObjectNode();
			modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, name);
			modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, model.getVersion());
			modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, desc);
			model.setMetaInfo(modelObjectNode.toString());
			model.setTenantId(String.valueOf(TenantContextHolder.getTenantId()));

			repositoryService.saveModel(model);
			repositoryService.addModelEditorSource(model.getId(), editorNode.toString().getBytes("utf-8"));
			return model;
		}
		catch (UnsupportedEncodingException e) {
			log.error("UnsupportedEncodingException", e);
		}
		return null;
	}
	
	@Override
	public void saveModel(String id, ModelForm form) {
		JsonNode jsonNode = BpmnConverterUtil.converterXmlToJson(form.getModelXml());
		String jsonXml = jsonNode.toPrettyString();
		System.out.println(jsonXml);
		this.saveModel(id, form.getName(), form.getDesc(), jsonXml, form.getModelSvg());
	}
	
	/**
	 * 保存model信息
	 * @param id
	 * @param name
	 * @param description
	 * @param jsonXml
	 * @param svgXml
	 */
	@Override
	public void saveModel(String id, String name, String description, String jsonXml, String svgXml) {
		try {
			Model model = repositoryService.getModel(id);
			ObjectNode modelJson = (ObjectNode) objectMapper.readTree(model.getMetaInfo());
			modelJson.put(ModelDataJsonConstants.MODEL_NAME, name);
			modelJson.put(ModelDataJsonConstants.MODEL_DESCRIPTION, description);
			model.setMetaInfo(modelJson.toString());
			model.setName(name);
			model.setTenantId(String.valueOf(TenantContextHolder.getTenantId()));

			repositoryService.saveModel(model);
			repositoryService.addModelEditorSource(model.getId(), jsonXml.getBytes(StandardCharsets.UTF_8));
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			final byte[] result = outStream.toByteArray();
			repositoryService.addModelEditorSourceExtra(model.getId(), result);
			outStream.close();
		} catch (Exception e) {
			log.error("Error saving model", e);
			throw new ActivitiException("Error saving model", e);
		}
	}

	/**
	 * 获取流程模型
	 * @see cn.cesgroup.cesweb.service.workflow.service.ModelService#getModel(java.lang.String)
	 * @author chanlong(陈龙)
	 * @date Sep 3, 2020 10:25:10 AM
	 */
	@Override
	public Model getModel(final String id) {
		return repositoryService.createModelQuery()
								.modelId(id)
								.modelTenantId(TenantContextHolder.getTenantId())
								.latestVersion()
								.singleResult();
	}
	
	/**
	 * 根据modelId获取model
	 * @param modelId
	 */
	@Override
	public Object getEditorJson(final String modelId) {
		byte[] source = repositoryService.getModelEditorSource(modelId);
		return BpmnConverterUtil.converterJsonToXml(new String(source, StandardCharsets.UTF_8));
	}
	
	/**
	 * 分页获取流程
	 * @param params
	 */
	@Override
	public IPage<Model> getModelPage(Map<String, Object> params) {
		ModelQuery modelQuery = repositoryService.createModelQuery()
												 .modelTenantId(TenantContextHolder.getTenantId())
												 .latestVersion()
												 .orderByLastUpdateTime()
												 .desc();
		
		String category = (String) params.get("category");
		if (StrUtil.isNotBlank(category)) {
			modelQuery.modelCategory(category);
		}

		int page = MapUtil.getInt(params, PaginationConstants.CURRENT);
		int limit = MapUtil.getInt(params, PaginationConstants.SIZE);

		IPage<Model> result = new Page<Model>(page, limit);
		result.setTotal(modelQuery.count());
		result.setRecords(modelQuery.listPage((page - 1) * limit, limit));
		return result;
	}

	/**
	 * 删除流程
	 * @param id
	 */
	@Override
	public Boolean removeModelById(String id) {
		repositoryService.deleteModel(id);
		return Boolean.TRUE;
	}

	/**
	 * 部署流程
	 * @param id
	 */
	@Override
	public Boolean deploy(String id) {
		try {
			// 获取模型
			Model model = repositoryService.getModel(id);
			ObjectNode objectNode = (ObjectNode) new ObjectMapper().readTree(repositoryService.getModelEditorSource(model.getId()));
			BpmnModel bpmnModel = new BpmnJsonConverter().convertToBpmnModel(objectNode);

			String processName = model.getName();
			if (!StrUtil.endWithIgnoreCase(processName, BPMN20_XML)) {
				processName += BPMN20_XML;
			}
			// 部署流程
			Deployment deployment = repositoryService.createDeployment()
													 .name(model.getName())
													 .addBpmnModel(processName, bpmnModel)
													 .tenantId(String.valueOf(TenantContextHolder.getTenantId()))
													 .deploy();

			// 设置流程分类
			List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).list();
			list.stream().forEach(processDefinition -> repositoryService.setProcessDefinitionCategory(processDefinition.getId(), model.getCategory()));
		}
		catch (Exception e) {
			log.error("部署失败，异常", e);
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}
}
