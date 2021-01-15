package cn.cesgroup.cesweb.common.log.event;

import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;

import cn.cesgroup.cesweb.api.system.entity.SysLog;
import cn.cesgroup.cesweb.api.system.feign.RemoteLogService;
import cn.cesgroup.cesweb.common.core.constant.SecurityConstants;
import lombok.AllArgsConstructor;

/**
 * 异步监听日志事件
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 10, 2020 4:13:46 PM
 * @version 1.0.2020
 */
@AllArgsConstructor
public class SysLogListener {

	private final RemoteLogService remoteLogService;

	@Async
	@Order
	@EventListener(SysLogEvent.class)
	public void saveSysLog(SysLogEvent event) {
		SysLog sysLog = event.getSysLog();
		remoteLogService.saveLog(sysLog, SecurityConstants.FROM_IN);
	}

}
