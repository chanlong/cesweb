package cn.cesgroup.cesweb.service.system.service.impl;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

import cn.cesgroup.cesweb.api.system.entity.SysUser;
import cn.cesgroup.cesweb.common.core.constant.CacheConstants;
import cn.cesgroup.cesweb.common.core.constant.SecurityConstants;
import cn.cesgroup.cesweb.common.core.constant.enums.LoginTypeEnum;
import cn.cesgroup.cesweb.common.core.util.Response;
import cn.cesgroup.cesweb.service.system.mapper.SysUserMapper;
import cn.cesgroup.cesweb.service.system.service.MobileService;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.RandomUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 手机登录相关业务实现
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 13, 2020 3:54:26 PM
 * @version 1.0.2020
 */
@Slf4j
@Service
@AllArgsConstructor
public class MobileServiceImpl implements MobileService {

	private final RedisTemplate<String, String> redisTemplate;

	private final SysUserMapper userMapper;

	/**
	 * 发送手机验证码 TODO: 调用短信网关发送验证码,测试返回前端
	 * @param mobile mobile
	 * @return code
	 */
	@Override
	public Response<Boolean> sendSmsCode(String mobile) {
		List<SysUser> userList = userMapper.selectList(Wrappers.<SysUser>query().lambda().eq(SysUser::getPhone, mobile));

		if (CollUtil.isEmpty(userList)) {
			log.info("手机号未注册:{}", mobile);
			return Response.ok(Boolean.FALSE, "手机号未注册");
		}

		Object codeObj = redisTemplate.opsForValue().get(CacheConstants.DEFAULT_CODE_KEY + LoginTypeEnum.SMS.getType() + StringPool.AT + mobile);

		if (codeObj != null) {
			log.info("手机号验证码未过期:{}，{}", mobile, codeObj);
			return Response.ok(Boolean.FALSE, "验证码发送过频繁");
		}

		String code = RandomUtil.randomNumbers(Integer.parseInt(SecurityConstants.CODE_SIZE));
		log.debug("手机号生成验证码成功:{},{}", mobile, code);
		redisTemplate.opsForValue().set(CacheConstants.DEFAULT_CODE_KEY + LoginTypeEnum.SMS.getType() + StringPool.AT + mobile, code, SecurityConstants.CODE_TIME, TimeUnit.SECONDS);
		return Response.ok(Boolean.TRUE, code);
	}

}
