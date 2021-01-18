package cn.cesgroup.cesweb.service.quartz.controller;

import static cn.cesgroup.cesweb.service.quartz.constants.QuartzEnum.JOB_LOG_STATUS_FAIL;
import static cn.cesgroup.cesweb.service.quartz.constants.QuartzEnum.JOB_STATUS_DEL;
import static cn.cesgroup.cesweb.service.quartz.constants.QuartzEnum.JOB_STATUS_NOT_RUNNING;
import static cn.cesgroup.cesweb.service.quartz.constants.QuartzEnum.JOB_STATUS_RELEASE;
import static cn.cesgroup.cesweb.service.quartz.constants.QuartzEnum.JOB_STATUS_RUNNING;

import org.quartz.Scheduler;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cn.cesgroup.cesweb.common.core.util.Response;
import cn.cesgroup.cesweb.common.log.annotation.SysLog;
import cn.cesgroup.cesweb.common.security.util.SecurityUtils;
import cn.cesgroup.cesweb.service.quartz.entity.SysJob;
import cn.cesgroup.cesweb.service.quartz.entity.SysJobLog;
import cn.cesgroup.cesweb.service.quartz.service.SysJobLogService;
import cn.cesgroup.cesweb.service.quartz.service.SysJobService;
import cn.cesgroup.cesweb.service.quartz.util.TaskUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

/**
 * 定时任务管理
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Aug 19, 2020 10:43:10 AM
 * @version 1.0.2020
 */
@RestController
@AllArgsConstructor
@RequestMapping("/sys-job")
@Api(value = "sys-job", tags = "定时任务")
public class SysJobController {

	private final SysJobService sysJobService;

	private final SysJobLogService sysJobLogService;

	private final TaskUtil taskUtil;

	private final Scheduler scheduler;

	/**
	 * 定时任务分页查询
	 * @param page 分页对象
	 * @param sysJob 定时任务调度表
	 * @return
	 */
	@GetMapping("/page")
	@ApiOperation(value = "分页定时业务查询")
	public Response<?> getSysJobPage(Page<SysJob> page, SysJob sysJob) {
		return Response.ok(sysJobService.page(page, Wrappers.query(sysJob)));
	}

	/**
	 * 通过id查询定时任务
	 * @param id id
	 * @return R
	 */
	@GetMapping("/{id}")
	@ApiOperation(value = "唯一标识查询定时任务")
	public Response<?> getById(@PathVariable("id") String id) {
		return Response.ok(sysJobService.getById(id));
	}

	/**
	 * 新增定时任务
	 * @param sysJob 定时任务调度表
	 * @return R
	 */
	@SysLog("新增定时任务")
	@PostMapping
	@PreAuthorize("@pms.hasPermission('job_sys_job_add')")
	@ApiOperation(value = "新增定时任务")
	public Response<?> save(@RequestBody SysJob sysJob) {
		sysJob.setJobStatus(JOB_STATUS_RELEASE.getType());
		sysJob.setCreateBy(SecurityUtils.getUser().getUsername());
		return Response.ok(sysJobService.save(sysJob));
	}

	/**
	 * 修改定时任务
	 * @param sysJob 定时任务调度表
	 * @return R
	 */
	@SysLog("修改定时任务")
	@PutMapping
	@PreAuthorize("@pms.hasPermission('job_sys_job_edit')")
	@ApiOperation(value = "修改定时任务")
	public Response<?> updateById(@RequestBody SysJob sysJob) {
		sysJob.setUpdateBy(SecurityUtils.getUser().getUsername());
		SysJob querySysJob = this.sysJobService.getById(sysJob.getJobId());
		if (JOB_STATUS_NOT_RUNNING.getType().equals(querySysJob.getJobStatus())) {
			this.taskUtil.addOrUpateJob(sysJob, scheduler);
			sysJobService.updateById(sysJob);
		}
		else if (JOB_STATUS_RELEASE.getType().equals(querySysJob.getJobStatus())) {
			sysJobService.updateById(sysJob);
		}
		return Response.ok();
	}

	/**
	 * 通过id删除定时任务
	 * @param id id
	 * @return R
	 */
	@SysLog("删除定时任务")
	@DeleteMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('job_sys_job_del')")
	@ApiOperation(value = "唯一标识查询定时任务，暂停任务才能删除")
	public Response<?> removeById(@PathVariable String id) {
		SysJob querySysJob = this.sysJobService.getById(id);
		if (JOB_STATUS_NOT_RUNNING.getType().equals(querySysJob.getJobStatus())) {
			this.taskUtil.removeJob(querySysJob, scheduler);
			this.sysJobService.removeById(id);
		}
		else if (JOB_STATUS_RELEASE.getType().equals(querySysJob.getJobStatus())) {
			this.sysJobService.removeById(id);
		}
		return Response.ok();
	}

	/**
	 * 暂停全部定时任务
	 * @return
	 */
	@SysLog("暂停全部定时任务")
	@PostMapping("/shutdown-jobs")
	@PreAuthorize("@pms.hasPermission('job_sys_job_shutdown_job')")
	@ApiOperation(value = "暂停全部定时任务")
	public Response<?> shutdownJobs() {
		taskUtil.pauseJobs(scheduler);
		int count = this.sysJobService.count(new LambdaQueryWrapper<SysJob>().eq(SysJob::getJobStatus, JOB_STATUS_RUNNING.getType()));
		if (count <= 0) {
			return Response.ok("无正在运行定时任务");
		}
		else {
			// 更新定时任务状态条件，运行状态2更新为暂停状态2
			this.sysJobService.update(SysJob.builder().jobStatus(JOB_STATUS_NOT_RUNNING.getType()).build(), new UpdateWrapper<SysJob>().lambda().eq(SysJob::getJobStatus, JOB_STATUS_RUNNING.getType()));
			return Response.ok("暂停成功");
		}
	}

