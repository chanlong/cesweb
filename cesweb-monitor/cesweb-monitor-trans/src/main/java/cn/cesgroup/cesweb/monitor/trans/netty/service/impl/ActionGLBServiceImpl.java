package cn.cesgroup.cesweb.monitor.trans.netty.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import cn.cesgroup.cesweb.monitor.trans.manager.service.LoadBalanceService;
import cn.cesgroup.cesweb.monitor.trans.model.LoadBalanceInfo;
import cn.cesgroup.cesweb.monitor.trans.netty.service.IActionService;

/**
 * 获取负载模块信息
 *
 * @author LCN on 2017/11/11
 */
@Service(value = "glb")
public class ActionGLBServiceImpl implements IActionService {

	@Autowired
	private LoadBalanceService loadBalanceService;

	@Override
	public String execute(String channelAddress, String key, JSONObject params) {
		String res;
		String groupId = params.getString("g");
		String k = params.getString("k");

		LoadBalanceInfo loadBalanceInfo = loadBalanceService.get(groupId, k);
		if (loadBalanceInfo == null) {
			res = "";
		}
		else {
			res = loadBalanceInfo.getData();
		}
		return res;
	}

}
