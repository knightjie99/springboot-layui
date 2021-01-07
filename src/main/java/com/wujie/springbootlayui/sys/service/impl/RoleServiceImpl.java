package com.wujie.springbootlayui.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wujie.springbootlayui.sys.entity.Role;
import com.wujie.springbootlayui.sys.entity.User;
import com.wujie.springbootlayui.sys.mapper.RoleMapper;
import com.wujie.springbootlayui.sys.service.IRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Override
    public boolean removeById(Serializable id) {
        // 根据角色ID删除sys_role_permission表中的数据
        this.getBaseMapper().deleteRolePermissionByRid(id);
        // 根据角色ID删除sys_user_role表中的数据
        this.getBaseMapper().deleteUserRoleByRid(id);
        return super.removeById(id);
    }

    /**
     * 根据角色ID查询当前角色拥有的菜单ID和权限ID
     *
     * @param roleId
     * @return
     */
    @Override
    public List<Integer> queryRolePermissionIdsByRid(Integer[] roleId) {
        List<Integer> result = new ArrayList<>();


        for (int i = 0; i < roleId.length; i++) {
            Set<Integer> set = new HashSet<>();
            List<Integer> roles = this.getBaseMapper().queryRolePermissionIdsByRid(roleId[i]);
            for (int j = 0; j < roles.size(); j++) {
                set.add(roles.get(j));
            }
            result.addAll(set);
        }

        return result;
    }

    /**
     * 保存角色和菜单权限之间的关系
     *
     * @param rid
     * @param ids
     */
    @Override
    public void saveRolePermission(Integer rid, Integer[] ids) {
        RoleMapper roleMapper = this.getBaseMapper();
        // 根据rid删除sys_role_permission
        roleMapper.deleteRolePermissionByRid(rid);
        if (ids != null && ids.length > 0) {
            for (Integer pid : ids) {
                roleMapper.saveRolePermission(rid, pid);
            }
        }
    }

    /**
     * 查询当前用户拥有的角色ID
     *
     * @param id
     * @return
     */
    @Override
    public List<Integer> queryUserRoleIdsByUid(Integer id) {
        return getBaseMapper().queryUserRoleIdsByUid(id);

    }

    @Override
    public void insertUserRole(Integer uid, Integer[] rid) {
        RoleMapper roleMapper = this.getBaseMapper();

        if (null != rid) {
            for (int i = 0; i < rid.length; i++) {
                roleMapper.insertUserRole(uid, rid[i]);
            }
        }
    }

    @Override
    public Integer insertRole(Role role) {
    	this.getBaseMapper().insert(role);
    	//返回新增的主键ID
    	Integer id = role.getId();
        return id;
    }

    @Override
    public List<User> getUserByRoleId(Integer rid) {
        return this.getBaseMapper().getUserByRoleId(rid);
    }

    @Override
    public void deleteURbyUser(Serializable uid) {
        this.getBaseMapper().deleteRoleUserByUid(uid);
    }
}
