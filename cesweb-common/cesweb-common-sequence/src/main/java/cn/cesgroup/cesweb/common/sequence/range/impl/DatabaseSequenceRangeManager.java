package cn.cesgroup.cesweb.common.sequence.range.impl;

import javax.sql.DataSource;

import cn.cesgroup.cesweb.common.sequence.exception.SequenceException;
import cn.cesgroup.cesweb.common.sequence.range.SequenceRange;
import cn.cesgroup.cesweb.common.sequence.range.SequenceRangeManager;

/**
 * DB区间管理器
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 10, 2020 4:50:46 PM
 * @version 1.0.2020
 */
public class DatabaseSequenceRangeManager implements SequenceRangeManager {

	/**
	 * 区间步长
	 */
	private int step = 1000;

	/**
	 * 区间起始位置，真实从stepStart+1开始
	 */
	private long stepStart = 0;

	/**
	 * 获取区间失败重试次数
	 */
	private int retryTimes = 100;

	/**
	 * DB来源
	 */
	private DataSource dataSource;

	/**
	 * 表名，默认range
	 */
	private String tableName = "range";

	@Override
	public SequenceRange nextRange(String name) throws SequenceException {
		if (isEmpty(name)) {
			throw new SecurityException("[DbSeqRangeMgr-nextRange] name is empty.");
		}

		Long oldValue;
		Long newValue;

		for (int i = 0; i < getRetryTimes(); i++) {
			oldValue = BaseDatabaseHelper.selectRange(getDataSource(), getRealTableName(), name, getStepStart());

			if (null == oldValue) {
				// 区间不存在，重试
				continue;
			}

			newValue = oldValue + getStep();

			if (BaseDatabaseHelper.updateRange(getDataSource(), getRealTableName(), newValue, oldValue, name)) {
				return new SequenceRange(oldValue + 1, newValue);
			}
			// else 失败重试
		}

		throw new SequenceException("Retried too many times, retryTimes = " + getRetryTimes());
	}

	@Override
	public void init() {
		checkParam();
		BaseDatabaseHelper.createTable(getDataSource(), getRealTableName());
	}

	private boolean isEmpty(String str) {
		return null == str || str.trim().length() == 0;
	}

	private String getRealTableName() {
		return getTableName();
	}

	private void checkParam() {
		if (step <= 0) {
			throw new SecurityException("[DbSeqRangeMgr-checkParam] step must greater than 0.");
		}
		if (stepStart < 0) {
			throw new SecurityException("[DbSeqRangeMgr-setStepStart] stepStart < 0.");
		}
		if (retryTimes <= 0) {
			throw new SecurityException("[DbSeqRangeMgr-setRetryTimes] retryTimes must greater than 0.");
		}
		if (null == dataSource) {
			throw new SecurityException("[DbSeqRangeMgr-setDataSource] dataSource is null.");
		}
		if (isEmpty(tableName)) {
			throw new SecurityException("[DbSeqRangeMgr-setTableName] tableName is empty.");
		}
	}

	//////// getter and setter

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	public long getStepStart() {
		return stepStart;
	}

	public void setStepStart(long stepStart) {
		this.stepStart = stepStart;
	}

	public int getRetryTimes() {
		return retryTimes;
	}

	public void setRetryTimes(int retryTimes) {
		this.retryTimes = retryTimes;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

}
