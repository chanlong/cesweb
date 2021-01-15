package cn.cesgroup.cesweb.monitor.admin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import de.codecentric.boot.admin.server.config.AdminServerProperties;
import lombok.SneakyThrows;

/**
 * WebSecurityConfigurer
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 8, 2020 9:15:15 AM
 * @version 1.0.2020
 */
@Configuration
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

	private final String adminContextPath;

	public WebSecurityConfigurer(AdminServerProperties adminServerProperties) {
		this.adminContextPath = adminServerProperties.getContextPath();
	}

	@Override
	@SneakyThrows
	protected void configure(HttpSecurity http) {
		SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
		successHandler.setTargetUrlParameter("redirectTo");
		successHandler.setDefaultTargetUrl(adminContextPath + "/");

		http.headers()
			// 允许iframe加载
			.frameOptions().disable()
			// 设置忽略的请求
			.and().authorizeRequests()
				  .antMatchers(adminContextPath + "/login", 
						  	   adminContextPath + "/assets/**", 
						  	   adminContextPath + "/actuator/**", 
						  	   adminContextPath + "/redis/info")
				  .permitAll().anyRequest().authenticated()
			// 设置login
			.and().formLogin().loginPage(adminContextPath + "/login").successHandler(successHandler)
			// 设置logout
			.and().logout().logoutUrl(adminContextPath + "/logout")
			// 启用basic认证，SpringBootAdmin客户端使用的是basic认证
			.and().httpBasic()
			// 关闭csrf
			.and().csrf().disable();
	}

}
