package cn.cesgroup.cesweb.kernal.quartz.invoke;

import cn.cesgroup.cesweb.common.core.util.SpringContextHolder;
import cn.cesgroup.cesweb.kernal.quartz.constants.JobTypeEnum;
import cn.cesgroup.cesweb.kernal.quartz.exception.TaskException;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * TaskInvok工厂类
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Aug 19, 2020 10:54:46 AM
 * @version 1.0.2020
 */
@Slf4j
public class TaskInvokFactory {

	/**
	 * 根据对应jobType获取对应 invoker
	 * @param jobType
	 * @throws TaskException
	 */
	public static ITaskInvok getInvoker(String jobType) throws TaskException {
		if (StrUtil.isBlank(jobType)) {
			log.info("获取TaskInvok传递参数有误，jobType:{}", jobType);
			throw new TaskException("");
		}

		ITaskInvok iTaskInvok = null;
		if (JobTypeEnum.JAVA.getType().equals(jobType)) {
			iTaskInvok = SpringContextHolder.getBean("javaClassTaskInvok");
		}
		else if (JobTypeEnum.SPRING_BEAN.getType().equals(jobType)) {
			iTaskInvok = SpringContextHolder.getBean("springBeanTaskInvok");
		}
		else if (JobTypeEnum.REST.getType().equals(jobType)) {
			iTaskInvok = SpringContextHolder.getBean("restTaskInvok");
		}
		else if (JobTypeEnum.JAR.getType().equals(jobType)) {
			iTaskInvok = SpringContextHolder.getBean("jarTaskInvok");
		}
		else if (StrUtil.isBlank(jobType)) {
			log.info("定时任务类型无对应反射方式，反射类型:{}", jobType);
			throw new TaskException("");
		}

		return iTaskInvok;
	}

}
