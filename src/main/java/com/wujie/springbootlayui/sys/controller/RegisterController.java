package com.wujie.springbootlayui.sys.controller;

import cn.hutool.core.util.IdUtil;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wujie.springbootlayui.bus.service.IPassRecordService;
import com.wujie.springbootlayui.sys.cache.CachePool;
import com.wujie.springbootlayui.sys.common.*;
import com.wujie.springbootlayui.sys.entity.PwdReset;
import com.wujie.springbootlayui.sys.entity.Register;
import com.wujie.springbootlayui.sys.entity.User;
import com.wujie.springbootlayui.sys.service.IRegisterService;
import com.wujie.springbootlayui.sys.service.IUserService;
import com.wujie.springbootlayui.sys.vo.RegisterVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("register")
public class RegisterController {

    @Autowired
    private IRegisterService registerService;

    @Autowired
    private IUserService userService;
    
    @Autowired
	private IPassRecordService recordService;

    /**
     * 查询所有角色
     *
     * @param roleVo
     * @return
     */
    @RequestMapping("loadAllRegister")
    public DataGridView loadAllRegister(RegisterVo registerVo) {

        IPage<Register> page = new Page<Register>(registerVo.getPage(), registerVo.getLimit());

        QueryWrapper<Register> queryWrapper = new QueryWrapper<Register>();
        queryWrapper.like(StringUtils.isNotBlank(registerVo.getCompanyName()), "company_name", registerVo.getCompanyName());
        queryWrapper.eq(registerVo.getPhone() != null, "phone", registerVo.getPhone());
        queryWrapper.eq(registerVo.getStatus() != null, "status", registerVo.getStatus());
        registerService.page(page, queryWrapper);
        return new DataGridView(page.getTotal(), page.getRecords());
    }


    @RequestMapping("sendCode")
    public ResultObj sendCode(String phone) {
        if (StringUtil.isEmpty(phone)) {
            return new ResultObj(400, "phone_cantBe_null");
        }
        if (!StringUtil.isPhone(phone)) {
            return new ResultObj(400, "wrong_number");
        }
        Integer query = registerService.queryRegisterByPhone(phone);
        if (null != query) {
            return new ResultObj(500, "phone_uesed");
        }
        String code = StringUtil.getRandom();
        SendSmsResponse smsRsp = SMSUtil.sendSms(phone, code, 1);
        if (!smsRsp.getCode().equals("OK")) {
            return new ResultObj(500, "send_code_failed");
        }
        CachePool.CACHE_CONTAINER.put(phone, code + "," + StringUtil.getCurrentTime());
        return new ResultObj(200, "send_code_success", code);
    }

    @RequestMapping("sendCodeUserExists")
    public ResultObj sendCodeUserExists(String phone) {
        if (StringUtil.isEmpty(phone)) {
            return new ResultObj(400, "phone_cantBe_null");
        }
        if (!StringUtil.isPhone(phone)) {
            return new ResultObj(400, "wrong_number");
        }
//		Integer query = registerService.queryRegisterByPhone(phone);
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("phone", phone);

        if (userService.list(userQueryWrapper).size() < 0) {
            return new ResultObj(500, "phone_not_registered");
        }
        String code = StringUtil.getRandom();
        SendSmsResponse smsRsp = SMSUtil.sendSms(phone, code, 2);
        if (!smsRsp.getCode().equals("OK")) {
            return new ResultObj(500, "send_code_failed");
        }
        CachePool.CACHE_CONTAINER.put(phone, code + "," + StringUtil.getCurrentTime());
        return new ResultObj(200, "send_code_success", code);
    }


    @RequestMapping("doRegister")
    public ResultObj doRegister(RegisterVo registerVo) {

        String loginName = registerVo.getLoginName();
        Integer userId = userService.queryUserByLoginName(loginName);
        if (userId != null) {
            return new ResultObj(400, "account_registered");
        }

        String code = registerVo.getCode();
        String phone = registerVo.getPhone();
        if (StringUtil.isPhone(loginName)) {
            Integer userId2 = userService.queryUserByPhone(loginName);
            if (userId2 != null) {
                return new ResultObj(400, "phone_uesed");
            }
        }
        String getCache = (String) CachePool.CACHE_CONTAINER.get(phone);
        if (StringUtil.isEmpty(getCache)) {
            return new ResultObj(500, "register_failed");
        }
        String[] str = getCache.split(",");
        String getCode = str[0];
        if (!code.equals(getCode)) {
            return new ResultObj(400, "messages.LOGIN_ERROR_CODE");
        }
        String vaildTime = str[1];
        Integer compareTime = StringUtil.compareToCurrentTime(vaildTime);
        if (compareTime > 120) {
            return new ResultObj(400, "code_outof_date");
        }

        try {

            User user = new User();

            //设置类型
            user.setType(Constast.USER_TYPE_NORMAL);
            //设置盐
            String salt = IdUtil.simpleUUID().toUpperCase();
            user.setSalt(salt);
            //设置密码
            user.setPwd(new Md5Hash(registerVo.getPassword(), salt, 2).toString());
            user.setName(registerVo.getCompanyName());
            user.setLoginName(registerVo.getLoginName());
            user.setCreateTime(new Date());
            user.setUpdateTime(new Date());
            user.setPhone(registerVo.getPhone());
            Integer newId = userService.insertUser(user);
            //创建对应的通行记录表
            String tName = "bus_pass_record_"+newId;
            recordService.createPassRecordTable(tName);
            
            userService.saveUserRole(user.getId(), new Integer[]{9, 10, 11});

            return new ResultObj(Constast.OK,"messages.ADD_SUCCESS");
        } catch (Exception e) {
            e.printStackTrace();
            return  new ResultObj(Constast.ERROR,"messages.ADD_ERROR");
        }
    }

