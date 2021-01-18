package cn.cesgroup.cesweb.service.workflow.service;

import java.util.Map;

import org.activiti.engine.repository.Model;

import com.baomidou.mybatisplus.core.metadata.IPage;

import cn.cesgroup.cesweb.service.workflow.model.form.ModelForm;

/**
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Aug 21, 2020 10:35:39 AM
 * @version 1.0.2020
 */
public interface ModelService {

	/**
	 * 创建流程
	 * @param name
	 * @param key
	 * @param desc
	 * @param category
	 */
	Model create(String name, String key, String desc, String category);

	/**
	 * 保存model信息.
	 * @param id
	 * @param form
	 * @author chanlong(陈龙)
	 * @date Sep 7, 2020 3:29:50 PM
	 */
	void saveModel(String id, ModelForm form);
	
	/**
	 * 保存model信息
	 * @param modelId
	 * @param name
	 * @param description
	 * @param jsonXml
	 * @param svgXml
	 */
	void saveModel(String id, String name, String description, String jsonXml, String svgXml);
	
	/**
	 * 获取流程模型.
	 * @param id
	 * @author chanlong(陈龙)
	 * @date Sep 3, 2020 10:24:21 AM
	 */
	Model getModel(String id);
	
	/**
	 * 分页获取流程
	 * @param params
	 */
	IPage<Model> getModelPage(Map<String, Object> params);

	/**
	 * 删除流程
	 * @param id
	 */
	Boolean removeModelById(String id);

	/**
	 * 部署流程
	 * @param id
	 */
	Boolean deploy(String id);

	/**
	 * 根据modelId获取model
	 * @param modelId
	 */
	Object getEditorJson(String modelId);

}
