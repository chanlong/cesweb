package cn.cesgroup.cesweb.common.data.datascope;

import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

/**
 * 分页支持
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 10, 2020 3:32:21 PM
 * @version 1.0.2020
 */
public class SelectPageByScope extends AbstractMethod {

	@Override
	public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
		SqlMethod sqlMethod = SqlMethod.SELECT_PAGE;
		String sql = String.format(sqlMethod.getSql(), sqlFirst(), sqlSelectColumns(tableInfo, true), tableInfo.getTableName(), sqlWhereEntityWrapper(true, tableInfo), sqlComment());
		SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, modelClass);
		return this.addSelectMappedStatementForTable(mapperClass, "selectPageByScope", sqlSource, tableInfo);
	}

}
