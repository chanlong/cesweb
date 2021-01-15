package cn.cesgroup.cesweb.api.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 路由信息表
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 10, 2020 10:10:13 AM
 * @version 1.0.2020
 */
@Data
@ApiModel(value = "网关路由信息")
@EqualsAndHashCode(callSuper = true)
public class SysRouteConf extends Model<SysRouteConf> {

	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@TableId(type = IdType.ASSIGN_UUID)
	@ApiModelProperty(value = "主键")
	private String id;

	/**
	 * 路由ID
	 */
	@ApiModelProperty(value = "路由id")
	private String routeId;

	/**
	 * 路由名称
	 */
	@ApiModelProperty(value = "路由名称")
	private String routeName;

	/**
	 * 断言
	 */
	@ApiModelProperty(value = "断言")
	private String predicates;

	/**
	 * 过滤器
	 */
	@ApiModelProperty(value = "过滤器")
	private String filters;

	/**
	 * uri
	 */
	@ApiModelProperty(value = "请求uri")
	private String uri;

	/**
	 * 排序
	 */
	@TableField(value = "`order`")
	@ApiModelProperty(value = "排序值")
	private Integer order;

	/**
	 * 创建时间
	 */
	@ApiModelProperty(value = "创建时间")
	private LocalDateTime createTime;

	/**
	 * 修改时间
	 */
	@ApiModelProperty(value = "修改时间")
	private LocalDateTime updateTime;

	/**
	 * 删除标识（0-正常,1-删除）
	 */
	@TableLogic
	@ApiModelProperty(value = "删除标记,1:已删除,0:正常")
	private String delFlag;

}
