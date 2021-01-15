package cn.cesgroup.cesweb.common.log.event;

import cn.cesgroup.cesweb.api.system.entity.SysLog;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 系统日志事件
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 10, 2020 4:13:32 PM
 * @version 1.0.2020
 */
@Getter
@AllArgsConstructor
public class SysLogEvent {

	private final SysLog sysLog;

}
