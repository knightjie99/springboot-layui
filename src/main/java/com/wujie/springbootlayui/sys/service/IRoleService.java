package com.wujie.springbootlayui.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wujie.springbootlayui.sys.entity.Role;
import com.wujie.springbootlayui.sys.entity.User;

import java.io.Serializable;
import java.util.List;


public interface IRoleService extends IService<Role> {
	
	/**
	 * 新增角色
	 */
	Integer insertRole(Role role);

    /**
     * 根据角色ID查询当前角色拥有的菜单ID和权限ID
     * @param roleId
     * @return
     */
    List<Integer> queryRolePermissionIdsByRid(Integer[] roleId);

    /**
     * 保存角色和菜单权限之间的关系
     * @param rid
     * @param ids
     */
    void saveRolePermission(Integer rid, Integer[] ids);

    /**
     * 查询当前用户拥有的角色ID集合
     * @param id
     * @return
     */
    List<Integer>  queryUserRoleIdsByUid(Integer id);
    
    /**
     * 保存用户id对应的角色ID
     */
    void  insertUserRole(Integer uid, Integer[] rid);
    
    List<User> getUserByRoleId(Integer rid);

    void deleteURbyUser(Serializable uid);
    
}
