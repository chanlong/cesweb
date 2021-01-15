package cn.cesgroup.cesweb.monitor.trans.listener.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.cesgroup.cesweb.monitor.trans.config.ConfigReader;
import cn.cesgroup.cesweb.monitor.trans.framework.utils.Constants;
import cn.cesgroup.cesweb.monitor.trans.listener.service.InitService;
import cn.cesgroup.cesweb.monitor.trans.netty.service.NettyServerService;

/**
 * @author LCN on 2017/7/4.
 */
@Service
public class InitServiceImpl implements InitService {

	@Autowired
	private NettyServerService nettyServerService;

	@Autowired
	private ConfigReader configReader;

	@Override
	public void start() {
		/** 加载本地服务信息 **/
		Constants.socketPort = configReader.getSocketPort();
		Constants.maxConnection = configReader.getSocketMaxConnection();
		nettyServerService.start();
	}

	@Override
	public void close() {
		nettyServerService.close();
	}

}
