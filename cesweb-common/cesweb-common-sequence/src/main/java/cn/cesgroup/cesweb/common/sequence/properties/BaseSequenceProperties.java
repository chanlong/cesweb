package cn.cesgroup.cesweb.common.sequence.properties;

import lombok.Data;

/**
 * 发号器通用属性
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 10, 2020 4:37:06 PM
 * @version 1.0.2020
 */
@Data
class BaseSequenceProperties {

	/**
	 * 获取range步长[可选，默认：1000]
	 */
	private int step = 1000;

	/**
	 * 序列号分配起始值[可选：默认：0]
	 */
	private long stepStart = 0;

	/**
	 * 业务名称
	 */
	private String bizName = "cesweb";

}
