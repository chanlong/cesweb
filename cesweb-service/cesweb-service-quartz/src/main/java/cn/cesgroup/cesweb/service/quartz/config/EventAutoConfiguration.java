package cn.cesgroup.cesweb.service.quartz.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import cn.cesgroup.cesweb.service.quartz.event.SysJobListener;
import cn.cesgroup.cesweb.service.quartz.event.SysJobLogListener;
import cn.cesgroup.cesweb.service.quartz.factory.QuartzInvokeFactory;
import cn.cesgroup.cesweb.service.quartz.service.SysJobLogService;
import cn.cesgroup.cesweb.service.quartz.util.TaskInvokUtil;

/**
 * 多线程自动配置
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Aug 19, 2020 10:44:51 AM
 * @version 1.0.2020
 */
@EnableAsync
@Configuration
@ConditionalOnWebApplication
public class EventAutoConfiguration {

	@Autowired
	private TaskInvokUtil taskInvokUtil;

	@Autowired
	private SysJobLogService sysJobLogService;

	@Bean
	public SysJobListener sysJobListener() {
		return new SysJobListener(taskInvokUtil);
	}

	@Bean
	public QuartzInvokeFactory quartzInvokeFactory(ApplicationEventPublisher publisher) {
		return new QuartzInvokeFactory(publisher);
	}

	@Bean
	public SysJobLogListener sysJobLogListener() {
		return new SysJobLogListener(sysJobLogService);
	}

	@Bean
	public TaskInvokUtil taskInvokUtil(ApplicationEventPublisher publisher) {
		return new TaskInvokUtil(publisher);
	}

}
