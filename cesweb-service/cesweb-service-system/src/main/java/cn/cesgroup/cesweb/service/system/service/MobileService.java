package cn.cesgroup.cesweb.service.system.service;

import cn.cesgroup.cesweb.common.core.util.Response;

/**
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 13, 2020 1:40:58 PM
 * @version 1.0.2020
 */
public interface MobileService {

	/**
	 * 发送手机验证码
	 * @param mobile mobile
	 * @return code
	 */
	Response<Boolean> sendSmsCode(String mobile);

}
