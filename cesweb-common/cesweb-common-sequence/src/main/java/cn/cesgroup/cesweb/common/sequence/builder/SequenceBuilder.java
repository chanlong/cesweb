package cn.cesgroup.cesweb.common.sequence.builder;

import cn.cesgroup.cesweb.common.sequence.generate.Sequence;

/**
 * 序列号生成构建器
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 10, 2020 4:34:35 PM
 * @version 1.0.2020
 */
public interface SequenceBuilder {

	/**
	 * 构建一个序列号生成器
	 * @return 序列号生成器
	 */
	Sequence build();

}
