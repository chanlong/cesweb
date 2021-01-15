package cn.cesgroup.cesweb.kernal.quartz.event;

import org.quartz.Trigger;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;

import cn.cesgroup.cesweb.kernal.quartz.entity.SysJob;
import cn.cesgroup.cesweb.kernal.quartz.util.TaskInvokUtil;
import lombok.AllArgsConstructor;

/**
 * 异步监听定时任务事件
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Aug 19, 2020 10:47:39 AM
 * @version 1.0.2020
 */
@AllArgsConstructor
public class SysJobListener {

	private TaskInvokUtil taskInvokUtil;

	@Async
	@Order
	@EventListener(SysJobEvent.class)
	public void comSysJob(SysJobEvent event) {
		SysJob sysJob = event.getSysJob();
		Trigger trigger = event.getTrigger();
		taskInvokUtil.invokMethod(sysJob, trigger);
	}

}
