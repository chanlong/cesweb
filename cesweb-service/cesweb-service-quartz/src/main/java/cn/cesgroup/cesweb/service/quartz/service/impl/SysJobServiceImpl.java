package cn.cesgroup.cesweb.service.quartz.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cn.cesgroup.cesweb.service.quartz.entity.SysJob;
import cn.cesgroup.cesweb.service.quartz.mapper.SysJobMapper;
import cn.cesgroup.cesweb.service.quartz.service.SysJobService;
import lombok.AllArgsConstructor;

/**
 * 定时任务调度表
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Aug 19, 2020 10:50:33 AM
 * @version 1.0.2020
 */
@Service
@AllArgsConstructor
public class SysJobServiceImpl extends ServiceImpl<SysJobMapper, SysJob> implements SysJobService {

}
