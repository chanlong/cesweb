package cn.cesgroup.cesweb.service.generate.service;

import com.baomidou.mybatisplus.extension.service.IService;

import cn.cesgroup.cesweb.service.generate.entity.GenFormConf;

/**
 * 表单管理
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 13, 2020 11:59:15 AM
 * @version 1.0.2020
 */
public interface GenFormConfService extends IService<GenFormConf> {

	/**
	 * 获取表单信息
	 * @param dsName 数据源ID
	 * @param tableName 表名称
	 */
	String getForm(String dsName, String tableName);

}
