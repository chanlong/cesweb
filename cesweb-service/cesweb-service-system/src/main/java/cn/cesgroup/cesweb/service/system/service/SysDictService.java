package cn.cesgroup.cesweb.service.system.service;

import com.baomidou.mybatisplus.extension.service.IService;

import cn.cesgroup.cesweb.api.system.entity.SysDict;
import cn.cesgroup.cesweb.common.core.util.Response;

/**
 * 字典表
 *
 * @author lengleng
 * @date 2019/03/19
 */
public interface SysDictService extends IService<SysDict> {

	/**
	 * 根据ID 删除字典
	 * @param id
	 */
	Response<Object> removeDict(String id);

	/**
	 * 更新字典
	 * @param sysDict 字典
	 */
	Response<Boolean> updateDict(SysDict sysDict);

}
