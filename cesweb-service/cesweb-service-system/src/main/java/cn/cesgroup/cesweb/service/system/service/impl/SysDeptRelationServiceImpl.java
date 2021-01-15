package cn.cesgroup.cesweb.service.system.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cn.cesgroup.cesweb.api.system.entity.SysDept;
import cn.cesgroup.cesweb.api.system.entity.SysDeptRelation;
import cn.cesgroup.cesweb.service.system.mapper.SysDeptRelationMapper;
import cn.cesgroup.cesweb.service.system.service.SysDeptRelationService;
import cn.hutool.core.collection.CollUtil;
import lombok.AllArgsConstructor;

/**
 * 服务实现类
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 13, 2020 3:53:31 PM
 * @version 1.0.2020
 */
@Service
@AllArgsConstructor
public class SysDeptRelationServiceImpl extends ServiceImpl<SysDeptRelationMapper, SysDeptRelation> implements SysDeptRelationService {

	private final SysDeptRelationMapper sysDeptRelationMapper;

	/**
	 * 维护部门关系
	 * @param sysDept 部门
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void insertDeptRelation(SysDept sysDept) {
		// 增加部门关系表
		List<SysDeptRelation> relationList = sysDeptRelationMapper.selectList(
				Wrappers.<SysDeptRelation>query().lambda().eq(SysDeptRelation::getDescendant, sysDept.getParentId()))
				.stream().map(relation -> {
					relation.setDescendant(sysDept.getDeptId());
					return relation;
				}).collect(Collectors.toList());
		if (CollUtil.isNotEmpty(relationList)) {
			this.saveBatch(relationList);
		}

		// 自己也要维护到关系表中
		SysDeptRelation own = new SysDeptRelation();
		own.setDescendant(sysDept.getDeptId());
		own.setAncestor(sysDept.getDeptId());
		sysDeptRelationMapper.insert(own);
	}

	/**
	 * 通过ID删除部门关系
	 * @param id
	 */
	@Override
	public void deleteAllDeptRealtion(String id) {
		baseMapper.deleteDeptRelationsById(id);
	}

	/**
	 * 更新部门关系
	 * @param relation
	 */
	@Override
	public void updateDeptRealtion(SysDeptRelation relation) {
		baseMapper.updateDeptRelations(relation);
	}

}
