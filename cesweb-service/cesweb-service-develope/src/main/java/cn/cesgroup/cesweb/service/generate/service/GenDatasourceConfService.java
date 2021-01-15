package cn.cesgroup.cesweb.service.generate.service;

import com.baomidou.mybatisplus.extension.service.IService;

import cn.cesgroup.cesweb.service.generate.entity.GenDatasourceConf;

/**
 * 数据源信息表
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 13, 2020 12:00:16 PM
 * @version 1.0.2020
 */
public interface GenDatasourceConfService extends IService<GenDatasourceConf> {

	/**
	 * 保存数据源并且加密
	 * @param genDatasourceConf
	 */
	Boolean saveDsByEnc(GenDatasourceConf genDatasourceConf);

	/**
	 * 更新数据源
	 * @param genDatasourceConf
	 */
	Boolean updateDsByEnc(GenDatasourceConf genDatasourceConf);

	/**
	 * 更新动态数据的数据源列表
	 * @param datasourceConf
	 */
	void addDynamicDataSource(GenDatasourceConf datasourceConf);

	/**
	 * 校验数据源配置是否有效
	 * @param datasourceConf 数据源信息
	 * @return 有效/无效
	 */
	Boolean checkDataSource(GenDatasourceConf datasourceConf);

	/**
	 * 通过数据源名称删除
	 * @param dsId 数据源ID
	 */
	Boolean removeByDsId(Integer dsId);

}
