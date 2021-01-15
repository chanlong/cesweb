package cn.cesgroup.cesweb.common.sequence.range;

import cn.cesgroup.cesweb.common.sequence.exception.SequenceException;

/**
 * 区间管理器
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 10, 2020 4:46:37 PM
 * @version 1.0.2020
 */
public interface SequenceRangeManager {

	/**
	 * 获取指定区间名的下一个区间
	 * @param name 区间名
	 * @return 返回区间
	 * @throws SequenceException 异常
	 */
	SequenceRange nextRange(String name) throws SequenceException;

	/**
	 * 初始化
	 */
	void init();

}
