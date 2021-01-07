package com.wujie.springbootlayui.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wujie.springbootlayui.sys.common.*;
import com.wujie.springbootlayui.sys.entity.Permission;
import com.wujie.springbootlayui.sys.entity.Role;
import com.wujie.springbootlayui.sys.entity.User;
import com.wujie.springbootlayui.sys.service.IPermissionService;
import com.wujie.springbootlayui.sys.service.IRoleService;
import com.wujie.springbootlayui.sys.vo.RoleVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

	@Autowired
	private IRoleService roleService;

	@Autowired
	private IPermissionService permissionService;

	/**
	 * 查询所有角色
	 *
	 * @param roleVo
	 * @return
	 */
	@RequestMapping("loadAllRole")
	public DataGridView loadAllRole(RoleVo roleVo) {
		Subject subject = SecurityUtils.getSubject();
		ActiverUser activerUser = (ActiverUser) subject.getPrincipal();
		Integer belongId = activerUser.getUser().getId();

		IPage<Role> page = new Page<Role>(roleVo.getPage(), roleVo.getLimit());
		QueryWrapper<Role> queryWrapper = new QueryWrapper<Role>();
		if (belongId != 1) {
			queryWrapper.eq("belong_user", belongId);
		}
		queryWrapper.like(StringUtils.isNotBlank(roleVo.getName()), "name", roleVo.getName());
		queryWrapper.like(StringUtils.isNotBlank(roleVo.getRemark()), "remark", roleVo.getRemark());
		queryWrapper.eq(roleVo.getAvailable() != null, "available", roleVo.getAvailable());
		queryWrapper.ge(roleVo.getStartTime() != null, "createtime", roleVo.getStartTime());
		queryWrapper.le(roleVo.getEndTime() != null, "createtime", roleVo.getEndTime());
		queryWrapper.orderByDesc("createtime");
		roleService.page(page, queryWrapper);
		return new DataGridView(page.getTotal(), page.getRecords());
	}

	/**
	 * 添加角色
	 *
	 * @param roleVo
	 * @return
	 */
	@RequestMapping("addRole")
	public ResultObj addRole(RoleVo roleVo) {
		try {
			Subject subject = SecurityUtils.getSubject();
			ActiverUser activerUser = (ActiverUser) subject.getPrincipal();
			Integer belongId = activerUser.getUser().getId();
			roleVo.setBelongUser(belongId);
			// 根据用户ID查询出角色ID
			List<Integer> roleId = roleService.queryUserRoleIdsByUid(belongId);
			List<Integer> rolePermissions = roleService.queryRolePermissionIdsByRid(roleId.toArray(new Integer[roleId.size()]));

			roleVo.setCreatetime(new Date());
			roleService.insertRole(roleVo);
			Integer newId = roleVo.getId();
			Integer[] perIds = new Integer[rolePermissions.size()];
			roleService.saveRolePermission(newId, rolePermissions.toArray(perIds));
			return new ResultObj(Constast.OK,"messages.ADD_SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
			return  new ResultObj(Constast.ERROR,"messages.ADD_ERROR");
		}
	}

	/**
	 * 修改角色
	 *
	 * @param roleVo
	 * @return
	 */
	@RequestMapping("updateRole")
	public ResultObj updateRole(RoleVo roleVo) {
		try {
			roleService.updateById(roleVo);
			return new ResultObj(Constast.OK,"messages.UPDATE_SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultObj(Constast.ERROR,"messages.UPDATE_ERROR");
		}
	}

	/**
	 * 删除角色
	 *
	 * @param id
	 * @return
	 */
	@RequestMapping("deleteRole")
	public ResultObj deleteRole(Integer id) {
		try {
			// 删除角色都需先解绑用户
			List<User> users = roleService.getUserByRoleId(id);
			if (users.size() > 0) {
				String nameStr = "请先删除属于该角色的用户：";
				int count = 0;
				for (int i = 0; i < users.size(); i++) {
					if (count == 5) {
						nameStr += " 等";
						break;
					}

					User user = users.get(i);
					if (!StringUtil.isEmpty(user.getName())) {
						if (count > 0) {
							nameStr += ",";
						}

						nameStr += user.getName();
						count++;
					}
				}
				return new ResultObj(400, nameStr);
			}

			this.roleService.removeById(id);
			return new ResultObj(Constast.OK,"messages.DELETE_SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultObj(Constast.ERROR,"messages.DELETE_ERROR");
		}
	}

	/**
	 * 根据角色ID加载菜单和权限的树的json串
	 * 
	 * @param roleId
	 * @return
	 */
	@RequestMapping("initPermissionByRoleId")
	public DataGridView initPermissionByRoleId(Integer roleId) {
		// 查询当前用户信息
		Subject subject = SecurityUtils.getSubject();
		ActiverUser activerUser = (ActiverUser) subject.getPrincipal();
		Integer belongId = activerUser.getUser().getId();
		List<Integer> roles = new ArrayList<>();
		roles.add(roleId);
		// 如果belongId为1是超级管理员，有所有权限;如果不为1,就只能获取自己角色的权限

		// 查询所有可用的菜单和权限
		QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("available", Constast.AVAILABLE_TRUE);
		List<Permission> allPermissions = null;
		if (belongId == 1) {
			allPermissions = permissionService.list(queryWrapper);
		} else {
			List<Integer> currentRolePermissions = roleService.queryRolePermissionIdsByRid(roles.toArray(new Integer[roles.size()]));
			if (currentRolePermissions.size() > 0) {
				queryWrapper.in("id", currentRolePermissions);
				allPermissions = permissionService.list(queryWrapper);
			} else {
				allPermissions = new ArrayList<>();
			}

		}
		// 1.首先根据角色id查询出当前角色所拥有的所有菜单的ID和权限的ID
		List<Integer> currentRolePermissions = roleService.queryRolePermissionIdsByRid(roles.toArray(new Integer[roles.size()]));
		// 2.根据查询出来的菜单ID和权限ID，再查询出菜单的数据和权限的数据
		List<Permission> currentPermissions = null;
		// 如果根据角色id查询出来了菜单ID或权限ID，就去查询
		if (currentRolePermissions.size() > 0) {
			queryWrapper.in("id", currentRolePermissions);
			currentPermissions = permissionService.list(queryWrapper);
		} else {
			currentPermissions = new ArrayList<>();
		}
		// 构造List<TreeNode>
		List<TreeNode> nodes = new ArrayList<>();
		for (Permission allPermission : allPermissions) {
			String checkArr = "0";
			for (Permission currentPermission : currentPermissions) {
				if (allPermission.getId().equals(currentPermission.getId())) {
					checkArr = "1";
					break;
				}
			}
			Boolean spread = (allPermission.getOpen() == null || allPermission.getOpen() == 1) ? true : false;
			nodes.add(new TreeNode(allPermission.getId(), allPermission.getPid(), allPermission.getTitle(), spread,
					checkArr));
		}
		return new DataGridView(nodes);
	}

	/**
	 * 保存角色和菜单权限之间的关系
	 *
	 * @param rid
	 * @param ids
	 * @return
	 */
	@RequestMapping("saveRolePermission")
	public ResultObj saveRolePermission(Integer rid, Integer[] ids) {
		try {
			List<Integer> action = new ArrayList<>();
			for (int i = 0; i < ids.length; i++) {
				QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
				queryWrapper.eq("pid", ids[i]);
				queryWrapper.eq("type", "permission");
				List<Permission> list = permissionService.list(queryWrapper);
				for (int j = 0; j < list.size(); j++) {
					action.add(list.get(j).getId());
				}
			}

			List<Integer> tempList = Arrays.asList(ids);
			List<Integer> idList = new ArrayList<Integer>(tempList);
			idList.addAll(action);
			Integer[] newIds = new Integer[idList.size()];
			newIds = idList.toArray(newIds);

			roleService.saveRolePermission(rid, newIds);

			return  new ResultObj(Constast.OK,"messages.DISPATCH_SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultObj(Constast.ERROR,"messages.DISPATCH_ERROR");
		}

	}

}
