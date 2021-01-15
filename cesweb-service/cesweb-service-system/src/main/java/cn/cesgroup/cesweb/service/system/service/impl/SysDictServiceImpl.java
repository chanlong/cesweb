package cn.cesgroup.cesweb.service.system.service.impl;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cn.cesgroup.cesweb.api.system.entity.SysDict;
import cn.cesgroup.cesweb.api.system.entity.SysDictItem;
import cn.cesgroup.cesweb.common.core.constant.CacheConstants;
import cn.cesgroup.cesweb.common.core.constant.enums.DictTypeEnum;
import cn.cesgroup.cesweb.common.core.util.Response;
import cn.cesgroup.cesweb.service.system.mapper.SysDictItemMapper;
import cn.cesgroup.cesweb.service.system.mapper.SysDictMapper;
import cn.cesgroup.cesweb.service.system.service.SysDictService;
import lombok.AllArgsConstructor;

/**
 * 字典表 服务实现类
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 13, 2020 3:40:28 PM
 * @version 1.0.2020
 */
@Service
@AllArgsConstructor
public class SysDictServiceImpl extends ServiceImpl<SysDictMapper, SysDict> implements SysDictService {

	private final SysDictItemMapper dictItemMapper;

	/**
	 * 根据ID 删除字典
	 * @param id 字典ID
	 * @return
	 */
	@Override
	@CacheEvict(value = CacheConstants.DICT_DETAILS, allEntries = true)
	@Transactional(rollbackFor = Exception.class)
	public Response<Object> removeDict(String id) {
		SysDict dict = this.getById(id);
		// 系统内置
		if (DictTypeEnum.SYSTEM.getType().equals(dict.getSystem())) {
			return Response.failed("系统内置字典不能删除");
		}

		baseMapper.deleteById(id);
		dictItemMapper.delete(Wrappers.<SysDictItem>lambdaQuery().eq(SysDictItem::getDictId, id));
		return Response.ok();
	}

	/**
	 * 更新字典
	 * @param dict 字典
	 * @return
	 */
	@Override
	public Response<Boolean> updateDict(SysDict dict) {
		SysDict sysDict = this.getById(dict.getId());
		// 系统内置
		if (DictTypeEnum.SYSTEM.getType().equals(sysDict.getSystem())) {
			return Response.failed("系统内置字典不能修改");
		}
		return Response.ok(this.updateById(dict));
	}

}
