package cn.cesgroup.cesweb.service.workflow.listener;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import cn.cesgroup.cesweb.api.system.entity.SysUser;
import cn.cesgroup.cesweb.api.system.feign.RemoteUserService;
import cn.cesgroup.cesweb.common.core.util.Response;
import cn.cesgroup.cesweb.common.core.util.SpringContextHolder;
import cn.cesgroup.cesweb.common.data.tenant.TenantContextHolder;
import cn.cesgroup.cesweb.common.security.util.SecurityUtils;
import cn.hutool.core.collection.CollUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 请假流程监听器
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Aug 21, 2020 11:12:31 AM
 * @version 1.0.2020
 */
@Slf4j
public class LeaveProcessTaskListener implements TaskListener {

	/** serialVersionUID(long):. */
	private static final long serialVersionUID = 1L;

	/**
	 * 查询提交人的上级
	 * @param delegateTask
	 */
	@Override
	public void notify(DelegateTask delegateTask) {
		SimpMessagingTemplate simpMessagingTemplate = SpringContextHolder.getBean(SimpMessagingTemplate.class);
		RemoteUserService userService = SpringContextHolder.getBean(RemoteUserService.class);

		Response<List<SysUser>> result = userService.ancestorUsers(SecurityUtils.getUser().getUsername());
		List<String> remindUserList = new ArrayList<>();

		if (CollUtil.isEmpty(result.getData())) {
			log.info("用户 {} 不存在上级,任务单由当前用户审批", SecurityUtils.getUser().getUsername());
			delegateTask.addCandidateUser(SecurityUtils.getUser().getUsername());
			remindUserList.add(SecurityUtils.getUser().getUsername());
		} else {
			List<String> userList = result.getData().stream().map(SysUser::getUsername).collect(Collectors.toList());
			log.info("当前任务 {}，由 {}处理", delegateTask.getId(), userList);
			delegateTask.addCandidateUsers(userList);
			remindUserList.addAll(userList);
		}

		remindUserList.forEach(user -> {
			String target = String.format("%s-%s", SecurityUtils.getUser().getUsername(), TenantContextHolder.getTenantId());
			simpMessagingTemplate.convertAndSendToUser(target, "/remind", delegateTask.getName());
		});
	}

}
