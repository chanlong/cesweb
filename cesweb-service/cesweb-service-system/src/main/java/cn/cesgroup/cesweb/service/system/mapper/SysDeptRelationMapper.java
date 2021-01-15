package cn.cesgroup.cesweb.service.system.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import cn.cesgroup.cesweb.api.system.entity.SysDeptRelation;

/**
 * 部门关系 Mapper 接口
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 13, 2020 2:55:22 PM
 * @version 1.0.2020
 */
@Mapper
public interface SysDeptRelationMapper extends BaseMapper<SysDeptRelation> {

	/**
	 * 删除部门关系表数据
	 * @param id 部门ID
	 */
	void deleteDeptRelationsById(String id);

	/**
	 * 更改部分关系表数据
	 * @param deptRelation
	 */
	void updateDeptRelations(SysDeptRelation deptRelation);

}
