package cn.cesgroup.cesweb.common.sequence.generate.impl;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import cn.cesgroup.cesweb.common.sequence.exception.SequenceException;
import cn.cesgroup.cesweb.common.sequence.generate.RangeSequence;
import cn.cesgroup.cesweb.common.sequence.range.SequenceName;
import cn.cesgroup.cesweb.common.sequence.range.SequenceRange;
import cn.cesgroup.cesweb.common.sequence.range.SequenceRangeManager;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;

/**
 * 序列号区间生成器接口默认实现
 * <p>描述: 根据 sequence name 自增</p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 10, 2020 5:19:25 PM
 * @version 1.0.2020
 */
public class DefaultRangeSequence implements RangeSequence {

	/**
	 * 获取区间是加一把独占锁防止资源冲突
	 */
	private final Lock lock = new ReentrantLock();

	/**
	 * 序列号区间管理器
	 */
	private SequenceRangeManager sequenceRangeManager;

	/**
	 * 当前序列号区间
	 */
	private volatile SequenceRange currentRange;

	private static Map<String, SequenceRange> seqRangeMap = new ConcurrentHashMap<>(8);

	/**
	 * 需要获取区间的业务名称
	 */
	private SequenceName sequenceName;

	@Override
	public long nextValue() throws SequenceException {
		String name = sequenceName.create();

		currentRange = seqRangeMap.get(name);
		// 当前区间不存在，重新获取一个区间
		if (null == currentRange) {
			lock.lock();
			try {
				if (null == currentRange) {
					currentRange = sequenceRangeManager.nextRange(name);
					seqRangeMap.put(name, currentRange);
				}
			}
			finally {
				lock.unlock();
			}
		}

		// 当value值为-1时，表明区间的序列号已经分配完，需要重新获取区间
		long value = currentRange.getAndIncrement();
		if (value == -1) {
			lock.lock();
			try {
				for (;;) {
					if (currentRange.isOver()) {
						currentRange = sequenceRangeManager.nextRange(name);
					}

					value = currentRange.getAndIncrement();
					if (value == -1) {
						continue;
					}

					break;
				}
			}
			finally {
				lock.unlock();
			}
		}

		if (value < 0) {
			throw new SequenceException("Sequence value overflow, value = " + value);
		}

		return value;
	}

	/**
	 * 下一个生成序号（带格式）
	 * @throws SequenceException
	 */
	@Override
	public String nextNo() throws SequenceException {
		return String.format("%s%05d", DateUtil.format(new Date(), DatePattern.PURE_DATE_FORMAT), nextValue());
	}

	@Override
	public void setSeqRangeMgr(SequenceRangeManager sequenceRangeManager) {
		this.sequenceRangeManager = sequenceRangeManager;
	}

	@Override
	public void setName(SequenceName name) {
		this.sequenceName = name;
	}

}
