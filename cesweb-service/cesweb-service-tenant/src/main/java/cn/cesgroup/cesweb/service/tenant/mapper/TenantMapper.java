package cn.cesgroup.cesweb.service.tenant.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import cn.cesgroup.cesweb.api.tenant.entity.Tenant;

/**
 * 租户信息表 Mapper 接口
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 13, 2020 2:59:32 PM
 * @version 1.0.2020
 */
@Mapper
public interface TenantMapper extends BaseMapper<Tenant> {

}
