/**
 * <p>Copyright:Copyright(c) 2020</p>
 * <p>Company:Professional</p>
 * <p>Package:cn.cesgroup.cesweb.common.core</p>
 * <p>File:JasyptTest.java</p>
 * <p>类更新历史信息</p>
 * @todo chanlong(陈龙) 创建于 Jul 8, 2020 11:45:22 AM
 */
package cn.cesgroup.cesweb.common.core;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.intf.cli.JasyptPBEStringEncryptionCLI;
import org.junit.Test;
import org.springframework.core.env.StandardEnvironment;

import com.ulisesbocchio.jasyptspringboot.encryptor.DefaultLazyEncryptor;

/**
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 8, 2020 11:45:22 AM
 * @version 1.0.2020
 */
public class JasyptTest {

	@Test
    public void testEnvironmentProperties() {
		System.setProperty("jasypt.encryptor.password", "cesweb");
		System.setProperty("jasypt.encryptor.algorithm", "PBEWithMD5AndDES");
		
		StandardEnvironment env = new StandardEnvironment();
		StringEncryptor enc = new DefaultLazyEncryptor(env);
		System.out.println(enc.encrypt("cesweb"));
		
        JasyptPBEStringEncryptionCLI.main(new String[]{"input=\"cesweb\"","password=cesweb","algorithm=PBEWithMD5AndDES"});
    } 
}
