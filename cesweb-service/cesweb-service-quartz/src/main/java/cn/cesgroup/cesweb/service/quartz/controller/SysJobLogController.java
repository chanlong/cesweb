package cn.cesgroup.cesweb.service.quartz.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cn.cesgroup.cesweb.common.core.util.Response;
import cn.cesgroup.cesweb.service.quartz.entity.SysJobLog;
import cn.cesgroup.cesweb.service.quartz.service.SysJobLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

/**
 * 定时任务执行日志表
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Aug 19, 2020 10:43:35 AM
 * @version 1.0.2020
 */
@RestController
@AllArgsConstructor
@RequestMapping("/sys-job-log")
@Api(value = "sys-job-log", tags = "定时任务日志")
public class SysJobLogController {

	private final SysJobLogService sysJobLogService;

	/**
	 * 分页查询
	 * @param page 分页对象
	 * @param sysJobLog 定时任务执行日志表
	 * @return
	 */
	@GetMapping("/page")
	@ApiOperation(value = "分页定时任务日志查询")
	public Response<?> getSysJobLogPage(Page<SysJobLog> page, SysJobLog sysJobLog) {
		return Response.ok(sysJobLogService.page(page, Wrappers.query(sysJobLog)));
	}

}
