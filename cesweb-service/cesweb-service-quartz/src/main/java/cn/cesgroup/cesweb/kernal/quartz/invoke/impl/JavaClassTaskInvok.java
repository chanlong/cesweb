package cn.cesgroup.cesweb.kernal.quartz.invoke.impl;

import cn.cesgroup.cesweb.kernal.quartz.constants.QuartzEnum;
import cn.cesgroup.cesweb.kernal.quartz.entity.SysJob;
import cn.cesgroup.cesweb.kernal.quartz.exception.TaskException;
import cn.cesgroup.cesweb.kernal.quartz.invoke.ITaskInvok;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 定时任务java class反射实现
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Aug 19, 2020 10:52:42 AM
 * @version 1.0.2020
 */
@Slf4j
@Component("javaClassTaskInvok")
public class JavaClassTaskInvok implements ITaskInvok {

	@Override
	public void invokMethod(SysJob sysJob) throws TaskException {
		Object obj;
		Class<?> clazz;
		Method method;
		Object returnValue;
		try {
			if (StrUtil.isNotEmpty(sysJob.getMethodParamsValue())) {
				clazz = Class.forName(sysJob.getClassName());
				obj = clazz.newInstance();
				method = clazz.getDeclaredMethod(sysJob.getMethodName(), String.class);
				returnValue = method.invoke(obj, sysJob.getMethodParamsValue());
			}
			else {
				clazz = Class.forName(sysJob.getClassName());
				obj = clazz.newInstance();
				method = clazz.getDeclaredMethod(sysJob.getMethodName());
				returnValue = method.invoke(obj);
			}
			if (StrUtil.isEmpty(returnValue.toString()) || QuartzEnum.JOB_LOG_STATUS_FAIL.getType().equals(returnValue.toString())) {
				log.error("定时任务javaClassTaskInvok异常,执行任务：{}", sysJob.getClassName());
				throw new TaskException("定时任务javaClassTaskInvok业务执行失败,任务：" + sysJob.getClassName());
			}
		}
		catch (ClassNotFoundException e) {
			log.error("定时任务java反射类没有找到,执行任务：{}", sysJob.getClassName());
			throw new TaskException("定时任务java反射类没有找到,执行任务：" + sysJob.getClassName());
		}
		catch (IllegalAccessException e) {
			log.error("定时任务java反射类异常,执行任务：{}", sysJob.getClassName());
			throw new TaskException("定时任务java反射类异常,执行任务：" + sysJob.getClassName());
		}
		catch (InstantiationException e) {
			log.error("定时任务java反射类异常,执行任务：{}", sysJob.getClassName());
			throw new TaskException("定时任务java反射类异常,执行任务：" + sysJob.getClassName());
		}
		catch (NoSuchMethodException e) {
			log.error("定时任务java反射执行方法名异常,执行任务：{}", sysJob.getClassName());
			throw new TaskException("定时任务java反射执行方法名异常,执行任务：" + sysJob.getClassName());
		}
		catch (InvocationTargetException e) {
			log.error("定时任务java反射执行异常,执行任务：{}", sysJob.getClassName());
			throw new TaskException("定时任务java反射执行异常,执行任务：" + sysJob.getClassName());
		}

	}

}
