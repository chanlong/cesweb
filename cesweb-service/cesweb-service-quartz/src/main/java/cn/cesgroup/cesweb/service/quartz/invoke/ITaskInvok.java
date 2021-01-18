package cn.cesgroup.cesweb.service.quartz.invoke;

import cn.cesgroup.cesweb.service.quartz.entity.SysJob;
import cn.cesgroup.cesweb.service.quartz.exception.TaskException;

/**
 * 定时任务反射实现接口类
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Aug 19, 2020 10:52:15 AM
 * @version 1.0.2020
 */
public interface ITaskInvok {

	/**
	 * 执行反射方法
	 * @param sysJob 配置类
	 * @throws TaskException
	 */
	void invokMethod(SysJob sysJob) throws TaskException;

}
