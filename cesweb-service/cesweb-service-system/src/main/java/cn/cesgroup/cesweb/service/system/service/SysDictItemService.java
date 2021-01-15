package cn.cesgroup.cesweb.service.system.service;

import com.baomidou.mybatisplus.extension.service.IService;

import cn.cesgroup.cesweb.api.system.entity.SysDictItem;
import cn.cesgroup.cesweb.common.core.util.Response;

/**
 * 字典项
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 13, 2020 1:39:38 PM
 * @version 1.0.2020
 */
public interface SysDictItemService extends IService<SysDictItem> {

	/**
	 * 删除字典项
	 * @param id 字典项ID
	 * @return
	 */
	Response<Boolean> removeDictItem(String id);

	/**
	 * 更新字典项
	 * @param item 字典项
	 * @return
	 */
	Response<Boolean> updateDictItem(SysDictItem item);

}
