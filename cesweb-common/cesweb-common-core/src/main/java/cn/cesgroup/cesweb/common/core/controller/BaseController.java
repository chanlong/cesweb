/**
 * <p>Copyright:Copyright(c) 2020</p>
 * <p>Company:Professional</p>
 * <p>Package:cn.cesgroup.cesweb.common.core.controller</p>
 * <p>File:BaseController.java</p>
 * <p>类更新历史信息</p>
 * @todo chanlong(陈龙) 创建于 Aug 27, 2020 8:44:26 AM
 */
package cn.cesgroup.cesweb.common.core.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.Getter;

/**
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Aug 27, 2020 8:44:26 AM
 * @version 1.0.2020
 */
@Getter
public abstract class BaseController {

	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private HttpServletResponse resposne;
}
