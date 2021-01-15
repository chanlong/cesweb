package cn.cesgroup.cesweb.kernal.quartz.event;

import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;

import cn.cesgroup.cesweb.kernal.quartz.entity.SysJobLog;
import cn.cesgroup.cesweb.kernal.quartz.service.SysJobLogService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 异步监听定时任务日志事件
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Aug 19, 2020 10:48:07 AM
 * @version 1.0.2020
 */
@Slf4j
@AllArgsConstructor
public class SysJobLogListener {

	private SysJobLogService sysJobLogService;

	@Async
	@Order
	@EventListener(SysJobLogEvent.class)
	public void saveSysJobLog(SysJobLogEvent event) {
		SysJobLog sysJobLog = event.getSysJobLog();
		sysJobLogService.save(sysJobLog);
		log.info("执行定时任务日志");
	}

}
