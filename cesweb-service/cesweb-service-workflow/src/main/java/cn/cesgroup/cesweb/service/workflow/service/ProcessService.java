package cn.cesgroup.cesweb.service.workflow.service;

import java.io.InputStream;
import java.util.Map;

import com.baomidou.mybatisplus.core.metadata.IPage;

import cn.cesgroup.cesweb.service.workflow.dto.ProcessDefDTO;

/**
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Aug 21, 2020 10:25:04 AM
 * @version 1.0.2020
 */
public interface ProcessService {

	/**
	 * 分页流程列表
	 * @param params
	 */
	IPage<ProcessDefDTO> getProcessByPage(Map<String, Object> params);

	/**
	 * 读取xml/image资源
	 * @param procDefId
	 * @param proInsId
	 * @param resType
	 */
	InputStream readResource(String procDefId, String proInsId, String resType);

	/**
	 * 更新状态
	 * @param status
	 * @param procDefId
	 */
	Boolean updateStatus(String status, String procDefId);

	/**
	 * 删除流程实例
	 * @param deploymentId
	 */
	Boolean removeProcIns(String deploymentId);

	/**
	 * 启动流程、更新请假单状态
	 * @param leaveId
	 * @return
	 */
	Boolean saveStartProcess(String processKey, String businessKey, Callback callback);

	/**
	 * 回调函数
	 * <p>描述: </p>
	 * <p>Company: Professional</p>
	 * @author chanlong(陈龙)
	 * @date Aug 21, 2020 10:25:28 AM
	 * @version 1.0.2020
	 */
	@FunctionalInterface
	interface Callback {
		void apply();
	}
}
