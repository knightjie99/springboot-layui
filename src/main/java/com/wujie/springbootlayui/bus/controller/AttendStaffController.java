package com.wujie.springbootlayui.bus.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.wujie.springbootlayui.sys.common.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wujie.springbootlayui.bus.entity.AttendRule;
import com.wujie.springbootlayui.bus.entity.AttendStaff;
import com.wujie.springbootlayui.bus.service.IAttendRuleService;
import com.wujie.springbootlayui.bus.service.IAttendStaffService;
import com.wujie.springbootlayui.bus.service.IStaffService;
import com.wujie.springbootlayui.bus.vo.AttendStaffVo;

@RestController
@RequestMapping("/attendStaff")
public class AttendStaffController {

    @Autowired
    private IAttendStaffService asService;

    @Autowired
    private IAttendRuleService ruleService;

    @Autowired
    private IStaffService staffService;

    /**
     * 查询所有人员排班
     *
     * @param ruleVo
     * @return
     */
    @RequestMapping("loadAllAttendStaff")
    public DataGridView loadAllAttendStaff(AttendStaffVo asVo) {
        // 查询当前用户信息
        Subject subject = SecurityUtils.getSubject();
        ActiverUser activerUser = (ActiverUser) subject.getPrincipal();
        Integer belongId = activerUser.getUser().getId();
        // 1.声明一个分页page对象
        IPage<AttendStaff> page = new Page<AttendStaff>(asVo.getPage(), asVo.getLimit());
        // 2.声明一个queryWrapper
        QueryWrapper<AttendStaff> queryWrapper = new QueryWrapper<AttendStaff>();
        queryWrapper.orderByDesc("create_time");
        // belongId为1时是超级管理员，可以查所有
        if (belongId != 1) {
            queryWrapper.eq("belong_id", belongId);
        }
        asService.page(page, queryWrapper);
        List<AttendStaff> list = page.getRecords();
        for (AttendStaff as : list) {
            Integer ruleId = as.getRuleId();
            if (ruleId != null) {
                AttendRule rule = ruleService.getById(ruleId);
                as.setRuleName(rule.getRuleName());
                as.setSignTime(rule.getSignTime());
                as.setLeaveTime(rule.getLeaveTime());
            }
            String staffList = as.getStaffList();
            if (!StringUtil.isEmpty(staffList)) {
                List<String> ids = Arrays.asList(staffList.split(","));
                String names = staffService.getStaffNameListByIds(ids);
                if(!StringUtil.isEmpty(names)&&names.length()>15){
                    List<String> nameList = Arrays.asList(names.split(","));
                    if(nameList.size()>5){
                        names="";
                        for (int i = 0; i <5 ; i++) {
                            names+=nameList.get(i)+",";
                        }
                        names+="等";
                    }
                }
                as.setNameList(names);
            }

        }
        return new DataGridView(page.getTotal(), list);
    }

    /**
     * 添加人员排班
     *
     * @param asVo
     * @return
     */
    @RequestMapping("addAttendStaff")
    public ResultObj addAttendStaff(AttendStaffVo asVo) {
        try {
            Subject subject = SecurityUtils.getSubject();
            ActiverUser activerUser = (ActiverUser) subject.getPrincipal();
            Integer belongId = activerUser.getUser().getId();
            asVo.setBelongId(belongId);
            asVo.setCreateTime(new Date());
            asVo.setUpdateTime(new Date());
            String listStr = asVo.getStaffList();
            if (!StringUtil.isEmpty(listStr)&&listStr.endsWith(",")) {
                asVo.setStaffList(listStr.substring(0, listStr.length() - 1));
            }
            asService.save(asVo);
            return new ResultObj(Constast.OK,"messages.ADD_SUCCESS");
        } catch (Exception e) {
            e.printStackTrace();
            return  new ResultObj(Constast.ERROR,"messages.ADD_ERROR");
        }
    }

    /**
     * 修改人员排班
     *
     * @param asVo
     * @return
     */
    @RequestMapping("updateAttendStaff")
    public ResultObj updateAttendRule(AttendStaffVo asVo) {
        try {
            asVo.setUpdateTime(new Date());
            String listStr = asVo.getStaffList();
            if (!StringUtil.isEmpty(listStr)&&listStr.endsWith(",")) {
                asVo.setStaffList(listStr.substring(0, listStr.length() - 1));
            }
            asService.updateById(asVo);
            return new ResultObj(Constast.OK,"messages.UPDATE_SUCCESS");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultObj(Constast.ERROR,"messages.UPDATE_ERROR");
        }
    }

    /**
     * 删除人员排班
     *
     * @param id
     * @return
     */
    @RequestMapping("deleteAttendStaff")
    public ResultObj deleteAttendStaff(Integer id) {
        try {
            asService.removeById(id);
            return  new ResultObj(Constast.OK,"messages.DELETE_SUCCESS");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultObj(Constast.ERROR,"messages.DELETE_ERROR");
        }
    }
}
