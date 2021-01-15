package cn.cesgroup.cesweb.monitor.trans.netty.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import cn.cesgroup.cesweb.monitor.trans.config.ConfigReader;
import cn.cesgroup.cesweb.monitor.trans.netty.service.IActionService;

/**
 * 心跳包
 *
 * @author LCN on 2017/11/11
 */
@Service(value = "h")
public class ActionHServiceImpl implements IActionService {

	@Autowired
	private ConfigReader configReader;

	@Override
	public String execute(String channelAddress, String key, JSONObject params) {
		return String.valueOf(configReader.getTransactionNettyDelayTime());
	}

}
