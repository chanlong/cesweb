package cn.cesgroup.cesweb.monitor.trans;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * tx-manager，进行了代码逻辑和代码规范重构
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author LCN
 * @author chanlong(陈龙)
 * @date Aug 20, 2020 3:06:32 PM
 * @version 1.0.2020
 */
@SpringCloudApplication
public class TxManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TxManagerApplication.class, args);
	}

}
