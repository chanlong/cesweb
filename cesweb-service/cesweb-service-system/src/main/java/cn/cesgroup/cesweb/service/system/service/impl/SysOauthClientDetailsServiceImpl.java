package cn.cesgroup.cesweb.service.system.service.impl;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cn.cesgroup.cesweb.api.system.entity.SysOauthClientDetails;
import cn.cesgroup.cesweb.common.core.constant.CacheConstants;
import cn.cesgroup.cesweb.service.system.mapper.SysOauthClientDetailsMapper;
import cn.cesgroup.cesweb.service.system.service.SysOauthClientDetailsService;

/**
 * 服务实现类
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 13, 2020 3:33:15 PM
 * @version 1.0.2020
 */
@Service
public class SysOauthClientDetailsServiceImpl extends ServiceImpl<SysOauthClientDetailsMapper, SysOauthClientDetails> implements SysOauthClientDetailsService {

	/**
	 * 通过ID删除客户端
	 * @param clientId
	 */
	@Override
	@CacheEvict(value = CacheConstants.CLIENT_DETAILS_KEY, key = "#clientId")
	public Boolean removeByClientId(String clientId) {
		return this.remove(Wrappers.<SysOauthClientDetails>lambdaQuery().eq(SysOauthClientDetails::getClientId, clientId));
	}

	/**
	 * 根据客户端信息
	 * @param clientDetails
	 */
	@Override
	@CacheEvict(value = CacheConstants.CLIENT_DETAILS_KEY, key = "#clientDetails.clientId")
	public Boolean updateClientById(SysOauthClientDetails clientDetails) {
		return this.updateById(clientDetails);
	}

}
