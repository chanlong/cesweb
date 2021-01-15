package cn.cesgroup.cesweb.monitor.trans.api.service.impl;

import cn.cesgroup.cesweb.monitor.trans.api.service.ApiModelService;
import cn.cesgroup.cesweb.monitor.trans.manager.ModelInfoManager;
import cn.cesgroup.cesweb.monitor.trans.model.ModelInfo;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LCN on 2017/11/13
 * @author LCN
 */
@Service
public class ApiModelServiceImpl implements ApiModelService {

	@Override
	public List<ModelInfo> onlines() {
		return ModelInfoManager.getInstance().getOnlines();
	}

}
