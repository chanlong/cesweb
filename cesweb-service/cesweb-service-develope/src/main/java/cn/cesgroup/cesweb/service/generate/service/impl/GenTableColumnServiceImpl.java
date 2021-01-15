package cn.cesgroup.cesweb.service.generate.service.impl;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cn.cesgroup.cesweb.service.generate.entity.ColumnEntity;
import cn.cesgroup.cesweb.service.generate.entity.GenConfig;
import cn.cesgroup.cesweb.service.generate.mapper.GenTableColumnMapper;
import cn.cesgroup.cesweb.service.generate.service.GenTableColumnService;
import cn.cesgroup.cesweb.service.generate.util.GenUtils;
import lombok.AllArgsConstructor;

/**
 * 表字段信息管理
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 13, 2020 12:01:32 PM
 * @version 1.0.2020
 */
@Service
@AllArgsConstructor
public class GenTableColumnServiceImpl extends ServiceImpl<GenTableColumnMapper, ColumnEntity> implements GenTableColumnService {

	@Override
	public IPage<ColumnEntity> listTable(Page<ColumnEntity> page, GenConfig genConfig) {
		IPage<ColumnEntity> columnPage = baseMapper.selectTableColumn(page, genConfig.getTableName(), genConfig.getDsName());

		// 处理 数据库类型和 Java 类型关系
		Configuration config = GenUtils.getConfig();
		columnPage.getRecords().forEach(column -> {
			String attrType = config.getString(column.getDataType(), "unknowType");
			column.setLowerAttrName(StringUtils.uncapitalize(GenUtils.columnToJava(column.getColumnName())));
			column.setJavaType(attrType);
		});
		return columnPage;
	}

}
