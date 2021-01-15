package cn.cesgroup.cesweb.common.core.util;

import java.io.Serializable;

import cn.cesgroup.cesweb.common.core.constant.CommonConstants;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 响应信息主体
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 7, 2020 2:14:47 PM
 * @version 1.0.2020
 */
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel("响应信息主体")
public class Response<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	@ApiModelProperty("返回标记：成功标记=0，失败标记=1")
	private int code;

	@Getter
	@Setter
	@ApiModelProperty("返回信息")
	private String msg;

	@Getter
	@Setter
	@ApiModelProperty("数据")
	private T data;

	public static <T> Response<T> ok() {
		return restResult(null, CommonConstants.SUCCESS, null);
	}

	public static <T> Response<T> ok(T data) {
		return restResult(data, CommonConstants.SUCCESS, null);
	}

	public static <T> Response<T> ok(T data, String msg) {
		return restResult(data, CommonConstants.SUCCESS, msg);
	}

	public static <T> Response<T> failed() {
		return restResult(null, CommonConstants.FAIL, null);
	}

	public static <T> Response<T> failed(String msg) {
		return restResult(null, CommonConstants.FAIL, msg);
	}

	public static <T> Response<T> failed(T data) {
		return restResult(data, CommonConstants.FAIL, null);
	}

	public static <T> Response<T> failed(T data, String msg) {
		return restResult(data, CommonConstants.FAIL, msg);
	}

	private static <T> Response<T> restResult(T data, int code, String msg) {
		Response<T> apiResult = new Response<>();
		apiResult.setCode(code);
		apiResult.setData(data);
		apiResult.setMsg(msg);
		return apiResult;
	}

}
