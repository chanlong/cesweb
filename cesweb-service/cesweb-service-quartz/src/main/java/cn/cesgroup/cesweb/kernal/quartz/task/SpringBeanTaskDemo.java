package cn.cesgroup.cesweb.kernal.quartz.task;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import cn.cesgroup.cesweb.kernal.quartz.constants.QuartzEnum;

import java.time.LocalDateTime;

/**
 * 用于测试Spring Bean调用的demo
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Aug 19, 2020 10:51:26 AM
 * @version 1.0.2020
 */
@Slf4j
@Component("demo")
public class SpringBeanTaskDemo {

	/**
	 * 测试Spring Bean的演示方法
	 */
	@SneakyThrows
	public String demoMethod(String para) {
		log.info("测试于:{}，输入参数{}", LocalDateTime.now(), para);
		return QuartzEnum.JOB_LOG_STATUS_SUCCESS.getType();
	}

}
