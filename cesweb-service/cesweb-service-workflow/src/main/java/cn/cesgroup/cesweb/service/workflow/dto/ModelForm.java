package cn.cesgroup.cesweb.service.workflow.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

/**
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Aug 21, 2020 12:25:31 PM
 * @version 1.0.2020
 */
@Data
public class ModelForm {

	@NotBlank(message = "标识不能为空")
	private String key;
	
	@NotBlank(message = "名称不能为空")
	private String name;

	@NotBlank(message = "描述不能为空")
	private String desc;
	
	@NotBlank(message = "分类不能为空")
	private String category;

}
