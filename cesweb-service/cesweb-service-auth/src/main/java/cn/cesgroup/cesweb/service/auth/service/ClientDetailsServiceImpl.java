package cn.cesgroup.cesweb.service.auth.service;

import javax.sql.DataSource;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.stereotype.Service;

import cn.cesgroup.cesweb.common.core.constant.CacheConstants;
import cn.cesgroup.cesweb.common.core.constant.SecurityConstants;
import cn.cesgroup.cesweb.common.data.tenant.TenantContextHolder;
import lombok.extern.slf4j.Slf4j;

/**
 * 扩展 JdbcClientDetailsService 支持多租户
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 14, 2020 9:45:26 AM
 * @version 1.0.2020
 */
@Slf4j
@Service
public class ClientDetailsServiceImpl extends JdbcClientDetailsService {

	public ClientDetailsServiceImpl(DataSource dataSource) {
		super(dataSource);
	}

	/**
	 * 重写原生方法支持redis缓存
	 * @param clientId
	 * @return ClientDetails
	 * @throws InvalidClientException
	 */
	@Override
	@Cacheable(value = CacheConstants.CLIENT_DETAILS_KEY, key = "#clientId", unless = "#result == null")
	public ClientDetails loadClientByClientId(String clientId) {
		log.info("当前客户端：{}",clientId);
		super.setSelectClientDetailsSql(String.format(SecurityConstants.DEFAULT_SELECT_STATEMENT, TenantContextHolder.getTenantId()));
		return super.loadClientByClientId(clientId);
	}

}
