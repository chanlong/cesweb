package cn.cesgroup.cesweb.cloud.sentinel.handle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;

import cn.cesgroup.cesweb.common.core.util.Response;
import cn.hutool.http.ContentType;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 降级 限流策略
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 7, 2020 4:24:47 PM
 * @version 1.0.2020
 */
@Slf4j
public class UrlBlockHandler implements BlockExceptionHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, BlockException e) throws Exception {
		log.error("sentinel 降级 资源名称{}", e.getRule().getResource(), e);

		response.setContentType(ContentType.JSON.toString());
		response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
		response.getWriter().print(JSONUtil.toJsonStr(Response.failed(e.getMessage())));
	}

}