	/**
	 * 启动全部定时任务
	 * @return
	 */
	@SysLog("启动全部定时任务")
	@PostMapping("/start-jobs")
	@PreAuthorize("@pms.hasPermission('job_sys_job_start_job')")
	@ApiOperation(value = "启动全部定时任务")
	public Response<?> startJobs() {
		// 更新定时任务状态条件，暂停状态3更新为运行状态2
		this.sysJobService.update(SysJob.builder().jobStatus(JOB_STATUS_RUNNING.getType()).build(), new UpdateWrapper<SysJob>().lambda().eq(SysJob::getJobStatus, JOB_STATUS_NOT_RUNNING.getType()));
		taskUtil.startJobs(scheduler);
		return Response.ok();
	}

	/**
	 * 刷新全部定时任务
	 * @return
	 */
	@SysLog("刷新全部定时任务")
	@PostMapping("/refresh-jobs")
	@PreAuthorize("@pms.hasPermission('job_sys_job_refresh_job')")
	@ApiOperation(value = "刷新全部定时任务")
	public Response<?> refreshJobs() {
		sysJobService.list().forEach((sysjob) -> {
			if (JOB_STATUS_RELEASE.getType().equals(sysjob.getJobStatus()) || JOB_STATUS_DEL.getType().equals(sysjob.getJobStatus())) {
				taskUtil.removeJob(sysjob, scheduler);
			}
			else if (JOB_STATUS_RUNNING.getType().equals(sysjob.getJobStatus()) || JOB_STATUS_NOT_RUNNING.getType().equals(sysjob.getJobStatus())) {
				taskUtil.addOrUpateJob(sysjob, scheduler);
			}
			else {
				taskUtil.removeJob(sysjob, scheduler);
			}
		});
		return Response.ok();
	}

	/**
	 * 启动定时任务
	 * @param jobId
	 * @return
	 */
	@SysLog("启动定时任务")
	@PostMapping("/start-job/{id}")
	@PreAuthorize("@pms.hasPermission('job_sys_job_start_job')")
	@ApiOperation(value = "启动定时任务")
	public Response<?> startJob(@PathVariable("id") String jobId) {
		SysJob querySysJob = this.sysJobService.getById(jobId);
		if (querySysJob != null && JOB_LOG_STATUS_FAIL.getType().equals(querySysJob.getJobStatus())) {
			taskUtil.addOrUpateJob(querySysJob, scheduler);
		}
		else {
			taskUtil.resumeJob(querySysJob, scheduler);
		}
		// 更新定时任务状态条件，暂停状态3更新为运行状态2
		this.sysJobService.updateById(SysJob.builder().jobId(jobId).jobStatus(JOB_STATUS_RUNNING.getType()).build());
		return Response.ok();
	}

	/**
	 * 启动定时任务
	 * @param jobId
	 */
	@SysLog("立刻执行定时任务")
	@PostMapping("/run-job/{id}")
	@PreAuthorize("@pms.hasPermission('job_sys_job_run_job')")
	@ApiOperation(value = "立刻执行定时任务")
	public Response<?> runJob(@PathVariable("id") String jobId) {
		SysJob querySysJob = this.sysJobService.getById(jobId);
		return TaskUtil.runOnce(scheduler, querySysJob) ? Response.ok() : Response.failed();
	}

	/**
	 * 暂停定时任务
	 */
	@SysLog("暂停定时任务")
	@PostMapping("/shutdown-job/{id}")
	@PreAuthorize("@pms.hasPermission('job_sys_job_shutdown_job')")
	@ApiOperation(value = "暂停定时任务")
	public Response<?> shutdownJob(@PathVariable("id") String id) {
		SysJob querySysJob = this.sysJobService.getById(id);
		// 更新定时任务状态条件，运行状态2更新为暂停状态3
		this.sysJobService.updateById(
				SysJob.builder().jobId(querySysJob.getJobId()).jobStatus(JOB_STATUS_NOT_RUNNING.getType()).build());
		taskUtil.pauseJob(querySysJob, scheduler);
		return Response.ok();
	}

	/**
	 * 唯一标识查询定时执行日志
	 */
	@GetMapping("/job-log")
	@ApiOperation(value = "唯一标识查询定时执行日志")
	public Response<?> getJobLog(Page<SysJobLog> page, SysJobLog sysJobLog) {
		return Response.ok(sysJobLogService.page(page, Wrappers.query(sysJobLog)));
	}

	/**
	 * 检验任务名称和任务组联合是否唯一
	 */
	@GetMapping("/is-valid-task-name")
	@ApiOperation(value = "检验任务名称和任务组联合是否唯一")
	public Response<?> isValidTaskName(@RequestParam String jobName, @RequestParam String jobGroup) {
		return this.sysJobService.count(Wrappers.query(SysJob.builder().jobName(jobName)
																	   .jobGroup(jobGroup).build())) > 0 
																	   ? Response.failed() 
																	   : Response.ok();
	}

}
