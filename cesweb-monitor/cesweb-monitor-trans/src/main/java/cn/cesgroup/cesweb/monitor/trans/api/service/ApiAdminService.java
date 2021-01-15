package cn.cesgroup.cesweb.monitor.trans.api.service;

import com.lorne.core.framework.exception.ServiceException;

import cn.cesgroup.cesweb.monitor.trans.compensate.model.TxModel;
import cn.cesgroup.cesweb.monitor.trans.model.ModelName;
import cn.cesgroup.cesweb.monitor.trans.model.TxState;

import java.util.List;
import java.util.Map;

/**
 * @author LCN on 2017/11/12
 * @author chanlong
 */
public interface ApiAdminService {

	TxState getState();

	/**
	 * k/v 获取 值封装成map
	 * @return
	 */
	List<Map<String, Object>> getMapState();

	String loadNotifyJson();

	List<ModelName> modelList();

	List<String> modelTimes(String model);

	List<TxModel> modelInfos(String path);

	boolean compensate(String path) throws ServiceException;

	boolean hasCompensate();

	boolean delCompensate(String path);

}
