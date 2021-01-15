package cn.cesgroup.cesweb.service.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import cn.cesgroup.cesweb.api.system.entity.SysRole;

/**
 * 角色表 Mapper 接口
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 13, 2020 2:58:24 PM
 * @version 1.0.2020
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {

	/**
	 * 通过用户ID，查询角色信息
	 * @param userId
	 */
	List<SysRole> listRolesByUserId(String userId);

}
