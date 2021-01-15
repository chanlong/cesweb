package cn.cesgroup.cesweb.service.system.service;

import com.baomidou.mybatisplus.extension.service.IService;

import cn.cesgroup.cesweb.api.system.entity.SysOauthClientDetails;

/**
 * 服务类
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 13, 2020 1:36:44 PM
 * @version 1.0.2020
 */
public interface SysOauthClientDetailsService extends IService<SysOauthClientDetails> {

	/**
	 * 通过ID删除客户端
	 * @param clientId
	 */
	Boolean removeByClientId(String clientId);

	/**
	 * 根据客户端信息
	 * @param clientDetails
	 */
	Boolean updateClientById(SysOauthClientDetails clientDetails);

}
