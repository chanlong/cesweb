package cn.cesgroup.cesweb.service.system.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import cn.cesgroup.cesweb.api.system.entity.SysOauthClientDetails;

/**
 * 第三方客户端表 Mapper 接口
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 13, 2020 2:57:34 PM
 * @version 1.0.2020
 */
@Mapper
public interface SysOauthClientDetailsMapper extends BaseMapper<SysOauthClientDetails> {

}
