package cn.cesgroup.cesweb.kernal.quartz.task;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.cesgroup.cesweb.common.core.util.Response;
import cn.cesgroup.cesweb.common.security.annotation.Inner;
import lombok.extern.slf4j.Slf4j;

/**
 * 用于测试REST风格调用的demo
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Aug 19, 2020 10:50:50 AM
 * @version 1.0.2020
 */
@Slf4j
@RestController
@RequestMapping("/inner-job")
public class RestTaskDemo {

	/**
	 * 测试REST风格调用定时任务的演示方法
	 */
	@Inner(value = false)
	@GetMapping("/{param}")
	public Response<?> demoMethod(@PathVariable("param") String param) {
		log.info("测试于:{}，传入参数{}", LocalDateTime.now(), param);
		return Response.ok();
	}

}
