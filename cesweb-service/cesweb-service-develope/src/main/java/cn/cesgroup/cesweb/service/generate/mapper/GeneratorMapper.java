package cn.cesgroup.cesweb.service.generate.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * 代码生成器
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 13, 2020 12:09:34 PM
 * @version 1.0.2020
 */
@Mapper
public interface GeneratorMapper {

	/**
	 * 分页查询表格
	 * @param page 分页信息
	 * @param tableName 表名称
	 */
	IPage<List<Map<String, Object>>> queryList(Page<List<Map<String, Object>>> page, @Param("tableName") String tableName);

	/**
	 * 查询表信息
	 * @param tableName 表名称
	 * @param dsName 数据源名称
	 */
	@DS("#last")
	Map<String, String> queryTable(@Param("tableName") String tableName, String dsName);

}
