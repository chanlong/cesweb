/**
 * <p>Copyright:Copyright(c) 2020</p>
 * <p>Company:Professional</p>
 * <p>Package:cn.cesgroup.cesweb.service.workflow.util</p>
 * <p>File:BpmnConverterUtil.java</p>
 * <p>类更新历史信息</p>
 * @todo chanlong(陈龙) 创建于 Sep 7, 2020 3:45:04 PM
 */
package cn.cesgroup.cesweb.service.workflow.util;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.converter.CallActivityXMLConverter;
import org.activiti.bpmn.converter.UserTaskXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Sep 7, 2020 3:45:04 PM
 * @version 1.0.2020
 */
public abstract class BpmnConverterUtil {

	/**
	 * 将JsonNode格式的流程图转为标准的xml.
	 * 
	 * @param jsonStr
	 * @author chanlong(陈龙)
	 * @date Sep 7, 2020 4:25:08 PM
	 */
	public static String converterJsonToXml(String jsonStr) {
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode = null;
		try {
			jsonNode = objectMapper.readTree(jsonStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
		BpmnJsonConverter bpmnJsonConverter = new BpmnJsonConverter();
		BpmnModel bpmnModel = bpmnJsonConverter.convertToBpmnModel(jsonNode);
		// 如果没有Processes,认为是一个空流程
		if (bpmnModel.getProcesses().isEmpty()) {
			return "";
		}
		BpmnXMLConverter bpmnXMLConverter = new BpmnXMLConverter();
		byte[] bytes = bpmnXMLConverter.convertToXML(bpmnModel);
		return new String(bytes);
	}

	/**
	 * 将xml转为jsonnode.
	 * 
	 * @param xml
	 * @author chanlong(陈龙)
	 * @date Sep 7, 2020 4:25:53 PM
	 */
	public static JsonNode converterXmlToJson(String xml) {
		// 创建转换对象
		BpmnXMLConverter bpmnXMLConverter = new BpmnXMLConverter();
		// XMLStreamReader读取XML资源
		XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
		StringReader stringReader = new StringReader(xml);
		XMLStreamReader xmlStreamReader = null;
		try {
			xmlStreamReader = xmlInputFactory.createXMLStreamReader(stringReader);
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
		BpmnXMLConverter.addConverter(new UserTaskXMLConverter());
		BpmnXMLConverter.addConverter(new CallActivityXMLConverter());
		// 把xml转换成BpmnModel对象
		BpmnModel bpmnModel = bpmnXMLConverter.convertToBpmnModel(xmlStreamReader);
		// 创建转换对象
		BpmnJsonConverter bpmnJsonConverter = new BpmnJsonConverter();
		// 把BpmnModel对象转换成json
		JsonNode jsonNodes = bpmnJsonConverter.convertToJson(bpmnModel);
		return jsonNodes;
	}
}
