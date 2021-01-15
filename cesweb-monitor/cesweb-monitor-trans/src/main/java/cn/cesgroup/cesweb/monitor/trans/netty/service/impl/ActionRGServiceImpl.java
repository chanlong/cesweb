package cn.cesgroup.cesweb.monitor.trans.netty.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import cn.cesgroup.cesweb.monitor.trans.manager.service.TxManagerService;
import cn.cesgroup.cesweb.monitor.trans.netty.service.IActionService;

/**
 * 强制回滚事务组
 *
 * @author LCN on 2017/11/11
 */
@Service(value = "rg")
public class ActionRGServiceImpl implements IActionService {

	@Autowired
	private TxManagerService txManagerService;

	@Override
	public String execute(String channelAddress, String key, JSONObject params) {
		String res = "";
		String groupId = params.getString("g");
		boolean bs = txManagerService.rollbackTransactionGroup(groupId);
		res = bs ? "1" : "0";
		return res;
	}

}
