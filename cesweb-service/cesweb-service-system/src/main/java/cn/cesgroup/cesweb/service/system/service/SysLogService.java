package cn.cesgroup.cesweb.service.system.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

import cn.cesgroup.cesweb.api.system.entity.SysLog;
import cn.cesgroup.cesweb.api.system.model.PreLogModel;

/**
 * 日志表 服务类
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 13, 2020 1:38:00 PM
 * @version 1.0.2020
 */
public interface SysLogService extends IService<SysLog> {

	/**
	 * 批量插入前端错误日志
	 * @param preLogVoList 日志信息
	 * @return true/false
	 */
	Boolean saveBatchLogs(List<PreLogModel> preLogVoList);

}
