package cn.cesgroup.cesweb.service.tenant.service.impl;

/**
 * 租户 服务实现类
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date Jul 13, 2020 3:14:35 PM
 * @version 1.0.2020
 */
//@Service
//@AllArgsConstructor
//public class TenantServiceImpl extends ServiceImpl<TenantMapper, Tenant> implements TenantService {

//	
//	private static final PasswordEncoder ENCODER = new BCryptPasswordEncoder();
//
//	private final SysOauthClientDetailsService clientServices;
//
//	private final SysDeptRelationService deptRelationService;
//
//	private final SysUserRoleService userRoleService;
//
//	private final SysRoleMenuService roleMenuService;
//
//	private final SysDictItemService dictItemService;
//
//	private final SysPublicParamService paramService;
//
//	private final SysUserService userService;
//
//	private final SysRoleService roleService;
//
//	private final SysMenuService menuService;
//
//	private final SysDeptService deptService;
//
//	private final SysDictService dictService;
//
//	/**
//	 * 获取正常状态租户
//	 * <p>
//	 * 1. 状态正常 2. 开始时间小于等于当前时间 3. 结束时间大于等于当前时间
//	 * @return
//	 */
//	@Override
//	@Cacheable(value = CacheConstants.TENANT_DETAILS)
//	public List<Tenant> getNormalTenant() {
//		return baseMapper.selectList(Wrappers.<Tenant>lambdaQuery().eq(Tenant::getStatus, CommonConstants.STATUS_NORMAL));
//	}
//
//	/**
//	 * 保存租户
//	 * <p>
//	 * 1. 保存租户 
//	 * 2. 初始化权限相关表	- sys_user 
//	 * 					- sys_role 
//	 * 					- sys_menu 
//	 * 					- sys_user_role 
//	 * 					- sys_role_menu 
//	 * 					- sys_dict 
//	 * 					- sys_dict_item 
//	 * 					- sys_client_details 
//	 * 					- sys_public_params
//	 * @param Tenant 租户实体
//	 */
//	@Override
//	@Transactional(rollbackFor = Exception.class)
//	@CacheEvict(value = CacheConstants.TENANT_DETAILS)
//	public Boolean saveTenant(Tenant tenant) {
//		this.save(tenant);
//		
//		// 查询系统默认租户配置参数
//		String defaultId = ParamResolver.getStr("TENANT_DEFAULT_ID", "1");
//		String defaultDeptName = ParamResolver.getStr("TENANT_DEFAULT_DEPTNAME", "租户默认部门");
//		String defaultUsername = ParamResolver.getStr("TENANT_DEFAULT_USERNAME", "admin");
//		String defaultPassword = ParamResolver.getStr("TENANT_DEFAULT_PASSWORD", "123456");
//		String defaultRoleCode = ParamResolver.getStr("TENANT_DEFAULT_ROLECODE", "ROLE_ADMIN");
//		String defaultRoleName = ParamResolver.getStr("TENANT_DEFAULT_ROLENAME", "租户默认角色");
//
//		List<SysDict> dictList = new ArrayList<>(32);
//		List<String> dictIdList = new ArrayList<>(32);
//		List<SysDictItem> dictItemList = new ArrayList<>(64);
//		List<SysMenu> menuList = new ArrayList<>(128);
//		List<SysOauthClientDetails> clientDetailsList = new ArrayList<>(16);
//		List<SysPublicParam> publicParamList = new ArrayList<>(64);
//
//		TenantBroker.runAs(defaultId, (id) -> {
//			// 查询系统内置字典
//			dictList.addAll(dictService.list());
//			// 查询系统内置字典项目
//			dictIdList.addAll(dictList.stream().map(SysDict::getId).collect(Collectors.toList()));
//			dictItemList.addAll(dictItemService.list(Wrappers.<SysDictItem>lambdaQuery().in(SysDictItem::getDictId, dictIdList)));
//			// 查询当前租户菜单
//			menuList.addAll(menuService.list());
//			// 查询客户端配置
//			clientDetailsList.addAll(clientServices.list());
//			// 查询系统参数配置
//			publicParamList.addAll(paramService.list());
//		});
//
//		// 保证插入租户为新的租户
//		return TenantBroker.applyAs(tenant.getId(), (id -> {
//
//			// 插入部门
//			SysDept dept = new SysDept();
//			dept.setName(defaultDeptName);
//			dept.setParentId("0");
//			deptService.save(dept);
//			// 维护部门关系
//			deptRelationService.insertDeptRelation(dept);
//			// 构造初始化用户
//			SysUser user = new SysUser();
//			user.setUsername(defaultUsername);
//			user.setPassword(ENCODER.encode(defaultPassword));
//			user.setDeptId(dept.getDeptId());
//			userService.save(user);
//			// 构造新角色
//			SysRole role = new SysRole();
//			role.setRoleCode(defaultRoleCode);
//			role.setRoleName(defaultRoleName);
//			roleService.save(role);
//			// 用户角色关系
//			SysUserRole userRole = new SysUserRole();
//			userRole.setUserId(user.getUserId());
//			userRole.setRoleId(role.getRoleId());
//			userRoleService.save(userRole);
//			// 插入新的菜单
//			saveTenantMenu(TreeUtil.buildTree(menuList, CommonConstants.MENU_TREE_ROOT_ID), CommonConstants.MENU_TREE_ROOT_ID);
//			List<SysMenu> newMenuList = menuService.list();
//
//			// 查询全部菜单,构造角色菜单关系
//			List<SysRoleMenu> collect = newMenuList.stream().map(menu -> {
//				SysRoleMenu roleMenu = new SysRoleMenu();
//				roleMenu.setRoleId(role.getRoleId());
//				roleMenu.setMenuId(menu.getMenuId());
//				return roleMenu;
//			}).collect(Collectors.toList());
//			roleMenuService.saveBatch(collect);
//			// 插入系统字典
//			dictService.saveBatch(dictList);
//			// 处理字典项最新关联的字典ID
//			List<SysDictItem> itemList = dictList.stream()
//												 .flatMap(dict -> dictItemList.stream()
//												 .filter(item -> item.getType().equals(dict.getType()))
//												 .peek(item -> item.setDictId(dict.getId())))
//												 .collect(Collectors.toList());
//
//			// 插入客户端
//			clientServices.saveBatch(clientDetailsList);
//			// 插入系统配置
//			paramService.saveBatch(publicParamList);
//			return dictItemService.saveBatch(itemList);
//		}));
//	}
//
//	/**
//	 * 保存新的租户菜单，维护成新的菜单
//	 * 
//	 * @param nodeList 节点树
//	 * @param parent   上级
//	 */
//	private void saveTenantMenu(List<MenuTree> nodeList, String parentId) {
//		for (MenuTree node : nodeList) {
//			SysMenu menu = new SysMenu();
//			BeanUtils.copyProperties(node, menu, "parentId");
//			menu.setParentId(parentId);
//			menuService.save(menu);
//			if (CollUtil.isNotEmpty(node.getChildren())) {
//				List<MenuTree> childrenList = node.getChildren().stream().map(treeNode -> (MenuTree) treeNode).collect(Collectors.toList());
//				saveTenantMenu(childrenList, menu.getMenuId());
//			}
//		}
//	}

//}
