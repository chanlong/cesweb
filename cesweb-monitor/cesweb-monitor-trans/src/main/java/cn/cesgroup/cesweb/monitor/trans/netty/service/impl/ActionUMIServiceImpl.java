package cn.cesgroup.cesweb.monitor.trans.netty.service.impl;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import cn.cesgroup.cesweb.monitor.trans.framework.utils.SocketManager;
import cn.cesgroup.cesweb.monitor.trans.manager.ModelInfoManager;
import cn.cesgroup.cesweb.monitor.trans.model.ModelInfo;
import cn.cesgroup.cesweb.monitor.trans.netty.service.IActionService;

/**
 * 上传模块信息
 *
 * @author LCN on 2017/11/11
 */
@Service(value = "umi")
public class ActionUMIServiceImpl implements IActionService {

	@Override
	public String execute(String channelAddress, String key, JSONObject params) {
		String res = "1";

		String uniqueKey = params.getString("u");
		String ipAddress = params.getString("i");
		String model = params.getString("m");

		ModelInfo modelInfo = new ModelInfo();
		modelInfo.setChannelName(channelAddress);
		modelInfo.setIpAddress(ipAddress);
		modelInfo.setModel(model);
		modelInfo.setUniqueKey(uniqueKey);

		ModelInfoManager.getInstance().addModelInfo(modelInfo);

		SocketManager.getInstance().onLine(channelAddress, uniqueKey);

		return res;
	}

}
