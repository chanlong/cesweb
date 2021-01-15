package cn.cesgroup.cesweb.common.sequence.range.impl;

import cn.cesgroup.cesweb.common.sequence.range.SequenceName;
import lombok.AllArgsConstructor;

/**
 * 根据传入生成名称
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 10, 2020 4:48:47 PM
 * @version 1.0.2020
 */
@AllArgsConstructor
public class DefaultSequenceName implements SequenceName {

	private String name;

	/**
	 * 生成空间名称
	 */
	@Override
	public String create() {
		return name;
	}

}
