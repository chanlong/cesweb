package cn.cesgroup.cesweb.service.workflow.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cn.cesgroup.cesweb.common.core.constant.enums.TaskStatusEnum;
import cn.cesgroup.cesweb.common.core.util.Response;
import cn.cesgroup.cesweb.common.security.util.SecurityUtils;
import cn.cesgroup.cesweb.service.workflow.entity.LeaveBill;
import cn.cesgroup.cesweb.service.workflow.service.LeaveBillService;
import cn.cesgroup.cesweb.service.workflow.service.ProcessService;
import lombok.AllArgsConstructor;

/**
 * 请假流程
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Aug 21, 2020 9:42:04 AM
 * @version 1.0.2020
 */
@RestController
@AllArgsConstructor
@RequestMapping("/leave-bill")
public class LeaveBillController {

	private final LeaveBillService leaveBillService;

	private final ProcessService processService;

	/**
	 * 请假审批单简单分页查询
	 * @param page 分页对象
	 * @param leaveBill 请假审批单
	 */
	@GetMapping("/page")
	public Response<Page<LeaveBill>> getLeaveBillPage(Page<LeaveBill> page, LeaveBill leaveBill) {
		return Response.ok(leaveBillService.page(page, Wrappers.query(leaveBill)));
	}

	/**
	 * 信息
	 * @param leaveId
	 */
	@GetMapping("/{leaveId}")
	public Response<LeaveBill> getById(@PathVariable("leaveId") String leaveId) {
		return Response.ok(leaveBillService.getById(leaveId));
	}

	/**
	 * 保存
	 * @param leaveBill
	 */
	@PostMapping
	public Response<Boolean> save(@RequestBody LeaveBill leaveBill) {
		leaveBill.setUsername(SecurityUtils.getUser().getUsername());
		leaveBill.setState(TaskStatusEnum.UNSUBMIT.getStatus());
		return Response.ok(leaveBillService.save(leaveBill));
	}

	/**
	 * 修改
	 * @param leaveBill
	 */
	@PutMapping
	public Response<Boolean> updateById(@RequestBody LeaveBill leaveBill) {
		return Response.ok(leaveBillService.updateById(leaveBill));
	}

	/**
	 * 删除
	 * @param leaveId
	 */
	@DeleteMapping("/{leaveId}")
	public Response<Boolean> removeById(@PathVariable String leaveId) {
		return Response.ok(leaveBillService.removeById(leaveId));
	}

	/**
	 * 提交请假流程
	 * @param leaveId
	 */
	@GetMapping("/submit/{leaveId}")
	public Response<Boolean> submit(@PathVariable("leaveId") String leaveId) {
		return Response.ok(processService.saveStartProcess("LeaveBill", leaveId, () -> {
			LeaveBill leaveBill = leaveBillService.getById(leaveId);
			leaveBill.setState(TaskStatusEnum.CHECK.getStatus());
			leaveBillService.updateById(leaveBill);
		}));
	}

}
