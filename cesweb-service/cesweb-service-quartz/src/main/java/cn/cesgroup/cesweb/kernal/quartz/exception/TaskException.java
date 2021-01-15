package cn.cesgroup.cesweb.kernal.quartz.exception;

/**
 * 定时任务异常
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Aug 19, 2020 10:48:47 AM
 * @version 1.0.2020
 */
public class TaskException extends Exception {

	/** serialVersionUID(long):. */
	private static final long serialVersionUID = 1L;

	public TaskException() {
		super();
	}

	public TaskException(String msg) {
		super(msg);
	}

}
