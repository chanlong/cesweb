package cn.cesgroup.cesweb.service.system.service;

import com.baomidou.mybatisplus.extension.service.IService;

import cn.cesgroup.cesweb.api.system.dto.UserInfo;
import cn.cesgroup.cesweb.api.system.entity.SysSocialDetails;

/**
 * 系统社交登录账号表
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 13, 2020 1:34:42 PM
 * @version 1.0.2020
 */
public interface SysSocialDetailsService extends IService<SysSocialDetails> {

	/**
	 * 绑定社交账号
	 * @param state 类型
	 * @param code code
	 */
	Boolean bindSocial(String state, String code);

	/**
	 * 根据入参查询用户信息
	 * @param inStr
	 */
	UserInfo getUserInfo(String inStr);

}
