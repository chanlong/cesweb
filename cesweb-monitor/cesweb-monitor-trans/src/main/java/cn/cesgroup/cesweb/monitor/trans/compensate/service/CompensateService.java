package cn.cesgroup.cesweb.monitor.trans.compensate.service;

import com.lorne.core.framework.exception.ServiceException;

import cn.cesgroup.cesweb.monitor.trans.compensate.model.TransactionCompensateMsg;
import cn.cesgroup.cesweb.monitor.trans.compensate.model.TxModel;
import cn.cesgroup.cesweb.monitor.trans.model.ModelName;
import cn.cesgroup.cesweb.monitor.trans.netty.model.TxGroup;

import java.util.List;

/**
 * @author LCN on 2017/11/11
 */
public interface CompensateService {

	boolean saveCompensateMsg(TransactionCompensateMsg transactionCompensateMsg);

	List<ModelName> loadModelList();

	List<String> loadCompensateTimes(String model);

	List<TxModel> loadCompensateByModelAndTime(String path);

	void autoCompensate(String compensateKey, TransactionCompensateMsg transactionCompensateMsg);

	boolean executeCompensate(String key) throws ServiceException;

	void reloadCompensate(TxGroup txGroup);

	boolean hasCompensate();

	boolean delCompensate(String path);

	TxGroup getCompensateByGroupId(String groupId);

}
