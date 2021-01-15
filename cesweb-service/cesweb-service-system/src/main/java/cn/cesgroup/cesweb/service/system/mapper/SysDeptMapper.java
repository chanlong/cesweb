package cn.cesgroup.cesweb.service.system.mapper;

import org.apache.ibatis.annotations.Mapper;

import cn.cesgroup.cesweb.api.system.entity.SysDept;
import cn.cesgroup.cesweb.common.data.datascope.DataScopeMapper;

/**
 * 部门管理 Mapper 接口
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 13, 2020 2:55:04 PM
 * @version 1.0.2020
 */
@Mapper
public interface SysDeptMapper extends DataScopeMapper<SysDept> {

}
