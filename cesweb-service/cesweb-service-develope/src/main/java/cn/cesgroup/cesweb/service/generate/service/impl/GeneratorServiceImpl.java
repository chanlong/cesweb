package cn.cesgroup.cesweb.service.generate.service.impl;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

import org.springframework.stereotype.Service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cn.cesgroup.cesweb.service.generate.entity.GenConfig;
import cn.cesgroup.cesweb.service.generate.entity.GenFormConf;
import cn.cesgroup.cesweb.service.generate.mapper.GenFormConfMapper;
import cn.cesgroup.cesweb.service.generate.mapper.GenTableColumnMapper;
import cn.cesgroup.cesweb.service.generate.mapper.GeneratorMapper;
import cn.cesgroup.cesweb.service.generate.service.GeneratorService;
import cn.cesgroup.cesweb.service.generate.util.GenUtils;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;

/**
 * 代码生成器
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 13, 2020 12:02:20 PM
 * @version 1.0.2020
 */
@Service
@AllArgsConstructor
public class GeneratorServiceImpl implements GeneratorService {

	private final GenTableColumnMapper tableColumnMapper;

	private final GenFormConfMapper genFormConfMapper;
	
	private final GeneratorMapper generatorMapper;

	/**
	 * 分页查询表
	 * @param tableName 查询条件
	 * @param dsName
	 */
	@Override
	@DS("#last")
	public IPage<List<Map<String, Object>>> getPage(Page<List<Map<String, Object>>> page, String tableName, String dsName) {
		return generatorMapper.queryList(page, tableName);
	}

	@Override
	public Map<String, String> previewCode(GenConfig genConfig) {
		// 根据tableName 查询最新的表单配置
		List<GenFormConf> formConfList = genFormConfMapper.selectList(Wrappers.<GenFormConf>lambdaQuery().eq(GenFormConf::getTableName, genConfig.getTableName()).orderByDesc(GenFormConf::getCreateTime));

		DynamicDataSourceContextHolder.push(genConfig.getDsName());

		String tableNames = genConfig.getTableName();
		for (String tableName : StrUtil.split(tableNames, StrUtil.DASHED)) {
			// 查询表信息
			Map<String, String> table = generatorMapper.queryTable(tableName, genConfig.getDsName());
			
			// 查询列信息
			List<Map<String, String>> columns = tableColumnMapper.selectMapTableColumn(tableName, genConfig.getDsName());
			
			// 生成代码
			if (CollUtil.isNotEmpty(formConfList)) {
				return GenUtils.generatorCode(genConfig, table, columns, null, formConfList.get(0));
			}
			else {
				return GenUtils.generatorCode(genConfig, table, columns, null, null);
			}
		}
		return MapUtil.empty();
	}

	/**
	 * 生成代码
	 * @param genConfig 生成配置
	 */
	@Override
	public byte[] generatorCode(GenConfig genConfig) {
		// 根据tableName 查询最新的表单配置
		List<GenFormConf> formConfList = genFormConfMapper.selectList(Wrappers.<GenFormConf>lambdaQuery().eq(GenFormConf::getTableName, genConfig.getTableName()).orderByDesc(GenFormConf::getCreateTime));

		DynamicDataSourceContextHolder.push(genConfig.getDsName());
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ZipOutputStream zip = new ZipOutputStream(outputStream);

		String tableNames = genConfig.getTableName();
		for (String tableName : StrUtil.split(tableNames, StrUtil.DASHED)) {
			// 查询表信息
			Map<String, String> table = generatorMapper.queryTable(tableName, genConfig.getDsName());
			// 查询列信息
			List<Map<String, String>> columns = tableColumnMapper.selectMapTableColumn(tableName, genConfig.getDsName());
			
			// 生成代码
			if (CollUtil.isNotEmpty(formConfList)) {
				GenUtils.generatorCode(genConfig, table, columns, zip, formConfList.get(0));
			}
			else {
				GenUtils.generatorCode(genConfig, table, columns, zip, null);
			}
		}
		IoUtil.close(zip);
		return outputStream.toByteArray();
	}

}
