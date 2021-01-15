package cn.cesgroup.cesweb.service.generate.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cn.cesgroup.cesweb.service.generate.entity.GenConfig;

/**
 * 代码生成
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 13, 2020 11:59:33 AM
 * @version 1.0.2020
 */
public interface GeneratorService {

	/**
	 * 生成代码
	 * @param genConfig 生成信息
	 */
	byte[] generatorCode(GenConfig genConfig);

	/**
	 * 分页查询表
	 * @param page 分页信息
	 * @param tableName 表名
	 * @param name 数据源ID
	 */
	IPage<List<Map<String, Object>>> getPage(Page<List<Map<String, Object>>> page, String tableName, String name);

	/**
	 * 预览代码
	 * @param genConfig 生成信息
	 */
	Map<String, String> previewCode(GenConfig genConfig);

}
