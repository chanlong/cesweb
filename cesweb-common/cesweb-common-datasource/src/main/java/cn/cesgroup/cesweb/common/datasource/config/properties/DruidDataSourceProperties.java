package cn.cesgroup.cesweb.common.datasource.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 参考DruidDataSourceWrapper
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 13, 2020 5:25:03 PM
 * @version 1.0.2020
 */
@Data
@Component
@ConfigurationProperties("spring.datasource.druid")
public class DruidDataSourceProperties {

	/**
	 * 数据源用户名
	 */
	private String username;

	/**
	 * 数据源密码
	 */
	private String password;

	/**
	 * jdbcurl
	 */
	private String url;

	/**
	 * 数据源驱动
	 */
	private String driverClassName;

	/**
	 * 查询数据源的SQL
	 */
	private String queryDsSql = "select * from gen_datasource_conf where del_flag = 0";

}
