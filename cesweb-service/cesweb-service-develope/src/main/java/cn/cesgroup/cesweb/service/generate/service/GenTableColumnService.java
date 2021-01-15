package cn.cesgroup.cesweb.service.generate.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import cn.cesgroup.cesweb.service.generate.entity.ColumnEntity;
import cn.cesgroup.cesweb.service.generate.entity.GenConfig;

/**
 * 表字段信息表
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 13, 2020 11:58:34 AM
 * @version 1.0.2020
 */
public interface GenTableColumnService extends IService<ColumnEntity> {

	/**
	 * 查询表的字段信息
	 * @param page
	 * @param genConfig 查询条件
	 */
	IPage<ColumnEntity> listTable(Page<ColumnEntity> page, GenConfig genConfig);

}
