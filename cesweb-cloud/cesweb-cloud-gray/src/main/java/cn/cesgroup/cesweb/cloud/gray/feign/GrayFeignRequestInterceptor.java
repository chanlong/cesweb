package cn.cesgroup.cesweb.cloud.gray.feign;

import cn.cesgroup.cesweb.common.core.constant.CommonConstants;
import cn.cesgroup.cesweb.common.core.util.WebUtils;
import cn.hutool.core.util.StrUtil;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;

/**
 * feign 请求VERSION 传递
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 10, 2020 3:49:47 PM
 * @version 1.0.2020
 */
@Slf4j
public class GrayFeignRequestInterceptor implements RequestInterceptor {

	/**
	 * Called for every request. Add data using methods on the supplied
	 * {@link RequestTemplate}.
	 * @param template
	 */
	@Override
	public void apply(RequestTemplate template) {
		String reqVersion = WebUtils.getRequest() != null ? WebUtils.getRequest().getHeader(CommonConstants.VERSION) : null;

		if (StrUtil.isNotBlank(reqVersion)) {
			log.debug("feign gray add header version :{}", reqVersion);
			template.header(CommonConstants.VERSION, reqVersion);
		}
	}

}
