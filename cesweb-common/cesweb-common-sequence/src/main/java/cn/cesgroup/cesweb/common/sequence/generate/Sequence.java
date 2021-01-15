package cn.cesgroup.cesweb.common.sequence.generate;

import cn.cesgroup.cesweb.common.sequence.exception.SequenceException;

/**
 * 序列号生成器接口
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 10, 2020 5:18:22 PM
 * @version 1.0.2020
 */
public interface Sequence {

	/**
	 * 生成下一个序列号
	 * @return 序列号
	 * @throws SequenceException 序列号异常
	 */
	long nextValue() throws SequenceException;

	/**
	 * 下一个生成序号（带格式）
	 * @return 序列号
	 * @throws SequenceException
	 */
	String nextNo() throws SequenceException;

}
