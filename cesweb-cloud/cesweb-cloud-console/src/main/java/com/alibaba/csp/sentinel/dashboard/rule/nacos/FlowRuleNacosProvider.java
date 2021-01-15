/**
 * <p>Copyright:Copyright(c) 2020</p>
 * <p>Company:Professional</p>
 * <p>Package:com.alibaba.csp.sentinel.dashboard.rule.nacos.provider</p>
 * <p>File:FlowRuleNacosProvider.java</p>
 * <p>类更新历史信息</p>
 * @todo chanlong(陈龙) 创建于 Jul 9, 2020 10:52:18 AM
 */
package com.alibaba.csp.sentinel.dashboard.rule.nacos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.FlowRuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.DynamicRuleProvider;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.csp.sentinel.util.StringUtil;
import com.alibaba.nacos.api.config.ConfigService;

/**
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 9, 2020 10:52:18 AM
 * @version 1.0.2020
 */
@Component("flowRuleNacosProvider")
public class FlowRuleNacosProvider implements DynamicRuleProvider<List<FlowRuleEntity>> {

	public static final String FLOW_DATA_ID_POSTFIX = "-sentinel";
	public static final String GROUP_ID = "DEFAULT_GROUP";

	@Override
	public List<FlowRuleEntity> getRules(String appName) throws Exception {
		String rules = configService.getConfig(appName + FLOW_DATA_ID_POSTFIX, GROUP_ID, 3000);
		if (StringUtil.isEmpty(rules)) {
			return new ArrayList<>();
		}
		return converter.convert(rules);
	}
	
	@Autowired
	private ConfigService configService;
	
	@Autowired
	private Converter<String, List<FlowRuleEntity>> converter;
}
