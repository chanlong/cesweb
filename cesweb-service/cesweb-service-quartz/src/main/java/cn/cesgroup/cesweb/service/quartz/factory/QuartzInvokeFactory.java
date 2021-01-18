package cn.cesgroup.cesweb.service.quartz.factory;

import org.aspectj.lang.annotation.Aspect;
import org.quartz.Trigger;
import org.springframework.context.ApplicationEventPublisher;

import cn.cesgroup.cesweb.service.quartz.entity.SysJob;
import cn.cesgroup.cesweb.service.quartz.event.SysJobEvent;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

/**
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Aug 19, 2020 10:14:20 AM
 * @version 1.0.2020
 */
@Aspect
@AllArgsConstructor
public class QuartzInvokeFactory {

	private final ApplicationEventPublisher publisher;

	@SneakyThrows
	void init(SysJob sysJob, Trigger trigger) {
		publisher.publishEvent(new SysJobEvent(sysJob, trigger));
	}

}
