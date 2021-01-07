package com.wujie.springbootlayui.sys.controller;


import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wujie.springbootlayui.bus.entity.AttendRule;
import com.wujie.springbootlayui.bus.service.*;
import com.wujie.springbootlayui.sys.common.*;
import com.wujie.springbootlayui.sys.config.MybatisPlusConfig;
import com.wujie.springbootlayui.sys.entity.Role;
import com.wujie.springbootlayui.sys.entity.User;
import com.wujie.springbootlayui.sys.service.IDeviceService;
import com.wujie.springbootlayui.sys.service.IRoleService;
import com.wujie.springbootlayui.sys.service.IUserService;
import com.wujie.springbootlayui.sys.vo.UserVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IPassRecordService passRecordService;

    @Autowired
    private IAttendService attendService;

    @Autowired
    private IDeviceService deviceService;

    /**
     * 查询所有用户
     *
     * @param userVo
     * @return
     */
    @RequestMapping("loadAllUser")
    public DataGridView loadAllUser(UserVo userVo) {
        Subject subject = SecurityUtils.getSubject();
        ActiverUser activerUser = (ActiverUser) subject.getPrincipal();
        Integer belongId = activerUser.getUser().getId();
        List<Integer> roleId = roleService.queryUserRoleIdsByUid(belongId);
        IPage<User> page = new Page<User>(userVo.getPage(), userVo.getLimit());
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.eq(StringUtils.isNotBlank(userVo.getName()), "login_name", userVo.getName()).or().eq(StringUtils.isNotBlank(userVo.getName()), "name", userVo.getName());
        //查询系统用户 gt大于1 
        queryWrapper.eq("type", Constast.USER_TYPE_NORMAL);

        if (!roleId.contains(1)) {
            queryWrapper.eq("belong_user", belongId);
        }

        userService.page(page, queryWrapper);

        //将所有用户数据放入list中
        List<User> list = page.getRecords();
        return new DataGridView(page.getTotal(), list);
    }


    /**
     * 加载排序码
     *
     * @return
     */
    @RequestMapping("loadUserMaxOrderNum")
    public Map<String, Object> loadUserMaxOrderNum() {
        Subject subject = SecurityUtils.getSubject();
        ActiverUser activerUser = (ActiverUser) subject.getPrincipal();
        Integer belongId = activerUser.getUser().getId();

        Map<String, Object> map = new HashMap<String, Object>();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("belong_user", belongId);
        queryWrapper.orderByDesc("order_num");
        IPage<User> page = new Page<>(1, 1);
        List<User> list = userService.page(page, queryWrapper).getRecords();
        if (list.size() > 0) {
            map.put("value", list.get(0).getOrderNum() + 1);
        } else {
            map.put("value", 1);
        }
        return map;
    }

    /**
     * 根据部门ID查询用户
     *
     * @param deptid
     * @return
     */
    @RequestMapping("loadUsersByDeptId")
    public DataGridView loadUsersByDeptIp(Integer deptid) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(deptid != null, "deptid", deptid);
        queryWrapper.eq("available", Constast.AVAILABLE_TRUE);
        List<User> list = userService.list(queryWrapper);
        return new DataGridView(list);
    }

    /**
     * 把用户名转成拼音
     *
     * @param username
     * @return
     */
    @RequestMapping("changeChineseToPinyin")
    public Map<String, Object> changeChineseToPinyin(String username) {
        Map<String, Object> map = new HashMap<>();
        if (null != username) {
            map.put("value", PinyinUtils.getPingYin(username));
        } else {
            map.put("value", "");
        }
        return map;
    }

    /**
     * 添加用户
     *
     * @param userVo
     * @return
     */
    @RequestMapping("addUser")
    public ResultObj addUser(UserVo userVo) {
        try {
            Subject subject = SecurityUtils.getSubject();
            ActiverUser activerUser = (ActiverUser) subject.getPrincipal();
            Integer belongId = activerUser.getUser().getId();
            String loginName = userVo.getLoginName();
            Integer user = userService.queryUserByLoginName(loginName);
            if (user != null) {
                return new ResultObj(400, "account_registered");
            }
            String phone = userVo.getPhone();
            boolean isPhone = StringUtil.isPhone(phone);
            if (!isPhone) {
                return new ResultObj(400, "wrong_number");
            }

            Integer checkPhone = userService.queryUserByPhone(phone);
            Integer checkPhoneName = userService.queryUserByPhone(loginName);
            if (checkPhone != null || checkPhoneName != null) {
                return new ResultObj(400, "phone_uesed");
            }

            //设置类型
            userVo.setType(Constast.USER_TYPE_NORMAL);
            //设置盐
            String salt = IdUtil.simpleUUID().toUpperCase();
            userVo.setSalt(salt);
            //设置密码
            String pwd = userVo.getPwd();
            if (StringUtil.isEmpty(pwd)) {
                return new ResultObj(400, "password_cantBe_null");
            }
            boolean checkPwd = StringUtil.checkStr(pwd);
            if (!checkPwd) {
                return new ResultObj(400, "password_not_legal");
            }
            userVo.setPwd(new Md5Hash(pwd, salt, 2).toString());
            userVo.setBelongUser(belongId);
            userVo.setCreateTime(new Date());
            userVo.setUpdateTime(new Date());
            Integer newId = userService.insertUser(userVo);
            //创建对应的通行记录表
            String tName = "bus_pass_record_" + newId;
            passRecordService.createPassRecordTable(tName);

            String tName2 = "bus_attend_" + newId;
            MybatisPlusConfig.attendTName.set(tName2);
            attendService.createAttendTable(tName2);
            //为新用户添加对应的默认角色
            roleService.insertUserRole(userVo.getId(), new Integer[]{9, 10, 11});
            return new ResultObj(Constast.OK,"messages.ADD_SUCCESS");
        } catch (Exception e) {
            e.printStackTrace();
            return  new ResultObj(Constast.ERROR,"messages.ADD_ERROR");
        }
    }

    /**
     * 根据id查询一个用户
     *
     * @param id 领导的id
     * @return
     */
    @RequestMapping("loadUserById")
    public DataGridView loadUserById(Integer id) {
        return new DataGridView(userService.getById(id));
    }

    /**
     * 修改用户
     *
     * @param userVo
     * @return
     */
    @RequestMapping("updateUser")
    public ResultObj updateUser(UserVo userVo, HttpSession session) {
        try {
            userVo.setUpdateTime(new Date());
            if (!StringUtil.isEmpty(userVo.getUserPhoto())) {
                userVo.setUserPhoto(AppFileUtils.renameFile(userVo.getUserPhoto()));
            }
            User user = userService.getById(userVo.getId());
            String pwd = userVo.getPwd();
            String oldPwd = user.getPwd();
            if (!StringUtil.isEmpty(pwd) && !StringUtil.isEmpty(oldPwd)) {
                if (!pwd.equals(oldPwd)) {
                    String salt = user.getSalt();
                    userVo.setPwd(new Md5Hash(pwd, salt, 2).toString());
                }
            }

            userService.updateById(userVo);

            //将user存储到session中
            WebUtils.getSession().removeAttribute("user");
            WebUtils.getSession().setAttribute("user", user);
            return new ResultObj(Constast.OK,"messages.UPDATE_SUCCESS");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultObj(Constast.ERROR,"messages.UPDATE_ERROR");
        }
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @RequestMapping("deleteUser/{id}")
    public ResultObj deleteUser(@PathVariable("id") Integer id) {
        try {

            roleService.deleteURbyUser(id);

            QueryWrapper<AttendRule> ruleWrapper = new QueryWrapper<>();
            ruleWrapper.eq("belong_id", id);
            userService.deleteUserBelongs(id);

            deviceService.resetBelongId(id);

            userService.removeById(id);
            return new ResultObj(Constast.OK,"messages.DELETE_SUCCESS");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultObj(Constast.ERROR,"messages.DELETE_ERROR");
        }
    }

    /**
     * 重置用户密码
     *
     * @param id
     * @return
     */
    @RequestMapping("resetPwd/{id}")
    public ResultObj resetPwd(@PathVariable("id") Integer id) {
        try {
            User user = new User();
            user.setId(id);
            //设置盐  32位(大写英文字母(A-Z)加数字(0-9))
            String salt = IdUtil.simpleUUID().toUpperCase();
            user.setSalt(salt);
            //设置密码
            user.setPwd(new Md5Hash(Constast.USER_DEFAULT_PWD, salt, 2).toString());
            userService.updateById(user);
            return new ResultObj(Constast.OK,"messages.RESET_SUCCESS");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultObj(Constast.ERROR,"messages.RESET_ERROR");
        }
    }

    /**
     * 设置用户密码
     *
     * @param id
     * @return
     */
    @RequestMapping("setPwd/{pwd}")
    public ResultObj resetPwd(@PathVariable("pwd") String pwd, HttpSession session) {
        try {
            User user = (User) session.getAttribute("user");
            //设置盐  32位(大写英文字母(A-Z)加数字(0-9))
            String salt = IdUtil.simpleUUID().toUpperCase();
            user.setSalt(salt);
            //设置密码
            user.setPwd(new Md5Hash(pwd, salt, 2).toString());
            userService.updateById(user);
            return new ResultObj(Constast.OK,"messages.RESET_SUCCESS");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultObj(Constast.ERROR,"messages.RESET_ERROR");
        }
    }


    /**
     * 根据用户id查询角色并选中已拥有的角色
     *
     * @param id 用户id
     * @return
     */
    @RequestMapping("initRoleByUserId")
    public DataGridView initRoleByUserId(Integer id) {
        //1.查询所有可用的角色
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("available", Constast.AVAILABLE_TRUE);
        List<Map<String, Object>> listMaps = roleService.listMaps(queryWrapper);
        //2.查询当前用户拥有的角色ID集合
        List<Integer> rid = roleService.queryUserRoleIdsByUid(id);
        for (Map<String, Object> map : listMaps) {
            Boolean LAY_CHECKED = false;
            Integer roleId = (Integer) map.get("id");
            //如果当前用户已有该角色，则让LAY_CHECKED为true。LAY_CHECKED为true时，复选框选中
            if (rid.contains(roleId)) {
                LAY_CHECKED = true;
            }
            map.put("LAY_CHECKED", LAY_CHECKED);
        }
        return new DataGridView(Long.valueOf(listMaps.size()), listMaps);
    }

    /**
     * 保存用户和角色的关系
     *
     * @param uid 用户的ID
     * @param ids 用户拥有的角色的ID的数组
     * @return
     */
    @RequestMapping("saveUserRole")
    public ResultObj saveUserRole(Integer uid, Integer[] ids) {
        System.out.println("cont rid:" + ids);
        try {
            userService.saveUserRole(uid, ids);
            return  new ResultObj(Constast.OK,"messages.DISPATCH_SUCCESS");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultObj(Constast.ERROR,"messages.DISPATCH_ERROR");
        }
    }


}

