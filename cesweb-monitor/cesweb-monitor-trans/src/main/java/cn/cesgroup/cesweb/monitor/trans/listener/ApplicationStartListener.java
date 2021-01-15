package cn.cesgroup.cesweb.monitor.trans.listener;

import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import cn.cesgroup.cesweb.monitor.trans.framework.utils.Constants;
import cn.hutool.core.net.NetUtil;

/**
 * @author LCN on 2017/8/7
 */
@Component
public class ApplicationStartListener implements ApplicationListener<WebServerInitializedEvent> {

	@Override
	public void onApplicationEvent(WebServerInitializedEvent event) {
		int serverPort = event.getWebServer().getPort();
		String ip = NetUtil.getLocalhostStr();
		Constants.address = ip + ":" + serverPort;
	}

}
