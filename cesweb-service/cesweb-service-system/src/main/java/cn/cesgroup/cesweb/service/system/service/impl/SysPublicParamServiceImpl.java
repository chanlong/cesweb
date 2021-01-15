package cn.cesgroup.cesweb.service.system.service.impl;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cn.cesgroup.cesweb.api.system.entity.SysPublicParam;
import cn.cesgroup.cesweb.common.core.constant.CacheConstants;
import cn.cesgroup.cesweb.common.core.constant.enums.DictTypeEnum;
import cn.cesgroup.cesweb.common.core.util.Response;
import cn.cesgroup.cesweb.service.system.mapper.SysPublicParamMapper;
import cn.cesgroup.cesweb.service.system.service.SysPublicParamService;
import lombok.AllArgsConstructor;

/**
 * 公共参数配置
 *
 * @author Lucky
 * @date 2019-04-29
 */
/**
 * 系统参数配置表 服务实现类
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 13, 2020 3:31:20 PM
 * @version 1.0.2020
 */
@Service
@AllArgsConstructor
public class SysPublicParamServiceImpl extends ServiceImpl<SysPublicParamMapper, SysPublicParam> implements SysPublicParamService {

	@Override
	@Cacheable(value = CacheConstants.PARAMS_DETAILS, key = "#publicKey", unless = "#result == null ")
	public String getSysPublicParamKeyToValue(String publicKey) {
		SysPublicParam sysPublicParam = this.baseMapper.selectOne(Wrappers.<SysPublicParam>lambdaQuery().eq(SysPublicParam::getPublicKey, publicKey));
		if (sysPublicParam != null) {
			return sysPublicParam.getPublicValue();
		}
		return null;
	}

	/**
	 * 更新参数
	 * @param sysPublicParam
	 */
	@Override
	@CacheEvict(value = CacheConstants.PARAMS_DETAILS, key = "#sysPublicParam.publicKey")
	public Response<Boolean> updateParam(SysPublicParam sysPublicParam) {
		SysPublicParam param = this.getById(sysPublicParam.getPublicId());
		// 系统内置
		if (DictTypeEnum.SYSTEM.getType().equals(param.getSystem())) {
			return Response.failed("系统内置参数不能删除");
		}
		return Response.ok(this.updateById(sysPublicParam));
	}

	/**
	 * 删除参数
	 * @param publicId
	 * @return
	 */
	@Override
	@CacheEvict(value = CacheConstants.PARAMS_DETAILS, allEntries = true)
	public Response<Boolean> removeParam(String publicId) {
		SysPublicParam param = this.getById(publicId);
		// 系统内置
		if (DictTypeEnum.SYSTEM.getType().equals(param.getSystem())) {
			return Response.failed("系统内置参数不能删除");
		}
		return Response.ok(this.removeById(publicId));
	}

}
