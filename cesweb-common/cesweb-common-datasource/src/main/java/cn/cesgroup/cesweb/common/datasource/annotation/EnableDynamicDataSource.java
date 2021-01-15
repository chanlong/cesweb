package cn.cesgroup.cesweb.common.datasource.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Import;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;

import cn.cesgroup.cesweb.common.datasource.config.DynamicDataSourceAutoConfiguration;

/**
 * 开启动态数据源
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 13, 2020 5:25:18 PM
 * @version 1.0.2020
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@EnableAutoConfiguration(exclude = { DruidDataSourceAutoConfigure.class })
@Import(DynamicDataSourceAutoConfiguration.class)
public @interface EnableDynamicDataSource {

}
