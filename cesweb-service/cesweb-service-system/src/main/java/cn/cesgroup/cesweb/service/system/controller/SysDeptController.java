package cn.cesgroup.cesweb.service.system.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;

import cn.cesgroup.cesweb.api.system.dto.DeptTree;
import cn.cesgroup.cesweb.api.system.entity.SysDept;
import cn.cesgroup.cesweb.api.system.entity.SysDeptRelation;
import cn.cesgroup.cesweb.common.core.util.Response;
import cn.cesgroup.cesweb.common.log.annotation.SysLog;
import cn.cesgroup.cesweb.service.system.service.SysDeptRelationService;
import cn.cesgroup.cesweb.service.system.service.SysDeptService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;

/**
 * 部门管理 前端控制器
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 13, 2020 4:01:21 PM
 * @version 1.0.2020
 */
@RestController
@AllArgsConstructor
@RequestMapping("/dept")
@Api(value = "dept", tags = "部门管理模块")
public class SysDeptController {

	private final SysDeptRelationService relationService;

	private final SysDeptService sysDeptService;

	/**
	 * 通过ID查询
	 * @param id ID
	 * @return SysDept
	 */
	@GetMapping("/{id}")
	public Response<SysDept> getById(@PathVariable String id) {
		return Response.ok(sysDeptService.getById(id));
	}

	/**
	 * 返回树形菜单集合
	 * @return 树形菜单
	 */
	@GetMapping(value = "/tree")
	public Response<List<DeptTree>> getTree() {
		return Response.ok(sysDeptService.selectTree());
	}

	/**
	 * 添加
	 * @param sysDept 实体
	 * @return success/false
	 */
	@SysLog("添加部门")
	@PostMapping
	@PreAuthorize("@pms.hasPermission('sys_dept_add')")
	public Response<Boolean> save(@Valid @RequestBody SysDept sysDept) {
		return Response.ok(sysDeptService.saveDept(sysDept));
	}

	/**
	 * 删除
	 * @param id ID
	 * @return success/false
	 */
	@SysLog("删除部门")
	@DeleteMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('sys_dept_del')")
	public Response<Boolean> removeById(@PathVariable String id) {
		return Response.ok(sysDeptService.removeDeptById(id));
	}

	/**
	 * 编辑
	 * @param sysDept 实体
	 * @return success/false
	 */
	@SysLog("编辑部门")
	@PutMapping
	@PreAuthorize("@pms.hasPermission('sys_dept_edit')")
	public Response<Boolean> update(@Valid @RequestBody SysDept sysDept) {
		sysDept.setUpdateTime(LocalDateTime.now());
		return Response.ok(sysDeptService.updateDeptById(sysDept));
	}

	/**
	 * 查收子级列表
	 * @return 返回子级
	 */
	@GetMapping(value = "/getDescendantList/{deptId}")
	public Response<List<SysDeptRelation>> getDescendantList(@PathVariable String deptId) {
		return Response.ok(relationService.list(Wrappers.<SysDeptRelation>lambdaQuery().eq(SysDeptRelation::getAncestor, deptId)));
	}

}
