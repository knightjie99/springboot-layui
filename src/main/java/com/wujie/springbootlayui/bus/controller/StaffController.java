package com.wujie.springbootlayui.bus.controller;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

import cn.hutool.core.io.FileUtil;
import com.wujie.springbootlayui.bus.entity.StaffError;
import com.wujie.springbootlayui.bus.service.IStaffErrorService;
import com.wujie.springbootlayui.sys.common.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wujie.springbootlayui.bus.entity.AttendStaff;
import com.wujie.springbootlayui.bus.entity.Gate;
import com.wujie.springbootlayui.bus.entity.GateAuthor;
import com.wujie.springbootlayui.bus.entity.Staff;
import com.wujie.springbootlayui.bus.service.IAttendStaffService;
import com.wujie.springbootlayui.bus.service.IGateAuthorService;
import com.wujie.springbootlayui.bus.service.IGateService;
import com.wujie.springbootlayui.bus.service.IStaffService;
import com.wujie.springbootlayui.bus.vo.StaffVo;
import com.wujie.springbootlayui.sys.entity.Dept;
import com.wujie.springbootlayui.sys.service.IDeptService;
import com.wujie.springbootlayui.sys.websocket.WsHandler;

import javax.validation.Valid;

@RestController
@RequestMapping("/staff")
public class StaffController {

    @Autowired
    private IStaffService staffService;

    @Autowired
    private IDeptService deptService;

    @Autowired
    private IGateAuthorService authorService;

    @Autowired
    private IGateService gateService;

    @Autowired
    private IStaffErrorService errorService;

    @Autowired
    private IAttendStaffService attendstaffService;

    @Autowired
    private WsHandler wsHandler;

    /**
     * 查询所有的员工
     *
     * @param staffVo
     * @return
     */
    @RequestMapping("loadAllStaff")
    public Object loadAllStaff(StaffVo staffVo) {
        // 查询当前用户信息
        Subject subject = SecurityUtils.getSubject();
        ActiverUser activerUser = (ActiverUser) subject.getPrincipal();
        Integer belongId = activerUser.getUser().getId();
        // 1.声明一个分页page对象
        IPage<Staff> page = new Page<Staff>(staffVo.getPage(), staffVo.getLimit());
        // 2.声明一个queryWrapper
        QueryWrapper<Staff> queryWrapper = new QueryWrapper<Staff>();
        queryWrapper.orderByDesc("create_time");
        queryWrapper.orderByDesc("id");
        // belongId为1时是超级管理员，可以查所有
        if (belongId != 1) {
            queryWrapper.eq("belong_id", belongId);
            queryWrapper.eq("available", 1);
        }
        queryWrapper.like(StringUtils.isNotBlank(staffVo.getStaffName()), "staff_name", staffVo.getStaffName());
        queryWrapper.like(StringUtils.isNotBlank(staffVo.getPhone()), "phone", staffVo.getPhone());
        queryWrapper.eq(staffVo.getStatus() != null, "status", staffVo.getStatus());
        queryWrapper.eq("available", 1);
        staffService.page(page, queryWrapper);
        List<Staff> list = page.getRecords();
        for (Staff staff : list) {
            Integer deptId = staff.getDeptId();
            if (deptId != null) {
                // 先从缓存中去取，如果缓存中没有就去数据库中取
                Dept one = deptService.getDeptById(deptId);
                // 设置staff的部门名称
                if (one != null) {
                    staff.setDeptName(one.getName());
                } else {
                    staff.setDeptName("部门已删除");
                }
            }
        }
        return new DataGridView(page.getTotal(), list);
    }

