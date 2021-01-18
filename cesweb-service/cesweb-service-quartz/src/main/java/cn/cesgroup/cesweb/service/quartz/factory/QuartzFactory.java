package cn.cesgroup.cesweb.service.quartz.factory;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;

import cn.cesgroup.cesweb.service.quartz.constants.QuartzEnum;
import cn.cesgroup.cesweb.service.quartz.entity.SysJob;
import lombok.SneakyThrows;

/**
 * 动态任务工厂
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Aug 19, 2020 10:13:44 AM
 * @version 1.0.2020
 */
@DisallowConcurrentExecution
public class QuartzFactory implements Job {

	@Autowired
	private QuartzInvokeFactory quartzInvokeFactory;

	@Override
	@SneakyThrows
	public void execute(JobExecutionContext jobExecutionContext) {
		SysJob sysJob = (SysJob) jobExecutionContext.getMergedJobDataMap().get(QuartzEnum.SCHEDULE_JOB_KEY.getType());
		quartzInvokeFactory.init(sysJob, jobExecutionContext.getTrigger());
	}

}
