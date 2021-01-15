package cn.cesgroup.cesweb.monitor.trans.manager.service;

import cn.cesgroup.cesweb.monitor.trans.netty.model.TxGroup;

/**
 * @author LCN on 2017/6/9.
 */
public interface TxManagerSenderService {

	int confirm(TxGroup group);

	String sendMsg(String model, String msg, int delay);

	String sendCompensateMsg(String model, String groupId, String data, int startState);

}
