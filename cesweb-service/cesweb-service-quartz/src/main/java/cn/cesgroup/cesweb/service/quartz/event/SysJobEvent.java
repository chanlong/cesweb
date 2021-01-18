package cn.cesgroup.cesweb.service.quartz.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.quartz.Trigger;

import cn.cesgroup.cesweb.service.quartz.entity.SysJob;

/**
 * 定时任务多线程事件
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Aug 19, 2020 10:47:28 AM
 * @version 1.0.2020
 */
@Getter
@AllArgsConstructor
public class SysJobEvent {

	private final SysJob sysJob;

	private final Trigger trigger;

}
