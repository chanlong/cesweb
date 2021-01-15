package cn.cesgroup.cesweb.common.job.properties;

import lombok.Data;

/**
 * xxl 管控台相关属性
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 10, 2020 4:01:26 PM
 * @version 1.0.2020
 */
@Data
public class XxlAdminProperties {

	/**
	 * 调度中心部署跟地址 [选填]：如调度中心集群部署存在多个地址则用逗号分隔。 执行器将会使用该地址进行"执行器心跳注册"和"任务结果回调"；为空则关闭自动注册；
	 */
	private String addresses = "http://127.0.0.1:8000/xxl-job-admin";

}
