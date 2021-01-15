package cn.cesgroup.cesweb.service.system.service.impl;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cn.cesgroup.cesweb.api.system.dto.DeptTree;
import cn.cesgroup.cesweb.api.system.entity.SysDept;
import cn.cesgroup.cesweb.api.system.entity.SysDeptRelation;
import cn.cesgroup.cesweb.api.system.util.TreeUtil;
import cn.cesgroup.cesweb.common.data.datascope.DataScope;
import cn.cesgroup.cesweb.service.system.mapper.SysDeptMapper;
import cn.cesgroup.cesweb.service.system.service.SysDeptRelationService;
import cn.cesgroup.cesweb.service.system.service.SysDeptService;
import cn.hutool.core.collection.CollUtil;
import lombok.AllArgsConstructor;

/**
 * 部门管理 服务实现类
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 13, 2020 3:50:13 PM
 * @version 1.0.2020
 */
@Service
@AllArgsConstructor
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements SysDeptService {

	private final SysDeptRelationService sysDeptRelationService;

	private final SysDeptMapper deptMapper;

	/**
	 * 添加信息部门
	 * @param dept 部门
	 * @return
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean saveDept(SysDept dept) {
		SysDept sysDept = new SysDept();
		BeanUtils.copyProperties(dept, sysDept);
		this.save(sysDept);
		sysDeptRelationService.insertDeptRelation(sysDept);
		return Boolean.TRUE;
	}

	/**
	 * 删除部门
	 * @param id 部门 ID
	 * @return 成功、失败
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean removeDeptById(String id) {
		// 级联删除部门
		List<String> idList = sysDeptRelationService.list(Wrappers.<SysDeptRelation>query().lambda()
													.eq(SysDeptRelation::getAncestor, id))
													.stream().map(SysDeptRelation::getDescendant)
													.collect(Collectors.toList());

		if (CollUtil.isNotEmpty(idList)) {
			this.removeByIds(idList);
		}

		// 删除部门级联关系
		sysDeptRelationService.deleteAllDeptRealtion(id);
		return Boolean.TRUE;
	}

	/**
	 * 更新部门
	 * @param sysDept 部门信息
	 * @return 成功、失败
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean updateDeptById(SysDept sysDept) {
		// 更新部门状态
		this.updateById(sysDept);
		// 更新部门关系
		SysDeptRelation relation = new SysDeptRelation();
		relation.setAncestor(sysDept.getParentId());
		relation.setDescendant(sysDept.getDeptId());
		sysDeptRelationService.updateDeptRealtion(relation);
		return Boolean.TRUE;
	}

	/**
	 * 查询全部部门树
	 * @return 树
	 */
	@Override
	public List<DeptTree> selectTree() {
		// 查询全部部门
		List<SysDept> deptAllList = deptMapper.selectList(Wrappers.emptyWrapper());
		// 查询数据权限内部门
		List<String> deptOwnIdList = deptMapper.selectListByScope(Wrappers.emptyWrapper(), new DataScope()).stream().map(SysDept::getDeptId).collect(Collectors.toList());

		// 权限内部门
		List<DeptTree> collect = deptAllList.stream().filter(dept -> !dept.getDeptId().equals(dept.getParentId()))
				.sorted(Comparator.comparingInt(SysDept::getSort)).map(dept -> {
					DeptTree node = new DeptTree();
					node.setId(dept.getDeptId());
					node.setParentId(dept.getParentId());
					node.setName(dept.getName());

					// 有权限不返回标识
					if (deptOwnIdList.contains(dept.getDeptId())) {
						node.setIsLock(Boolean.FALSE);
					}
					return node;
				}).collect(Collectors.toList());
		List<DeptTree> tree = TreeUtil.build(collect, "0");
		return tree;
	}

}
