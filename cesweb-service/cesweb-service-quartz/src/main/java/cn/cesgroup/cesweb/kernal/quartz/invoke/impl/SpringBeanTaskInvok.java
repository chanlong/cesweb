package cn.cesgroup.cesweb.kernal.quartz.invoke.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import cn.cesgroup.cesweb.common.core.util.SpringContextHolder;
import cn.cesgroup.cesweb.kernal.quartz.constants.QuartzEnum;
import cn.cesgroup.cesweb.kernal.quartz.entity.SysJob;
import cn.cesgroup.cesweb.kernal.quartz.exception.TaskException;
import cn.cesgroup.cesweb.kernal.quartz.invoke.ITaskInvok;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 定时任务spring bean反射实现
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Aug 19, 2020 10:53:31 AM
 * @version 1.0.2020
 */
@Slf4j
@Component("springBeanTaskInvok")
public class SpringBeanTaskInvok implements ITaskInvok {

	@Override
	public void invokMethod(SysJob sysJob) throws TaskException {
		Object target;
		Method method;
		Object returnValue;
		// 通过Spring上下文去找 也有可能找不到
		target = SpringContextHolder.getBean(sysJob.getClassName());
		try {
			if (StrUtil.isNotEmpty(sysJob.getMethodParamsValue())) {
				method = target.getClass().getDeclaredMethod(sysJob.getMethodName(), String.class);
				ReflectionUtils.makeAccessible(method);
				returnValue = method.invoke(target, sysJob.getMethodParamsValue());
			}
			else {
				method = target.getClass().getDeclaredMethod(sysJob.getMethodName());
				ReflectionUtils.makeAccessible(method);
				returnValue = method.invoke(target);
			}
			if (StrUtil.isEmpty(returnValue.toString())
					|| QuartzEnum.JOB_LOG_STATUS_FAIL.getType().equals(returnValue.toString())) {
				log.error("定时任务springBeanTaskInvok异常,执行任务：{}", sysJob.getClassName());
				throw new TaskException("定时任务springBeanTaskInvok业务执行失败,任务：" + sysJob.getClassName());
			}
		}
		catch (NoSuchMethodException e) {
			log.error("定时任务spring bean反射异常方法未找到,执行任务：{}", sysJob.getClassName());
			throw new TaskException("定时任务spring bean反射异常方法未找到,执行任务：" + sysJob.getClassName());
		}
		catch (IllegalAccessException e) {
			log.error("定时任务spring bean反射异常,执行任务：{}", sysJob.getClassName());
			throw new TaskException("定时任务spring bean反射异常,执行任务：" + sysJob.getClassName());
		}
		catch (InvocationTargetException e) {
			log.error("定时任务spring bean反射执行异常,执行任务：{}", sysJob.getClassName());
			throw new TaskException("定时任务spring bean反射执行异常,执行任务：" + sysJob.getClassName());
		}
	}

}
