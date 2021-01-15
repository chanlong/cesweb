package cn.cesgroup.cesweb.service.system.service;

import com.baomidou.mybatisplus.extension.service.IService;

import cn.cesgroup.cesweb.api.system.entity.SysPublicParam;
import cn.cesgroup.cesweb.common.core.util.Response;

/**
 * 公共参数配置
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 13, 2020 1:35:52 PM
 * @version 1.0.2020
 */
public interface SysPublicParamService extends IService<SysPublicParam> {

	/**
	 * 通过key查询公共参数指定值
	 * @param publicKey
	 * @return
	 */
	String getSysPublicParamKeyToValue(String publicKey);

	/**
	 * 更新参数
	 * @param sysPublicParam
	 * @return
	 */
	Response<Boolean> updateParam(SysPublicParam sysPublicParam);

	/**
	 * 删除参数
	 * @param publicId
	 */
	Response<Boolean> removeParam(String publicId);

}
