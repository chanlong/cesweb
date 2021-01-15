package cn.cesgroup.cesweb.monitor.trans.api.service.impl;

import cn.cesgroup.cesweb.monitor.trans.api.service.ApiTxManagerService;
import cn.cesgroup.cesweb.monitor.trans.compensate.model.TransactionCompensateMsg;
import cn.cesgroup.cesweb.monitor.trans.compensate.service.CompensateService;
import cn.cesgroup.cesweb.monitor.trans.config.ConfigReader;
import cn.cesgroup.cesweb.monitor.trans.manager.service.MicroService;
import cn.cesgroup.cesweb.monitor.trans.manager.service.TxManagerSenderService;
import cn.cesgroup.cesweb.monitor.trans.manager.service.TxManagerService;
import cn.cesgroup.cesweb.monitor.trans.model.TxServer;
import cn.cesgroup.cesweb.monitor.trans.model.TxState;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author LCN on 2017/7/1.
 * @author LCN
 * @author chanlong
 */
@Service
@AllArgsConstructor
public class ApiTxManagerServiceImpl implements ApiTxManagerService {

	private final TxManagerService managerService;

	private final MicroService eurekaService;

	private final CompensateService compensateService;

	private final TxManagerSenderService txManagerSenderService;

	private final ConfigReader configReader;

	@Override
	public TxServer getServer() {
		return eurekaService.getServer();
	}

	@Override
	public int cleanNotifyTransaction(String groupId, String taskId) {
		return managerService.cleanNotifyTransaction(groupId, taskId);
	}

	@Override
	public boolean sendCompensateMsg(long currentTime, String groupId, String model, String address, String uniqueKey,
			String className, String methodStr, String data, int time, int startError) {
		TransactionCompensateMsg transactionCompensateMsg = new TransactionCompensateMsg(currentTime, groupId, model,
				address, uniqueKey, className, methodStr, data, time, 0, startError);
		return compensateService.saveCompensateMsg(transactionCompensateMsg);
	}

	@Override
	public String sendMsg(String model, String msg) {
		return txManagerSenderService.sendMsg(model, msg, configReader.getTransactionNettyDelayTime());
	}

	@Override
	public TxState getState() {
		return eurekaService.getState();
	}

}
