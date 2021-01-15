package cn.cesgroup.cesweb.service.generate.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cn.cesgroup.cesweb.common.core.util.Response;
import cn.cesgroup.cesweb.service.generate.entity.ColumnEntity;
import cn.cesgroup.cesweb.service.generate.entity.GenConfig;
import cn.cesgroup.cesweb.service.generate.service.GenTableColumnService;
import cn.cesgroup.cesweb.service.generate.service.GeneratorService;
import cn.hutool.core.io.IoUtil;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

/**
 * 代码生成器
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 13, 2020 12:17:50 PM
 * @version 1.0.2020
 */
@RestController
@AllArgsConstructor
@RequestMapping("/generator")
public class GeneratorController {

	private final GeneratorService generatorService;

	private final GenTableColumnService columnService;

	/**
	 * 列表
	 * @param tableName 参数集
	 * @param dsName 数据源编号
	 * @return 数据库表
	 */
	@GetMapping("/page")
	public Response<IPage<List<Map<String, Object>>>> getPage(Page<List<Map<String, Object>>> page, String tableName, String dsName) {
		return Response.ok(generatorService.getPage(page, tableName, dsName));
	}

	/**
	 * 预览代码
	 * @param genConfig 数据表配置
	 * @return
	 */
	@GetMapping("/preview")
	public Response<Map<String, String>> previewCode(GenConfig genConfig) {
		return Response.ok(generatorService.previewCode(genConfig));
	}

	/**
	 * 查询表的列信息
	 * @param page 分页
	 * @param genConfig 数据表配置
	 * @return
	 */
	@GetMapping("/table")
	public Response<IPage<ColumnEntity>> getTable(Page<ColumnEntity> page, GenConfig genConfig) {
		return Response.ok(columnService.listTable(page, genConfig));
	}

	/**
	 * 生成代码
	 * @param genConfig 数据表配置
	 */
	@SneakyThrows
	@PostMapping("/code")
	public void generatorCode(@RequestBody GenConfig genConfig, HttpServletResponse response) {
		byte[] data = generatorService.generatorCode(genConfig);
		response.reset();
		response.setHeader(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=%s.zip", genConfig.getTableName()));
		response.addHeader(HttpHeaders.CONTENT_LENGTH, String.valueOf(data.length));
		response.setContentType("application/octet-stream; charset=UTF-8");

		IoUtil.write(response.getOutputStream(), Boolean.TRUE, data);
	}

}
