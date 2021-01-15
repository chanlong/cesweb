package cn.cesgroup.cesweb.common.security.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 服务调用鉴权注解
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 10, 2020 2:18:48 PM
 * @version 1.0.2020
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Inner {

	/**
	 * 是否AOP统一处理
	 * @return false, true
	 */
	boolean value() default true;

	/**
	 * 需要特殊判空的字段(预留)
	 * @return {}
	 */
	String[] field() default {};

}
