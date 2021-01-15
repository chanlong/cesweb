package cn.cesgroup.cesweb.common.sequence.generate;

import cn.cesgroup.cesweb.common.sequence.range.SequenceName;
import cn.cesgroup.cesweb.common.sequence.range.SequenceRangeManager;

/**
 * 序列号区间生成器接口
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 10, 2020 5:18:32 PM
 * @version 1.0.2020
 */
public interface RangeSequence extends Sequence {

	/**
	 * 设置区间管理器
	 * @param sequenceRangeManager 区间管理器
	 */
	void setSeqRangeMgr(SequenceRangeManager sequenceRangeManager);

	/**
	 * 设置获取序列号名称
	 * @param name 名称
	 */
	void setName(SequenceName name);

}
