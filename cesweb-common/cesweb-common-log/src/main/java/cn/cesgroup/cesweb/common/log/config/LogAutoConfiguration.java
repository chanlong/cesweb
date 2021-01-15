package cn.cesgroup.cesweb.common.log.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import cn.cesgroup.cesweb.api.system.feign.RemoteLogService;
import cn.cesgroup.cesweb.common.log.aspect.SysLogAspect;
import cn.cesgroup.cesweb.common.log.event.SysLogListener;
import lombok.AllArgsConstructor;

/**
 * 日志自动配置
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 10, 2020 4:13:15 PM
 * @version 1.0.2020
 */
@EnableAsync
@Configuration
@AllArgsConstructor
@ConditionalOnWebApplication
public class LogAutoConfiguration {

	private final RemoteLogService remoteLogService;

	@Bean
	public SysLogListener sysLogListener() {
		return new SysLogListener(remoteLogService);
	}

	@Bean
	public SysLogAspect sysLogAspect(ApplicationEventPublisher publisher) {
		return new SysLogAspect(publisher);
	}

}
