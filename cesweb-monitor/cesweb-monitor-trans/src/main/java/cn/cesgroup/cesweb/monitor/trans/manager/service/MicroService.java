package cn.cesgroup.cesweb.monitor.trans.manager.service;

import cn.cesgroup.cesweb.monitor.trans.model.TxServer;
import cn.cesgroup.cesweb.monitor.trans.model.TxState;

/**
 * @author LCN on 2017/11/11
 */
public interface MicroService {

	String TMKEY = "tx-manager";

	TxServer getServer();

	TxState getState();

}
