/**
 * <p>Copyright:Copyright(c) 2020</p>
 * <p>Company:Professional</p>
 * <p>Package:cn.cesgroup.cesweb.common.core.util</p>
 * <p>File:FieldUtil.java</p>
 * <p>类更新历史信息</p>
 * @todo chanlong(陈龙) 创建于 Aug 21, 2020 10:43:11 AM
 */
package cn.cesgroup.cesweb.common.core.util;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.reflect.FieldUtils;

import cn.hutool.core.bean.BeanUtil;

/**
 * 对象字段信息反射工具类
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Aug 21, 2020 10:43:11 AM
 * @version 1.0.2020
 */
public abstract class FieldUtil {

	public static String getFieldNameWithAnnotation(final Class<?> beanClass, final Class<? extends Annotation> annotationClass) {
		Field[] fields = FieldUtils.getFieldsWithAnnotation(beanClass, annotationClass);
		if (ArrayUtils.isNotEmpty(fields)) {
			return fields[0].getName();
		} else {
			return getFieldNameWithAnnotation(beanClass.getSuperclass(), annotationClass);
		}
	}
	
	public static Serializable getPropertyWithAnnotation(final Object bean, final Class<? extends Annotation> annotationClass) {
		final Class<?> beanClass = bean.getClass();
		String fieldName = getFieldNameWithAnnotation(beanClass, annotationClass);
		return (Serializable)BeanUtil.getFieldValue(bean, fieldName);
	}
}
