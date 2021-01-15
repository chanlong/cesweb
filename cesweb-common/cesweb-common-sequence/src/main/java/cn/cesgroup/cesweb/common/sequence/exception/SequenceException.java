package cn.cesgroup.cesweb.common.sequence.exception;

/**
 * 序列号生成异常
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 10, 2020 4:36:51 PM
 * @version 1.0.2020
 */
public class SequenceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SequenceException(String message) {
		super(message);
	}

	public SequenceException(Throwable cause) {
		super(cause);
	}

}
