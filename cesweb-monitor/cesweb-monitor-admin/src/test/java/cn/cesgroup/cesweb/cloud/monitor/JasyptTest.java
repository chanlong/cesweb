/**
 * <p>Copyright:Copyright(c) 2020</p>
 * <p>Company:Professional</p>
 * <p>Package:cn.cesgroup.cesweb.common.core</p>
 * <p>File:JasyptTest.java</p>
 * <p>类更新历史信息</p>
 * @todo chanlong(陈龙) 创建于 Jul 8, 2020 11:45:22 AM
 */
package cn.cesgroup.cesweb.cloud.monitor;

import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.cesgroup.cesweb.monitor.admin.MonitorAdminApplication;

/**
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 8, 2020 11:45:22 AM
 * @version 1.0.2020
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MonitorAdminApplication.class)
public class JasyptTest {

	@Autowired
	private StringEncryptor stringEncryptor;
	
	@Test
	public void testEnvironmentProperties() {
		System.out.println(stringEncryptor.encrypt("cesweb"));
	}
}