    @RequestMapping("doForgetCode")
    public ResultObj doForgetCode(PwdReset prp) {
        String phone = prp.getPhone();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone", phone);

        User DBuser = userService.getOne(queryWrapper);
        if (DBuser == null) {
            return new ResultObj(400, "wrong_number_actual");
        }


        String code = prp.getCode();
        String getCache = (String) CachePool.CACHE_CONTAINER.get(phone);
        if (StringUtil.isEmpty(getCache)) {
            return new ResultObj(500, "messages.LOGIN_ERROR_CODE");
        }
        String[] str = getCache.split(",");
        String getCode = str[0];
        if (!code.equals(getCode)) {
            return new ResultObj(400, "messages.LOGIN_ERROR_CODE");
        }
        String vaildTime = str[1];
        Integer compareTime = StringUtil.compareToCurrentTime(vaildTime);
        if (compareTime > 120) {
            return new ResultObj(400, "code_outof_date");
        }
        ResultObj result = new ResultObj(200, "messages.OK");
        result.setData(DBuser.getLoginName());

        return result;
    }

    @RequestMapping("doForget")
    public ResultObj doForget(PwdReset prp) {
        String loginName = prp.getLogName();
        String phone = prp.getPhone();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone", phone);
        queryWrapper.eq("login_name", loginName);
        System.out.println(phone + " " + loginName);

        User DBuser = userService.getOne(queryWrapper);
        if (DBuser == null) {
            return new ResultObj(400, "phone_or_account_error");
        }

        String salt = IdUtil.simpleUUID().toUpperCase();
        DBuser.setSalt(salt);
        DBuser.setPwd(new Md5Hash(prp.getPwd(), salt, 2).toString());
        DBuser.setUpdateTime(new Date());

        try {
            userService.updateById(DBuser);
            return new ResultObj(Constast.OK,"messages.UPDATE_SUCCESS");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultObj(Constast.ERROR,"messages.UPDATE_ERROR");
        }
    }


    /**
     * 修改注册信息
     *
     * @param registerVo
     * @return
     */
    @RequestMapping("updateRegister")
    public ResultObj updateRegister(RegisterVo registerVo) {

        Subject subject = SecurityUtils.getSubject();
        ActiverUser activerUser = (ActiverUser) subject.getPrincipal();
        Integer belongId = activerUser.getUser().getId();
        if (belongId == null) {
            return new ResultObj(600, "messages.LOGIN_ERROR");
        }
        try {
            //如果审核通过 status为3,需要在用户表中新增用户
            if (registerVo.getStatus() == 3) {
                Register register = registerService.getById(registerVo.getId());

                User user = new User();

                //设置类型
                user.setType(Constast.USER_TYPE_NORMAL);
                //设置盐
                String salt = IdUtil.simpleUUID().toUpperCase();
                user.setSalt(salt);
                //设置密码
                user.setPwd(new Md5Hash(register.getPassword(), salt, 2).toString());
                user.setName(register.getCompanyName());
                user.setLoginName(register.getLoginName());
                user.setCreateTime(new Date());
                user.setUpdateTime(new Date());
                user.setPhone(register.getPhone());
                user.setBelongUser(belongId);
                userService.save(user);
                userService.saveUserRole(user.getId(), new Integer[]{9, 10, 11});
                //设置角色
            }

            registerService.updateById(registerVo);
            return new ResultObj(Constast.OK,"messages.UPDATE_SUCCESS");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultObj(Constast.ERROR,"messages.UPDATE_ERROR");
        }
    }

    @RequestMapping("deleteRegister")
    public ResultObj deleteRegister(RegisterVo registerVo) {
        try {
            registerService.removeById(registerVo.getId());
            return new ResultObj(Constast.OK,"messages.DELETE_SUCCESS");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultObj(Constast.ERROR,"messages.DELETE_ERROR");
        }


    }
}
