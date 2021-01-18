package cn.cesgroup.cesweb.service.quartz.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cn.cesgroup.cesweb.service.quartz.entity.SysJobLog;
import cn.cesgroup.cesweb.service.quartz.mapper.SysJobLogMapper;
import cn.cesgroup.cesweb.service.quartz.service.SysJobLogService;
import lombok.AllArgsConstructor;

/**
 * 定时任务执行日志表
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Aug 19, 2020 10:50:09 AM
 * @version 1.0.2020
 */
@Service
@AllArgsConstructor
public class SysJobLogServiceImpl extends ServiceImpl<SysJobLogMapper, SysJobLog> implements SysJobLogService {

}
