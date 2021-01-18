package cn.cesgroup.cesweb.api.tenant.entity;

import java.time.LocalDate;

import com.baomidou.mybatisplus.annotation.TableName;

import cn.cesgroup.cesweb.common.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 租户信息表
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 10, 2020 10:10:40 AM
 * @version 1.0.2020
 */
@Data
@ApiModel(value = "租户信息")
@TableName("t_mt_tenant")
@EqualsAndHashCode(callSuper = true)
public class Tenant extends BaseEntity<Tenant> {

	private static final long serialVersionUID = 1L;

	/**
	 * 租户名称
	 */
	@ApiModelProperty(value = "租户名称")
	private String name;

	/**
	 * 租户编号
	 */
	@ApiModelProperty(value = "租户编号")
	private String code;
	
	/**
	 * 数据源名称
	 */
	@ApiModelProperty(value = "数据源名称")
	private String dsname;

	/**
	 * 有效期-开始
	 */
	@ApiModelProperty(value = "有效期-开始")
	private LocalDate validityBegin;

	/**
	 * 有效期-结束
	 */
	@ApiModelProperty(value = "有效期-结束")
	private LocalDate validityFinish;

}
