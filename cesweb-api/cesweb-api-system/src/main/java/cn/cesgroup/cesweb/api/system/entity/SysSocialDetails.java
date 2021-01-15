package cn.cesgroup.cesweb.api.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * 系统社交登录账号表
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 10, 2020 10:10:26 AM
 * @version 1.0.2020
 */
@Data
@ApiModel(value = "第三方账号信息")
@EqualsAndHashCode(callSuper = true)
public class SysSocialDetails extends Model<SysSocialDetails> {

	private static final long serialVersionUID = 1L;

	/**
	 * 主鍵
	 */
	@TableId(type = IdType.ASSIGN_UUID)
	@ApiModelProperty(value = "主键")
	private String id;

	/**
	 * 类型
	 */
	@NotBlank(message = "类型不能为空")
	@ApiModelProperty(value = "账号类型")
	private String type;

	/**
	 * 描述
	 */
	@ApiModelProperty(value = "描述")
	private String remark;

	/**
	 * appid
	 */
	@NotBlank(message = "账号不能为空")
	@ApiModelProperty(value = "appId")
	private String appId;

	/**
	 * app_secret
	 */
	@NotBlank(message = "密钥不能为空")
	@ApiModelProperty(value = "app secret")
	private String appSecret;

	/**
	 * 回调地址
	 */
	@ApiModelProperty(value = "回调地址")
	private String redirectUrl;

	/**
	 * 创建时间
	 */
	@ApiModelProperty(value = "创建时间")
	private LocalDateTime createTime;

	/**
	 * 更新时间
	 */
	@ApiModelProperty(value = "更新时间")
	private LocalDateTime updateTime;

	/**
	 * 删除标记
	 */
	@TableLogic
	@ApiModelProperty(value = "删除标记,1:已删除,0:正常")
	private String delFlag;

}
