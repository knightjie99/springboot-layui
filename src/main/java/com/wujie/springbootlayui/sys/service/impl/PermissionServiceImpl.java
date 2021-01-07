package com.wujie.springbootlayui.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wujie.springbootlayui.sys.entity.Permission;
import com.wujie.springbootlayui.sys.mapper.PermissionMapper;
import com.wujie.springbootlayui.sys.service.IPermissionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;


@Service
@Transactional
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

    @Override
    public boolean removeById(Serializable id) {

        //根据权限ID或菜单ID删除sys_role_permission表中的数据
        PermissionMapper permissionMapper = this.getBaseMapper();
        permissionMapper.deleteRolePermissionByPid(id);
        //删除权限表中的数据
        return super.removeById(id);
    }
}