    /**
     * 批量添加员工
     *
     * @param staffVo
     * @return
     */
    @RequestMapping("batchAddStaff")
    public ResultObj batchAddStaff(StaffVo staffVo) {
        try {
            Subject subject = SecurityUtils.getSubject();
            ActiverUser activerUser = (ActiverUser) subject.getPrincipal();
            Integer belongId = activerUser.getUser().getId();
            staffVo.setBelongId(belongId);
            if (belongId == null) {
                return new ResultObj(500, "messages.LOGIN_ERROR");
            }
            if (staffVo.getMf() == null) {
                return new ResultObj(500, "file_error");
            }

            String newXmlName = AppFileUtils.renameFile(staffVo.getMf());
            String fileDoc = AppFileUtils.UPLOAD_PATH;
            String dir = fileDoc + newXmlName.split("/")[0];
            String extraDir = dir + "/" + belongId;
            File extra = new File(extraDir);
            if (!extra.exists()) {
                extra.mkdirs();
            }
            // 解析ZIP文件
            String filePath = fileDoc + newXmlName;
            String excel = AppFileUtils.extractZip("staff", filePath, extraDir);

            String[][] excelData = ExcelUtil.getExcelStr(excel);
            if (excelData == null) {
                return new ResultObj(500, "excel_error");
            }
            Dept other = deptService.getOtherDept(belongId);
            int suc = 0;
            List<String> numList = new ArrayList<>();
            for (int i = 2; i < excelData.length; i++) {
                try {

                    String[] strs = excelData[i];
                    //编号
                    String staffNum = strs[0];

                    //姓名
                    String name = strs[1];
                    //性别
                    String sex = strs[2];
                    //部门
                    String deptName = strs[3];
                    //身份证号
                    String idNum = strs[4];
                    if (!StringUtil.isEmpty(idNum) && StringUtils.isNumeric(idNum)) {
                        BigDecimal bd = new BigDecimal(idNum);
                        idNum = bd.toPlainString();
                    }
                    //手机号
                    String phone = strs[5];
                    if (!StringUtil.isEmpty(phone)) {
                        BigDecimal bd = new BigDecimal(phone);
                        phone = bd.toPlainString();
                    }
                    //卡号
                    String cardNum = strs[6];
                    if (!StringUtil.isEmpty(cardNum) && StringUtils.isNumeric(cardNum)) {
                        BigDecimal bd = new BigDecimal(cardNum);
                        cardNum = bd.toPlainString();
                    }
                    //识别头像
                    String facePhoto = strs[7];
                    //strs[8] 出生日期
                    //入职日期
                    String entryDate = strs[9];
                    //职位
                    String staffJob = strs[10];
                    //邮箱
                    String email = strs[11];
                    //提示语
                    String prompt=strs[12];
                    //备注
                    String remark = strs[13];

                    StaffError error = new StaffError();
                    error.setCreateTime(new Date());
                    error.setBelongId(belongId);
                    error.setName(name);
                    error.setCardNum(cardNum);
                    error.setFacePhoto(facePhoto);
                    error.setSex(sex);
                    error.setPhone(phone);
                    error.setIdNum(idNum);
                    error.setDeptName(deptName);

                    if (!StringUtil.isEmpty(staffNum) && !StringUtil.isEmpty(name) && !StringUtil.isEmpty(phone)) {
                        numList.add(staffNum);
                        QueryWrapper<Staff> staffQueryWrapper = new QueryWrapper<>();
                        int errorPoint = 0;
                        staffQueryWrapper.eq("staff_num", staffNum);
                        staffQueryWrapper.eq("available", 1);
                        if (staffService.list(staffQueryWrapper).size() > 0) {
                            errorPoint += 1;
                            error.setRemark("员工编号已被使用");
                            errorService.save(error);
                        }

                        Staff staff = new Staff();
                        staff.setStaffNum(staffNum);
                        staff.setStaffName(name);

                        if (sex.equals("男")) {
                            staff.setSex(1);
                        } else {
                            staff.setSex(2);
                        }

                        if (!StringUtil.isEmpty(deptName)) {
                            QueryWrapper<Dept> queryWrapper = new QueryWrapper<>();
                            queryWrapper.eq("name", deptName);
                            queryWrapper.eq("belong_user", belongId);
                            List<Dept> depts = deptService.list(queryWrapper);
                            if (depts.size() > 0) {
                                staff.setDeptId(depts.get(0).getId());
                            } else {
                                staff.setDeptId(other.getId());
                            }
                        } else {
                            staff.setDeptId(other.getId());
                        }


                        if (!StringUtil.isEmpty(idNum)) {
                            String idFrom = IDCardUtil.IDCardValidate(idNum);
                            if (!idFrom.equals("YES")) {
                                errorPoint += 1;
                                error.setRemark("身份证格式错误");
                                errorService.save(error);
                            } else {
                                QueryWrapper<Staff> verifyWrapper = new QueryWrapper<>();
                                verifyWrapper.eq("id_num", idNum);
                                IPage<Staff> page = new Page<>();
                                if (staffService.page(page, verifyWrapper).getRecords().size() > 0) {
                                    errorPoint += 1;
                                    error.setRemark("身份证已被使用");
                                    errorService.save(error);
                                }
                            }
                        }

                        if (errorPoint == 0) {
                            staff.setIdNum(idNum);
                            staff.setPhone(phone);
                            staff.setCardNum(cardNum);
                            staff.setStaffJob(staffJob);
                            staff.setEmail(email);
                            staff.setRemark(remark);
                            staff.setEntryDate(entryDate);
                            String path = extraDir + "/" + facePhoto;
                            String staffPhoto = AppFileUtils.uploadFile(path);
                            staff.setStaffPhoto(staffPhoto);
                            staff.setBelongId(belongId);
                            staff.setStatus(1);
                            staff.setCreateTime(new Date());
                            staff.setUpdateTime(new Date());
                            staff.setPrompt(prompt);
                            staffService.save(staff);
                            suc++;
                        }

                    } else {

                        if(!StringUtil.isEmpty(staffNum) || !StringUtil.isEmpty(name) || !StringUtil.isEmpty(phone)){
                            //全为空时 不会添加错误记录
                            error.setRemark("手机身份证或者姓名中有为空的");
                            errorService.save(error);
                        }

                    }

                } catch (Exception e) {
                    System.out.println(e);
                }
            }//
            FileUtil.del(extra);
            FileUtil.del(filePath);
            System.out.println("locate");
            for (int i = 0; i < numList.size(); i++) {
                System.out.println(numList.get(i));
            }

            if (suc > 0) {
                return new ResultObj(200, "successfully_import");
            } else {
                return new ResultObj(500, "no_valid_staff");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return  new ResultObj(Constast.ERROR,"messages.ADD_ERROR");
        }

    }

    /**
     * 添加一个员工
     *
     * @param staffVo
     * @return
     */
    @RequestMapping("addStaff")
    public ResultObj addStaff(@Valid StaffVo staffVo, BindingResult bResult) {
        try {

            if (bResult.hasErrors()) {
                String errrorMsg = bResult.getFieldError().getDefaultMessage();
                return new ResultObj(400, errrorMsg);
            }
            Subject subject = SecurityUtils.getSubject();
            ActiverUser activerUser = (ActiverUser) subject.getPrincipal();
            Integer belongId = activerUser.getUser().getId();
            staffVo.setBelongId(belongId);

            QueryWrapper<Staff> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("staff_num", staffVo.getStaffNum());
            queryWrapper.eq("available", 1);
            List<Staff> oldList = staffService.list(queryWrapper);
            if (staffVo.getDeptId() == null) {
                return new ResultObj(500, "staff_add_no_dept");
            }

            if (oldList.size() > 0) {

                if (oldList.get(0).getAvailable() == 0) {
                    return new ResultObj(500, "staff_deleted");
                }

                return new ResultObj(500, "staffNum_used");
            }

            if (!StringUtil.isEmpty(staffVo.getIdNum())) {
                String idFrom = IDCardUtil.IDCardValidate(staffVo.getIdNum());
                if (!idFrom.equals("YES")) {
                    return new ResultObj(500, idFrom);
                } else {
                    QueryWrapper<Staff> verifyWrapper = new QueryWrapper<>();
                    verifyWrapper.eq("id_num", staffVo.getIdNum());
                    IPage<Staff> page = new Page<>();
                    if (staffService.page(page, verifyWrapper).getRecords().size() > 0) {
                        return new ResultObj(500, "Idnum_used");
                    }
                }
            }

            staffVo.setCreateTime(new Date());
            staffVo.setUpdateTime(new Date());
            staffVo.setStatus(1);

            if (staffVo.getStaffPhoto() != null) {
                String newName = AppFileUtils.renameFile(staffVo.getStaffPhoto());
                staffVo.setStaffPhoto(newName);
            }
            staffService.save(staffVo);
            return new ResultObj(Constast.OK,"messages.ADD_SUCCESS");

        } catch (Exception e) {
            e.printStackTrace();
            return  new ResultObj(Constast.ERROR,"messages.ADD_ERROR");
        }

    }

    /**
     * 修改一个员工
     *
     * @param staffVo
     * @return
     */
    @RequestMapping("updateStaff")
    public ResultObj updateStaff(@Valid StaffVo staffVo, BindingResult bResult) {
        try {
            if (bResult.hasErrors()) {
                String errrorMsg = bResult.getFieldError().getDefaultMessage();
                return new ResultObj(400, errrorMsg);
            }
            Integer staffId = staffVo.getId();
            String cardNum = staffVo.getCardNum();
            if (!StringUtil.isEmpty(cardNum)) {
                boolean flag = StringUtil.checkStr(cardNum);
                if (!flag) {
                    return new ResultObj(400, "NCF_error");
                }
            }
            List<Integer> gateIds = authorService.getGateIdByStaffId(staffId);
            for (Integer gateId : gateIds) {
                Gate gate = gateService.getGateById(gateId);
                if (null != gate) {
                    String deviceSn = gate.getDeviceSn();
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("code", 200);
                    //type 1-人员 2-闸机  3-下发更新包 4-心跳回复
                    map.put("type", 1);
                    map.put("identityType", 1);
                    map.put("msg", "员工信息已更新!");
                    map.put("data", staffId);
                    wsHandler.sendInfo(deviceSn, map.toString());
                }
            }
            if (staffVo.getStaffPhoto() != null ) {
                String newName = AppFileUtils.renameFile(staffVo.getStaffPhoto());
                staffVo.setStaffPhoto(newName);
            }


            staffVo.setUpdateTime(new Date());
            staffService.updateById(staffVo);
            return new ResultObj(Constast.OK,"messages.UPDATE_SUCCESS");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultObj(Constast.ERROR,"messages.UPDATE_ERROR");
        }
    }

    /**
     * 删除一个员工
     *
     * @param id 员工的ID
     * @return
     */
    @RequestMapping("deleteStaff")
    public ResultObj deleteStaff(Integer id) {
        try {
            List<Integer> gateIds = authorService.getGateIdByStaffId(id);
            for (Integer gateId : gateIds) {
                Integer authorId = authorService.getIdByGateId(gateId);
                GateAuthor author = authorService.getGateAuthorById(authorId);
                if (author != null) {
                    String staffList = author.getStaffList();
                    List<String> list = Arrays.asList(staffList.split(","));
                    List<String> strs = new ArrayList<String>(list);
                    for (int i = 0; i < strs.size(); i++) {
                        if (strs.get(i).contains(String.valueOf(id))) {
                            strs.remove(i);
                        }
                    }

                    String letfStaff = StringUtils.join(strs, ",");
                    author.setStaffList(letfStaff);
                    authorService.updateById(author);

                }

                Gate gate = gateService.getGateById(gateId);
                if (null != gate) {
                    String deviceSn = gate.getDeviceSn();
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("code", 200);
                    map.put("type", 1);
                    map.put("identityType", 1);
                    map.put("msg", "员工信息已更新!");
                    map.put("data", id);
                    wsHandler.sendInfo(deviceSn, map.toString());
                }
            }

            // 处理员工考勤规则
            AttendStaff attendStaff = attendstaffService.getAttendStaffByStaffId(id);
            if (attendStaff != null) {
                String staffList = attendStaff.getStaffList();
                List<String> list = Arrays.asList(staffList.split(","));
                List<String> strs = new ArrayList<>(list);
                for (int i = 0; i < strs.size(); i++) {
                    if (strs.get(i).contains(String.valueOf(id))) {
                        strs.remove(i);
                    }
                }

                String letfStaff = StringUtils.join(strs, ",");
                attendStaff.setStaffList(letfStaff);
                attendstaffService.updateById(attendStaff);
            }
            // 处理员工信息，如果为超级超级管理员，则删除员工 ；如果不是超级管理员，则更改状态为不可用
            Subject subject = SecurityUtils.getSubject();
            ActiverUser activerUser = (ActiverUser) subject.getPrincipal();
            Integer belongId = activerUser.getUser().getId();
            if (belongId == 1) {
                staffService.removeById(id);
            } else {
                Staff staff = staffService.getStaffById(id);
                staff.setAvailable(0);
                staff.setUpdateTime(new Date());
                staffService.updateById(staff);
            }
            return new ResultObj(Constast.OK,"messages.DELETE_SUCCESS");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultObj(Constast.ERROR,"messages.DELETE_ERROR");
        }
    }

    /**
     * 批量删除员工
     *
     * @param staffVo 选中的员工
     * @return
     */
    @RequestMapping("batchDeleteStaff")
    public ResultObj batchDeleteStaff(StaffVo staffVo) {
        try {
            Collection<Serializable> idList = new ArrayList<Serializable>();
            for (Integer id : staffVo.getIds()) {
                idList.add(id);
            }
            staffService.removeByIds(idList);
            return new ResultObj(Constast.OK,"messages.DELETE_SUCCESS");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultObj(Constast.ERROR,"messages.DELETE_ERROR");
        }
    }

    /**
     * 加载所有员工的下拉列表
     *
     * @return
     */
    @RequestMapping("loadAllStaffForSelect")
    public DataGridView loadAllStaffForSelect() {
        QueryWrapper<Staff> queryWrapper = new QueryWrapper<Staff>();
        queryWrapper.eq("available", Constast.AVAILABLE_TRUE);
        List<Staff> list = staffService.list(queryWrapper);
        return new DataGridView(list);
    }

}
