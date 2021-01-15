package cn.cesgroup.cesweb.service.system.service;

import com.baomidou.mybatisplus.extension.service.IService;

import cn.cesgroup.cesweb.api.system.entity.SysDept;
import cn.cesgroup.cesweb.api.system.entity.SysDeptRelation;

/**
 * 服务类
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 13, 2020 1:40:32 PM
 * @version 1.0.2020
 */
public interface SysDeptRelationService extends IService<SysDeptRelation> {

	/**
	 * 新建部门关系
	 * @param sysDept 部门
	 */
	void insertDeptRelation(SysDept sysDept);

	/**
	 * 通过ID删除部门关系
	 * @param id
	 */
	void deleteAllDeptRealtion(String id);

	/**
	 * 更新部门关系
	 * @param relation
	 */
	void updateDeptRealtion(SysDeptRelation relation);

}
