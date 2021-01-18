package cn.cesgroup.cesweb.service.workflow.controller;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;

import cn.cesgroup.cesweb.common.core.util.Response;
import cn.cesgroup.cesweb.common.security.annotation.Inner;
import cn.cesgroup.cesweb.common.security.util.SecurityUtils;
import cn.cesgroup.cesweb.service.workflow.dto.CommentDto;
import cn.cesgroup.cesweb.service.workflow.dto.LeaveBillDto;
import cn.cesgroup.cesweb.service.workflow.service.TaskService;
import cn.hutool.core.io.IoUtil;
import lombok.AllArgsConstructor;

/**
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Aug 21, 2020 9:57:44 AM
 * @version 1.0.2020
 */
@RestController
@AllArgsConstructor
@RequestMapping("/task")
public class TaskController {

	private final TaskService taskService;

	@GetMapping("/todo")
	public Response<IPage<?>> todo(@RequestParam Map<String, Object> params) {
		return Response.ok(taskService.getTaskByName(params, SecurityUtils.getUser().getUsername()));
	}

	@GetMapping("/{id}")
	public Response<LeaveBillDto> getTaskById(@PathVariable String id) {
		return Response.ok(taskService.getTaskById(id));
	}

	@PostMapping
	public Response<Boolean> submitTask(@RequestBody LeaveBillDto leaveBillDto) {
		return Response.ok(taskService.submitTask(leaveBillDto));
	}

	@Inner(value = false)
	@GetMapping("/view/{id}")
	public ResponseEntity<?> viewCurrentImage(@PathVariable String id) {
		InputStream imageStream = taskService.viewByTaskId(id);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_PNG);
		return new ResponseEntity<Object>(IoUtil.readBytes(imageStream), headers, HttpStatus.CREATED);
	}

	@GetMapping("/comment/{id}")
	public Response<List<CommentDto>> commitList(@PathVariable String id) {
		return Response.ok(taskService.getCommentByTaskId(id));
	}

}
