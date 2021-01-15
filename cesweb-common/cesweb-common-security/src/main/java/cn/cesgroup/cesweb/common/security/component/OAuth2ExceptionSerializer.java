package cn.cesgroup.cesweb.common.security.component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import cn.cesgroup.cesweb.common.core.constant.CommonConstants;
import cn.cesgroup.cesweb.common.security.exception.OAuth2Exception;
import lombok.SneakyThrows;

/**
 * OAuth2 异常格式化
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 10, 2020 11:14:16 AM
 * @version 1.0.2020
 */
public class OAuth2ExceptionSerializer extends StdSerializer<OAuth2Exception> {

	/** serialVersionUID(long):. */
	private static final long serialVersionUID = 1L;

	public OAuth2ExceptionSerializer() {
		super(OAuth2Exception.class);
	}

	@Override
	@SneakyThrows
	public void serialize(OAuth2Exception value, JsonGenerator gen, SerializerProvider provider) {
		gen.writeStartObject();
		gen.writeObjectField("code", CommonConstants.FAIL);
		gen.writeStringField("msg", value.getMessage());
		gen.writeStringField("data", value.getErrorCode());
		gen.writeEndObject();
	}

}
