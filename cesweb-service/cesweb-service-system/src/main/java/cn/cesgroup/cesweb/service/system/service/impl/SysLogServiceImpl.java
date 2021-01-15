package cn.cesgroup.cesweb.service.system.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cn.cesgroup.cesweb.api.system.entity.SysLog;
import cn.cesgroup.cesweb.api.system.model.PreLogModel;
import cn.cesgroup.cesweb.common.core.constant.CommonConstants;
import cn.cesgroup.cesweb.service.system.mapper.SysLogMapper;
import cn.cesgroup.cesweb.service.system.service.SysLogService;

/**
 * 日志表 服务实现类
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 13, 2020 3:36:35 PM
 * @version 1.0.2020
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements SysLogService {

	/**
	 * 批量插入前端错误日志
	 * @param preLogVoList 日志信息
	 * @return true/false
	 */
	@Override
	public Boolean saveBatchLogs(List<PreLogModel> preLogModelList) {
		List<SysLog> sysLogs = preLogModelList.stream().map(pre -> {
			SysLog log = new SysLog();
			log.setType(CommonConstants.STATUS_LOCK);
			log.setTitle(pre.getInfo());
			log.setException(pre.getStack());
			log.setParams(pre.getMessage());
			log.setCreateTime(LocalDateTime.now());
			log.setRequestUri(pre.getUrl());
			log.setCreateBy(pre.getUser());
			return log;
		}).collect(Collectors.toList());
		return this.saveBatch(sysLogs);
	}

}
