package cn.cesgroup.cesweb.kernal.quartz.config;

import org.springframework.boot.autoconfigure.quartz.SchedulerFactoryBeanCustomizer;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Aug 19, 2020 10:13:23 AM
 * @version 1.0.2020
 */
@Configuration
public class QuartzCustomizerConfig implements SchedulerFactoryBeanCustomizer {

	@Override
	public void customize(SchedulerFactoryBean schedulerFactoryBean) {
		schedulerFactoryBean.setWaitForJobsToCompleteOnShutdown(true);
	}

}
