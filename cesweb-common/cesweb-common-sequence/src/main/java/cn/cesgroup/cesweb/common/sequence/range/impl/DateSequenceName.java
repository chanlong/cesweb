package cn.cesgroup.cesweb.common.sequence.range.impl;

import cn.cesgroup.cesweb.common.sequence.range.SequenceName;
import cn.hutool.core.date.DateUtil;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * 根据时间重置名称
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 10, 2020 4:48:33 PM
 * @version 1.0.2020
 */
@NoArgsConstructor
@AllArgsConstructor
public class DateSequenceName implements SequenceName {

	private String name;

	/**
	 * 生成空间名称
	 */
	@Override
	public String create() {
		return name + DateUtil.today();
	}

}