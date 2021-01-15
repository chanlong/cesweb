package cn.cesgroup.cesweb.common.sequence.builder;

import cn.cesgroup.cesweb.common.sequence.generate.Sequence;
import cn.cesgroup.cesweb.common.sequence.generate.impl.DefaultRangeSequence;
import cn.cesgroup.cesweb.common.sequence.range.SequenceName;
import cn.cesgroup.cesweb.common.sequence.range.impl.RedisSequenceRangeManager;

/**
 * 基于redis取步长，序列号生成构建器
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 10, 2020 4:35:15 PM
 * @version 1.0.2020
 */
public class RedisSequenceBuilder implements SequenceBuilder {

	/**
	 * 连接redis的IP[必选]
	 */
	private String ip;

	/**
	 * 连接redis的port[必选]
	 */
	private int port;

	/**
	 * 业务名称[必选]
	 */
	private SequenceName sequenceName;

	/**
	 * 认证权限，看redis是否配置了需要密码auth[可选]
	 */
	private String auth;

	/**
	 * 获取range步长[可选，默认：1000]
	 */
	private int step = 1000;

	/**
	 * 序列号分配起始值[可选：默认：0]
	 */
	private long stepStart = 0;

	public static RedisSequenceBuilder create() {
		RedisSequenceBuilder builder = new RedisSequenceBuilder();
		return builder;
	}

	@Override
	public Sequence build() {
		// 利用Redis获取区间管理器
		RedisSequenceRangeManager redisSequenceRangeManager = new RedisSequenceRangeManager();
		redisSequenceRangeManager.setIp(this.ip);
		redisSequenceRangeManager.setPort(this.port);
		redisSequenceRangeManager.setAuth(this.auth);
		redisSequenceRangeManager.setStep(this.step);
		redisSequenceRangeManager.setStepStart(stepStart);
		redisSequenceRangeManager.init();
		// 构建序列号生成器
		DefaultRangeSequence sequence = new DefaultRangeSequence();
		sequence.setName(this.sequenceName);
		sequence.setSeqRangeMgr(redisSequenceRangeManager);
		return sequence;
	}

	public RedisSequenceBuilder ip(String ip) {
		this.ip = ip;
		return this;
	}

	public RedisSequenceBuilder port(int port) {
		this.port = port;
		return this;
	}

	public RedisSequenceBuilder auth(String auth) {
		this.auth = auth;
		return this;
	}

	public RedisSequenceBuilder step(int step) {
		this.step = step;
		return this;
	}

	public RedisSequenceBuilder sequenceName(SequenceName sequenceName) {
		this.sequenceName = sequenceName;
		return this;
	}

	public RedisSequenceBuilder stepStart(long stepStart) {
		this.stepStart = stepStart;
		return this;
	}

}
