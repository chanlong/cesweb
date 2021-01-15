package cn.cesgroup.cesweb.common.log.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.ApplicationEventPublisher;

import cn.cesgroup.cesweb.common.log.annotation.SysLog;
import cn.cesgroup.cesweb.common.log.event.SysLogEvent;
import cn.cesgroup.cesweb.common.log.util.SysLogUtils;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * 操作日志使用spring event异步入库
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 10, 2020 4:12:41 PM
 * @version 1.0.2020
 */
@Slf4j
@Aspect
@AllArgsConstructor
public class SysLogAspect {

	private final ApplicationEventPublisher publisher;

	@SneakyThrows
	@Around("@annotation(sysLog)")
	public Object around(ProceedingJoinPoint point, SysLog sysLog) {
		String strClassName = point.getTarget().getClass().getName();
		String strMethodName = point.getSignature().getName();
		log.debug("[类名]:{},[方法]:{}", strClassName, strMethodName);

		cn.cesgroup.cesweb.api.system.entity.SysLog logVo = SysLogUtils.getSysLog();
		logVo.setTitle(sysLog.value());
		// 发送异步日志事件
		Long startTime = System.currentTimeMillis();
		Object obj = point.proceed();
		Long endTime = System.currentTimeMillis();
		logVo.setTime(endTime - startTime);
		publisher.publishEvent(new SysLogEvent(logVo));
		return obj;
	}

}
