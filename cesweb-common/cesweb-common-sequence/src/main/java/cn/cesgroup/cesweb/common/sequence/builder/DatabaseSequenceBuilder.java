package cn.cesgroup.cesweb.common.sequence.builder;

import javax.sql.DataSource;

import cn.cesgroup.cesweb.common.sequence.generate.Sequence;
import cn.cesgroup.cesweb.common.sequence.generate.impl.DefaultRangeSequence;
import cn.cesgroup.cesweb.common.sequence.range.SequenceName;
import cn.cesgroup.cesweb.common.sequence.range.impl.DatabaseSequenceRangeManager;

/**
 * 基于DB取步长，序列号生成构建器
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 10, 2020 4:33:25 PM
 * @version 1.0.2020
 */
public class DatabaseSequenceBuilder implements SequenceBuilder {

	/**
	 * 数据库数据源[必选]
	 */
	private DataSource dataSource;

	/**
	 * 业务名称[必选]
	 */
	private SequenceName sequenceName;

	/**
	 * 存放序列号步长的表[可选：默认：sequence]
	 */
	private String tableName = "sequence";

	/**
	 * 并发是数据使用了乐观策略，这个是失败重试的次数[可选：默认：100]
	 */
	private int retryTimes = 100;

	/**
	 * 获取range步长[可选：默认：1000]
	 */
	private int step = 1000;

	/**
	 * 序列号分配起始值[可选：默认：0]
	 */
	private long stepStart = 0;

	public static DatabaseSequenceBuilder create() {
		DatabaseSequenceBuilder builder = new DatabaseSequenceBuilder();
		return builder;
	}

	@Override
	public Sequence build() {
		// 利用DB获取区间管理器
		DatabaseSequenceRangeManager databaseSequenceRangeManager = new DatabaseSequenceRangeManager();
		databaseSequenceRangeManager.setDataSource(this.dataSource);
		databaseSequenceRangeManager.setTableName(this.tableName);
		databaseSequenceRangeManager.setRetryTimes(this.retryTimes);
		databaseSequenceRangeManager.setStep(this.step);
		databaseSequenceRangeManager.setStepStart(stepStart);
		databaseSequenceRangeManager.init();
		// 构建序列号生成器
		DefaultRangeSequence sequence = new DefaultRangeSequence();
		sequence.setName(this.sequenceName);
		sequence.setSeqRangeMgr(databaseSequenceRangeManager);
		return sequence;
	}

	public DatabaseSequenceBuilder dataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		return this;
	}

	public DatabaseSequenceBuilder tableName(String tableName) {
		this.tableName = tableName;
		return this;
	}

	public DatabaseSequenceBuilder retryTimes(int retryTimes) {
		this.retryTimes = retryTimes;
		return this;
	}

	public DatabaseSequenceBuilder step(int step) {
		this.step = step;
		return this;
	}

	public DatabaseSequenceBuilder sequenceName(SequenceName sequenceName) {
		this.sequenceName = sequenceName;
		return this;
	}

	public DatabaseSequenceBuilder stepStart(long stepStart) {
		this.stepStart = stepStart;
		return this;
	}

}
