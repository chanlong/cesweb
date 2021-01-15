package cn.cesgroup.cesweb.common.security.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import cn.cesgroup.cesweb.common.security.component.ResourceServerAutoConfiguration;
import cn.cesgroup.cesweb.common.security.component.SecurityBeanDefinitionRegistrar;

/**
 * 扩展资源服务注解
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 10, 2020 2:18:25 PM
 * @version 1.0.2020
 */
@Documented
@Inherited
@org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Import({ ResourceServerAutoConfiguration.class, SecurityBeanDefinitionRegistrar.class })
public @interface EnableResourceServer {

}
