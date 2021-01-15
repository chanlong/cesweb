package cn.cesgroup.cesweb.common.sequence.builder;

import cn.cesgroup.cesweb.common.sequence.generate.Sequence;
import cn.cesgroup.cesweb.common.sequence.generate.impl.SnowflakeSequence;

/**
 * 基于雪花算法，基于redis取步长，序列号生成构建器
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 10, 2020 4:35:31 PM
 * @version 1.0.2020
 */
public class SnowflakeSequenceBuilder implements SequenceBuilder {

	/**
	 * 数据中心ID，值的范围在[0,31]之间，一般可以设置机房的IDC[必选]
	 */
	private long dataCenterId;

	/**
	 * 工作机器ID，值的范围在[0,31]之间，一般可以设置机器编号[必选]
	 */
	private long workerId;

	public static SnowflakeSequenceBuilder create() {
		SnowflakeSequenceBuilder builder = new SnowflakeSequenceBuilder();
		return builder;
	}

	@Override
	public Sequence build() {
		SnowflakeSequence sequence = new SnowflakeSequence();
		sequence.setDataCenterId(this.dataCenterId);
		sequence.setWorkerId(this.workerId);
		return sequence;
	}

	public SnowflakeSequenceBuilder dataCenterId(long dataCenterId) {
		this.dataCenterId = dataCenterId;
		return this;
	}

	public SnowflakeSequenceBuilder workerId(long workerId) {
		this.workerId = workerId;
		return this;
	}

}
