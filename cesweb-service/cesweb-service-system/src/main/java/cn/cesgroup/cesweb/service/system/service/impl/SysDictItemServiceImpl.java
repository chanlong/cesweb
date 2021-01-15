package cn.cesgroup.cesweb.service.system.service.impl;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cn.cesgroup.cesweb.api.system.entity.SysDict;
import cn.cesgroup.cesweb.api.system.entity.SysDictItem;
import cn.cesgroup.cesweb.common.core.constant.CacheConstants;
import cn.cesgroup.cesweb.common.core.constant.enums.DictTypeEnum;
import cn.cesgroup.cesweb.common.core.util.Response;
import cn.cesgroup.cesweb.service.system.mapper.SysDictItemMapper;
import cn.cesgroup.cesweb.service.system.service.SysDictItemService;
import cn.cesgroup.cesweb.service.system.service.SysDictService;
import lombok.AllArgsConstructor;

/**
 * 字典项 服务实现类
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 13, 2020 3:48:46 PM
 * @version 1.0.2020
 */
@Service
@AllArgsConstructor
public class SysDictItemServiceImpl extends ServiceImpl<SysDictItemMapper, SysDictItem> implements SysDictItemService {

	private final SysDictService dictService;

	/**
	 * 删除字典项
	 * @param id 字典项ID
	 */
	@Override
	@CacheEvict(value = CacheConstants.DICT_DETAILS, allEntries = true)
	public Response<Boolean> removeDictItem(String id) {
		// 根据ID查询字典ID
		SysDictItem dictItem = this.getById(id);
		SysDict dict = dictService.getById(dictItem.getDictId());
		// 系统内置
		if (DictTypeEnum.SYSTEM.getType().equals(dict.getSystem())) {
			return Response.failed("系统内置字典项目不能删除");
		}
		return Response.ok(this.removeById(id));
	}

	/**
	 * 更新字典项
	 * @param item 字典项
	 */
	@Override
	@CacheEvict(value = CacheConstants.DICT_DETAILS, allEntries = true)
	public Response<Boolean> updateDictItem(SysDictItem item) {
		// 查询字典
		SysDict dict = dictService.getById(item.getDictId());
		// 系统内置
		if (DictTypeEnum.SYSTEM.getType().equals(dict.getSystem())) {
			return Response.failed("系统内置字典项目不能删除");
		}
		return Response.ok(this.updateById(item));
	}

}
