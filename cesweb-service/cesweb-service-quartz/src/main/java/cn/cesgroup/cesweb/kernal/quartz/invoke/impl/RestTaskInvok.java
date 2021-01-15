package cn.cesgroup.cesweb.kernal.quartz.invoke.impl;

import org.springframework.stereotype.Component;

import cn.cesgroup.cesweb.common.core.constant.CommonConstants;
import cn.cesgroup.cesweb.kernal.quartz.entity.SysJob;
import cn.cesgroup.cesweb.kernal.quartz.exception.TaskException;
import cn.cesgroup.cesweb.kernal.quartz.invoke.ITaskInvok;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 定时任务rest反射实现
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Aug 19, 2020 10:53:18 AM
 * @version 1.0.2020
 */
@Slf4j
@AllArgsConstructor
@Component("restTaskInvok")
public class RestTaskInvok implements ITaskInvok {

	@Override
	public void invokMethod(SysJob sysJob) throws TaskException {
		try {
			HttpRequest request = HttpUtil.createGet(sysJob.getExecutePath());
			if (sysJob.getTenantId() != null) {
				request.header(CommonConstants.TENANT_ID, sysJob.getTenantId().toString());
			}
			request.execute();
		}
		catch (Exception e) {
			log.error("定时任务restTaskInvok异常,执行任务：{}", sysJob.getExecutePath());
			throw new TaskException("定时任务restTaskInvok业务执行失败,任务：" + sysJob.getExecutePath());
		}
	}

}
