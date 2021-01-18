package cn.cesgroup.cesweb.service.workflow.service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.metadata.IPage;

import cn.cesgroup.cesweb.service.workflow.dto.CommentDto;
import cn.cesgroup.cesweb.service.workflow.dto.LeaveBillDto;
import cn.cesgroup.cesweb.service.workflow.dto.TaskDTO;

/**
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Aug 21, 2020 10:30:28 AM
 * @version 1.0.2020
 */
public interface TaskService {

	/**
	 * 获取用户代办列表
	 * @param params
	 * @param name
	 * @return
	 */
	IPage<TaskDTO> getTaskByName(Map<String, Object> params, String name);

	/**
	 * 通过任务ID查询任务信息关联申请单信息
	 * @param id
	 */
	LeaveBillDto getTaskById(String id);

	/**
	 * 提交任务
	 * @param leaveBillDto
	 */
	Boolean submitTask(LeaveBillDto leaveBillDto);

	/**
	 * 通过任务ID 查询批注信息
	 * @param taskId 任务ID
	 */
	List<CommentDto> getCommentByTaskId(String taskId);

	/**
	 * 追踪图片节点
	 * @param id
	 */
	InputStream viewByTaskId(String id);
}
