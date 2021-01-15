package cn.cesgroup.cesweb.kernal.quartz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import cn.cesgroup.cesweb.kernal.quartz.entity.SysJobLog;

import org.apache.ibatis.annotations.Mapper;

/**
 * 定时任务执行日志表
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Aug 19, 2020 10:49:07 AM
 * @version 1.0.2020
 */
@Mapper
public interface SysJobLogMapper extends BaseMapper<SysJobLog> {

}
