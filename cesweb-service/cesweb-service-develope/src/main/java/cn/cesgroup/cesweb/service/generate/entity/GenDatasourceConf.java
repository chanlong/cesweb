package cn.cesgroup.cesweb.service.generate.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 数据源表
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 13, 2020 12:11:34 PM
 * @version 1.0.2020
 */
@Data
@TableName("gen_datasource_conf")
@EqualsAndHashCode(callSuper = true)
public class GenDatasourceConf extends Model<GenDatasourceConf> {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private String id;

	/**
	 * 名称
	 */
	private String name;

	/**
	 * jdbcurl
	 */
	private String url;

	/**
	 * 用户名
	 */
	private String username;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 创建时间
	 */
	private LocalDateTime createDate;

	/**
	 * 更新
	 */
	private LocalDateTime updateDate;

	/**
	 * 删除标记
	 */
	@TableLogic
	private String delFlag;

	/**
	 * 租户ID
	 */
	private Integer tenantId;

}
