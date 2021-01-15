package cn.cesgroup.cesweb.common.log.annotation;

import java.lang.annotation.*;

/**
 * 操作日志注解
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 10, 2020 4:12:58 PM
 * @version 1.0.2020
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {

	/**
	 * 描述
	 * @return {String}
	 */
	String value();

}
