package cn.cesgroup.cesweb.kernal.quartz.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 任务类型枚举
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Aug 19, 2020 10:15:18 AM
 * @version 1.0.2020
 */
@Getter
@AllArgsConstructor
public enum JobTypeEnum {

	/**
	 * 反射java类
	 */
	JAVA("1", "反射java类"),

	/**
	 * spring bean 的方式
	 */
	SPRING_BEAN("2", "spring bean容器实例"),

	/**
	 * rest 调用
	 */
	REST("3", "rest调用"),

	/**
	 * jar
	 */
	JAR("4", "jar调用");

	/**
	 * 类型
	 */
	private final String type;

	/**
	 * 描述
	 */
	private final String description;

}
