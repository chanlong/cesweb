/*
 *    Copyright (c) 2018-2025, lengleng All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the pig4cloud.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: lengleng (wangiegie@gmail.com)
 */

package cn.cesgroup.cesweb.cloud.router.support;

import org.springframework.context.ApplicationEvent;

/**
 * 路由初始化事件
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 7, 2020 2:42:55 PM
 * @version 1.0.2020
 */
public class DynamicRouteInitEvent extends ApplicationEvent {

	/** serialVersionUID(long):. */
	private static final long serialVersionUID = 1L;

	public DynamicRouteInitEvent(Object source) {
		super(source);
	}

}
