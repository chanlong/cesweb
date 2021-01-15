package cn.cesgroup.cesweb.service.generate.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cn.cesgroup.cesweb.service.generate.entity.ColumnEntity;

/**
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 13, 2020 12:08:44 PM
 * @version 1.0.2020
 */
@Mapper
public interface GenTableColumnMapper extends BaseMapper<ColumnEntity> {

	/**
	 * 分页查询表分页信息
	 * @param page
	 * @param tableName
	 * @param dsName
	 */
	@DS("#last")
	IPage<ColumnEntity> selectTableColumn(Page<ColumnEntity> page, @Param("tableName") String tableName, @Param("dsName") String dsName);

	/**
	 * 查询表全部列信息
	 * @param tableName
	 * @param dsName
	 */
	@DS("#last")
	List<ColumnEntity> selectTableColumn(@Param("tableName") String tableName, @Param("dsName") String dsName);

	/**
	 * 查询表全部列信息
	 * @param tableName 表名称
	 * @param dsName 数据源名称
	 */
	@DS("#last")
	List<Map<String, String>> selectMapTableColumn(@Param("tableName") String tableName, String dsName);

}
