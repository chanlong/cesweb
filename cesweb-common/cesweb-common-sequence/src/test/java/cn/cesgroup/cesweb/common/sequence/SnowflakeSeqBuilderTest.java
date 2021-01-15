package cn.cesgroup.cesweb.common.sequence;

import org.junit.Test;

import cn.cesgroup.cesweb.common.sequence.builder.SnowflakeSequenceBuilder;
import cn.cesgroup.cesweb.common.sequence.generate.Sequence;

public class SnowflakeSeqBuilderTest {

	@Test
	public void snowflake() {
		Sequence sequence = SnowflakeSequenceBuilder.create().dataCenterId(0).workerId(0).build();
		System.out.println(sequence.nextValue());
		System.out.println(sequence.nextNo());
	}
}
