package cn.cesgroup.cesweb.monitor.trans.netty.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import cn.cesgroup.cesweb.monitor.trans.manager.service.LoadBalanceService;
import cn.cesgroup.cesweb.monitor.trans.model.LoadBalanceInfo;
import cn.cesgroup.cesweb.monitor.trans.netty.service.IActionService;

/**
 * 添加负载模块信息
 *
 * @author LCN on 2017/11/11
 */
@Service(value = "plb")
public class ActionPLBServiceImpl implements IActionService {

	@Autowired
	private LoadBalanceService loadBalanceService;

	@Override
	public String execute(String channelAddress, String key, JSONObject params) {

		String groupId = params.getString("g");
		String k = params.getString("k");
		String data = params.getString("d");

		LoadBalanceInfo loadBalanceInfo = new LoadBalanceInfo();
		loadBalanceInfo.setData(data);
		loadBalanceInfo.setKey(k);
		loadBalanceInfo.setGroupId(groupId);
		boolean ok = loadBalanceService.put(loadBalanceInfo);

		return ok ? "1" : "0";
	}

}
