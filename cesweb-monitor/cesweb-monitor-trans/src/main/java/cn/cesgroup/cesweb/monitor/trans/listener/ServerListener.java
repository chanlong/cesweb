package cn.cesgroup.cesweb.monitor.trans.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.cesgroup.cesweb.monitor.trans.listener.service.InitService;

/**
 * @author LCN on 2017/7/1.
 */

@Component
public class ServerListener implements ServletContextListener {

	@Autowired
	private InitService initService;

	@Override
	public void contextInitialized(ServletContextEvent event) {
		initService.start();
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		initService.close();
	}

}
