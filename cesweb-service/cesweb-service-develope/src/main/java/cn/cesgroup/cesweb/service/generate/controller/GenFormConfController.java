package cn.cesgroup.cesweb.service.generate.controller;

import org.springframework.security.access.prepost.PreAuthorize;
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

import cn.cesgroup.cesweb.common.core.util.Response;
import cn.cesgroup.cesweb.common.log.annotation.SysLog;
import cn.cesgroup.cesweb.service.generate.entity.GenFormConf;
import cn.cesgroup.cesweb.service.generate.service.GenFormConfService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

/**
 * 表单管理
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 13, 2020 12:12:40 PM
 * @version 1.0.2020
 */
@RestController
@AllArgsConstructor
@RequestMapping("/form")
@Api(value = "form", tags = "表单管理")
public class GenFormConfController {

	private final GenFormConfService genRecordService;

	/**
	 * 分页查询
	 * @param page 分页对象
	 * @param formConf 生成记录
	 * @return Response
	 */
	@ApiOperation(value = "分页查询", notes = "分页查询")
	@GetMapping("/page")
	public Response<Page<GenFormConf>> getGenFormConfPage(Page<GenFormConf> page, GenFormConf formConf) {
		return Response.ok(genRecordService.page(page, Wrappers.query(formConf)));
	}

	/**
	 * 通过id查询生成记录
	 * @param id id
	 * @return Response
	 */
	@ApiOperation(value = "通过id查询", notes = "通过id查询")
	@GetMapping("/{id}")
	public Response<GenFormConf> getById(@PathVariable("id") Integer id) {
		return Response.ok(genRecordService.getById(id));
	}

	/**
	 * 通过id查询生成记录
	 * @param dsName 数据源ID
	 * @param tableName tableName
	 * @return Response
	 */
	@ApiOperation(value = "通过tableName查询表单信息")
	@GetMapping("/info")
	public Response<String> form(String dsName, String tableName) {
		return Response.ok(genRecordService.getForm(dsName, tableName));
	}

	/**
	 * 新增生成记录
	 * @param formConf 生成记录
	 * @return R
	 */
	@ApiOperation(value = "新增生成记录", notes = "新增生成记录")
	@SysLog("新增生成记录")
	@PostMapping
	@PreAuthorize("@pms.hasPermission('gen_form_add')")
	public Response<Boolean> save(@RequestBody GenFormConf formConf) {
		return Response.ok(genRecordService.save(formConf));
	}

	/**
	 * 修改生成记录
	 * @param formConf 生成记录
	 * @return R
	 */
	@ApiOperation(value = "修改生成记录", notes = "修改生成记录")
	@SysLog("修改生成记录")
	@PutMapping
	@PreAuthorize("@pms.hasPermission('gen_form_edit')")
	public Response<Boolean> updateById(@RequestBody GenFormConf formConf) {
		return Response.ok(genRecordService.updateById(formConf));
	}

	/**
	 * 通过id删除生成记录
	 * @param id id
	 * @return R
	 */
	@ApiOperation(value = "通过id删除生成记录", notes = "通过id删除生成记录")
	@SysLog("通过id删除生成记录")
	@DeleteMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('gen_form_del')")
	public Response<Boolean> removeById(@PathVariable Integer id) {
		return Response.ok(genRecordService.removeById(id));
	}

}
