package cn.cesgroup.cesweb.service.workflow.controller;

import java.util.Map;

import javax.validation.Valid;

import org.activiti.engine.repository.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;

import cn.cesgroup.cesweb.common.core.controller.BaseController;
import cn.cesgroup.cesweb.common.core.util.Response;
import cn.cesgroup.cesweb.service.workflow.model.form.ModelForm;
import cn.cesgroup.cesweb.service.workflow.service.ModelService;
import lombok.AllArgsConstructor;

/**
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Aug 21, 2020 9:54:35 AM
 * @version 1.0.2020
 */
@RestController
@RequestMapping("/model")
@AllArgsConstructor
public class ModelController extends BaseController {

	private final ModelService modelService;

	/**
	 * 创建流程模型.
	 * @param form
	 * @author chanlong(陈龙)
	 * @date Sep 3, 2020 10:22:18 AM
	 */
	@PostMapping
	public Response<Model> createModel(@RequestBody @Valid ModelForm form) {
		return Response.ok(modelService.create(form.getName(), form.getKey(), form.getDesc(), form.getCategory()));
	}
	
	@PutMapping("/{id}")
	public Response<?> saveModelEditor(@PathVariable(value = "id") String id, @RequestBody ModelForm form) {
		modelService.saveModel(id, form);
		return Response.ok();
	}
	
	/**
	 * 删除流程模型.
	 * @param id
	 * @author chanlong(陈龙)
	 * @date Sep 3, 2020 10:18:55 AM
	 */
	@DeleteMapping("/{id}")
	public Response<Boolean> remove(@PathVariable("id") String id) {
		return Response.ok(modelService.removeModelById(id));
	}

	/**
	 * 获取流程模型.
	 * @param id
	 * @author chanlong(陈龙)
	 * @date Sep 3, 2020 10:18:20 AM
	 */
	@GetMapping("/{id}")
	public Response<?> getModel(@PathVariable("id") String id) {
		return Response.ok(modelService.getModel(id));
	}
	
	/**
	 * 分页查询流程模型.
	 * @param params
	 * @author chanlong(陈龙)
	 * @date Sep 3, 2020 10:20:03 AM
	 */
	@GetMapping
	public Response<IPage<Model>> getModelPage(@RequestParam Map<String, Object> params) {
		return Response.ok(modelService.getModelPage(params));
	}

	/**
	 * 部署流程.
	 * @param id
	 * @return
	 * @author chanlong(陈龙)
	 * @date Sep 3, 2020 10:18:46 AM
	 */
	@PostMapping("/{id}/deploy")
	public Response<?> deploy(@PathVariable("id") String id) {
		return modelService.deploy(id) ? Response.ok() : Response.failed();
	}

	/**
	 * 获取流程模型BpmnXML.
	 * @param id
	 * @author chanlong(陈龙)
	 * @date Sep 3, 2020 10:18:20 AM
	 */
	@GetMapping("/{id}/bpmnxml")
	public Response<?> getModelBpmn(@PathVariable("id") String id) {
		return Response.ok(modelService.getEditorJson(id));
	}
}
