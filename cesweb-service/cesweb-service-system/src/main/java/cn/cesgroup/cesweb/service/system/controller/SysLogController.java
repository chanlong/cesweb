package cn.cesgroup.cesweb.service.system.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cn.cesgroup.cesweb.api.system.entity.SysLog;
import cn.cesgroup.cesweb.api.system.model.PreLogModel;
import cn.cesgroup.cesweb.common.core.util.Response;
import cn.cesgroup.cesweb.common.security.annotation.Inner;
import cn.cesgroup.cesweb.service.system.service.SysLogService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;

/**
 * 日志表 前端控制器
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 13, 2020 4:11:42 PM
 * @version 1.0.2020
 */
@RestController
@AllArgsConstructor
@RequestMapping("/log")
@Api(value = "log", tags = "日志管理模块")
public class SysLogController {

	private final SysLogService sysLogService;

	/**
	 * 简单分页查询
	 * @param page 分页对象
	 * @param sysLog 系统日志
	 * @return
	 */
	@GetMapping("/page")
	public Response<?> getLogPage(Page<SysLog> page, SysLog sysLog) {
		return Response.ok(sysLogService.page(page, Wrappers.query(sysLog)));
	}

	/**
	 * 删除日志
	 * @param id ID
	 * @return success/false
	 */
	@DeleteMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('sys_log_del')")
	public Response<?> removeById(@PathVariable String id) {
		return Response.ok(sysLogService.removeById(id));
	}

	/**
	 * 插入日志
	 * @param sysLog 日志实体
	 * @return success/false
	 */
	@Inner
	@PostMapping("/save")
	public Response<?> save(@Valid @RequestBody SysLog sysLog) {
		return Response.ok(sysLogService.save(sysLog));
	}

	/**
	 * 批量插入前端异常日志
	 * @param preLogVoList 日志实体
	 * @return success/false
	 */
	@PostMapping("/logs")
	public Response<?> saveBatchLogs(@RequestBody List<PreLogModel> preLogVoList) {
		return Response.ok(sysLogService.saveBatchLogs(preLogVoList));
	}

}
