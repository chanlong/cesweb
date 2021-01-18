package cn.cesgroup.cesweb.service.workflow.service;

/**
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Aug 21, 2020 10:35:04 AM
 * @version 1.0.2020
 */
public interface EditorService {

	/**
	 * 获取设计器页面的json
	 */
	Object getStencilset();

	/**
	 * 根据modelId获取model
	 * @param modelId
	 */
	Object getEditorJson(String modelId);

	/**
	 * 保存model信息
	 * @param modelId
	 * @param name
	 * @param description
	 * @param jsonXml
	 * @param svgXml
	 */
	void saveModel(String modelId, String name, String description, String jsonXml, String svgXml);

}
