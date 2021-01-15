/**
 * <p>Copyright:Copyright(c) 2020</p>
 * <p>Company:Professional</p>
 * <p>Package:com.alibaba.csp.sentinel.dashboard.rule.nacos</p>
 * <p>File:FlowRuleNacosPublisher.java</p>
 * <p>类更新历史信息</p>
 * @todo chanlong(陈龙) 创建于 Jul 9, 2020 10:59:07 AM
 */
package com.alibaba.csp.sentinel.dashboard.rule.nacos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.FlowRuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.DynamicRulePublisher;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.csp.sentinel.util.AssertUtil;
import com.alibaba.nacos.api.config.ConfigService;

/**
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 9, 2020 10:59:07 AM
 * @version 1.0.2020
 */
@Component("flowRuleNacosPublisher")
public class FlowRuleNacosPublisher implements DynamicRulePublisher<List<FlowRuleEntity>> {

	public static final String FLOW_DATA_ID_POSTFIX = "-sentinel";
	public static final String GROUP_ID = "DEFAULT_GROUP";

	@Override
	public void publish(String app, List<FlowRuleEntity> rules) throws Exception {
		AssertUtil.notEmpty(app, "app name cannot be empty");
		if (rules == null) {
			return;
		}
		configService.publishConfig(app + FLOW_DATA_ID_POSTFIX, GROUP_ID, converter.convert(rules));
	}
	
	@Autowired
	private ConfigService configService;
	
	@Autowired
	private Converter<List<FlowRuleEntity>, String> converter;
}
