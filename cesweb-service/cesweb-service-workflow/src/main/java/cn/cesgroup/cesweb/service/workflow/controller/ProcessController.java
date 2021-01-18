package cn.cesgroup.cesweb.service.workflow.controller;

import java.io.InputStream;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;

import cn.cesgroup.cesweb.common.core.constant.enums.ResourceTypeEnum;
import cn.cesgroup.cesweb.common.core.util.Response;
import cn.cesgroup.cesweb.common.security.annotation.Inner;
import cn.cesgroup.cesweb.service.workflow.dto.ProcessDefDTO;
import cn.cesgroup.cesweb.service.workflow.service.ProcessService;
import cn.hutool.core.io.IoUtil;
import lombok.AllArgsConstructor;

/**
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Aug 21, 2020 9:54:50 AM
 * @version 1.0.2020
 */
@RestController
@AllArgsConstructor
@RequestMapping("/process")
public class ProcessController {

	private final ProcessService processService;

	@GetMapping
	public Response<IPage<ProcessDefDTO>> list(@RequestParam Map<String, Object> params) {
		return Response.ok(processService.getProcessByPage(params));
	}

	@Inner(value = false)
	@GetMapping(value = "/resource/{proInsId}/{procDefId}/{resType}")
	public ResponseEntity<?> resourceRead(@PathVariable String procDefId, @PathVariable String proInsId,
			@PathVariable String resType) {

		HttpHeaders headers = new HttpHeaders();

		if (ResourceTypeEnum.XML.getType().equals(resType)) {
			headers.setContentType(MediaType.APPLICATION_XML);
		}
		else {
			headers.setContentType(MediaType.IMAGE_PNG);
		}

		InputStream resourceAsStream = processService.readResource(procDefId, proInsId, resType);
		return new ResponseEntity<Object>(IoUtil.readBytes(resourceAsStream), headers, HttpStatus.CREATED);
	}

	@PutMapping("/status/{procDefId}/{status}")
	public Response<Boolean> updateState(@PathVariable String procDefId, @PathVariable String status) {
		return Response.ok(processService.updateStatus(status, procDefId));
	}

	@DeleteMapping("/{deploymentId}")
	public Response<Boolean> deleteProcIns(@PathVariable String deploymentId) {
		return Response.ok(processService.removeProcIns(deploymentId));
	}

}
