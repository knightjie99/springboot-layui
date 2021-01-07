package com.wujie.springbootlayui.bus.controller;

import cn.hutool.core.io.FileUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wujie.springbootlayui.bus.entity.*;
import com.wujie.springbootlayui.bus.service.IAttendService;
import com.wujie.springbootlayui.bus.service.IBackupService;
import com.wujie.springbootlayui.bus.service.IPassRecordService;
import com.wujie.springbootlayui.bus.service.IStaffService;
import com.wujie.springbootlayui.bus.vo.BackupVo;
import com.wujie.springbootlayui.sys.common.*;
import com.wujie.springbootlayui.sys.config.DynamicSchedule;
import com.wujie.springbootlayui.sys.service.IRoleService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/backup")
public class BackupController {

    @Autowired
    private IBackupService backupService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IStaffService staffService;

    @Autowired
    private IPassRecordService recordService;

    @Autowired
    private IAttendService attendService;

    @RequestMapping("loadAllBackup")
    public DataGridView loadAllAttendStaff() {
        Subject subject = SecurityUtils.getSubject();
        ActiverUser activerUser = (ActiverUser) subject.getPrincipal();
        Integer belongId = activerUser.getUser().getId();
        List<Integer>  roles = roleService.queryUserRoleIdsByUid(belongId);

        QueryWrapper<Backup> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("create_time");
        if (!roles.contains(1)) {
            queryWrapper.eq("belong_id", belongId);
        }
        List<Backup> backups = backupService.list(queryWrapper);
        return new DataGridView((long) backups.size(), backups);
    }

    @RequestMapping("addBackup")
    public ResultObj addBackup(BackupVo backupVo) {

        try {
            Subject subject = SecurityUtils.getSubject();
            ActiverUser activerUser = (ActiverUser) subject.getPrincipal();
            Integer belongId = activerUser.getUser().getId();
            backupVo.setBackupTime(new Date());
            backupVo.setUpdateTime(new Date());
            backupVo.setCreateTime(new Date());
            backupVo.setBelongId(belongId);
            String nowtime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            String dirPath = "backup/" + belongId + "/" + nowtime;
            File fileDoc = new File(AppFileUtils.UPLOAD_PATH + dirPath);
            backupVo.setFilePath(dirPath);


            if (!fileDoc.exists()) {
                fileDoc.mkdirs();
            }

            if (backupVo.getBackupWay() == 1) {
                if (backupVo.getBackupContent().contains("1")) {
                    QueryWrapper<Staff> staffQueryWrapper = new QueryWrapper<>();
                    staffQueryWrapper.eq("belong_id", belongId);
                    List<Staff> staffList = staffService.list(staffQueryWrapper);
                    if (staffList.size() > 0) {
                        backupService.backupStaff(fileDoc, staffList);

                    }
                }
                if (backupVo.getBackupContent().contains("2")) {
                    QueryWrapper<PassRecord> recordQueryWrapper = new QueryWrapper<>();
                    recordQueryWrapper.eq("belong_id", belongId);
                    List<PassRecord> records = recordService.list(recordQueryWrapper);
                    if (records.size() > 0) {
                        backupService.backupPassRecord(fileDoc, records);

                    }
                }
                if (backupVo.getBackupContent().contains("3")) {
                    QueryWrapper<Attend> attendQueryWrapper = new QueryWrapper<>();
                    attendQueryWrapper.eq("belong_id", belongId);
                    List<Attend> attends = attendService.list(attendQueryWrapper);
                    if (attends.size() > 0) {
                        backupService.backupAttend(fileDoc, attends);
                    }
                }
            } else if (backupVo.getBackupWay() == 2) {
                switch (backupVo.getFrequency()) {
                    case 1:
                        backupVo.setCron("0 30 22 ? * SUN");

                        break;
                    case 2:
                        backupVo.setCron("0 0 0 1 * ?");
                        break;
                }
            }

            backupService.save(backupVo);

            if (backupVo.getBackupWay() == 2) {
                DynamicSchedule.start(backupVo.getId(), backupVo.getCron(), backupVo.getBackupContent(), belongId);
            }

            return new ResultObj(Constast.OK,"messages.ADD_SUCCESS");
        } catch (Exception e) {
            e.printStackTrace();
            return  new ResultObj(Constast.ERROR,"messages.ADD_ERROR");
        }
    }

    @RequestMapping("updateBackup")
    public ResultObj updateBackup(BackupVo backupVo) {

        try {

            Subject subject = SecurityUtils.getSubject();
            ActiverUser activerUser = (ActiverUser) subject.getPrincipal();
            Integer belongId = activerUser.getUser().getId();

            backupVo.setUpdateTime(new Date());
            Backup backup = backupService.getById(backupVo.getId());


            DynamicSchedule.cancel(backupVo.getId());//先取消
            if (backupVo.getBackupWay() == 2) {
                switch (backupVo.getFrequency()) {
                    case 1:
                        backupVo.setCron("0 30 22 ? * SUN");

                        break;
                    case 2:
                        backupVo.setCron("0 0 0 1 * ?");
                        break;
                }
                DynamicSchedule.start(backupVo.getId(), backupVo.getCron(), backupVo.getBackupContent(), backupVo.getBelongId());
            } else {
                backupVo.setCron("");
                File fileDoc = new File(AppFileUtils.UPLOAD_PATH+backup.getFilePath());
                FileUtil.clean(fileDoc);

                if (backupVo.getBackupContent().contains("1")) {
                    QueryWrapper<Staff> staffQueryWrapper = new QueryWrapper<>();
                    staffQueryWrapper.eq("belong_id", belongId);
                    List<Staff> staffList = staffService.list(staffQueryWrapper);
                    if (staffList.size() > 0) {
                        backupService.backupStaff(fileDoc, staffList);

                    }
                }
                if (backupVo.getBackupContent().contains("2")) {
                    QueryWrapper<PassRecord> recordQueryWrapper = new QueryWrapper<>();
                    recordQueryWrapper.eq("belong_id", belongId);
                    List<PassRecord> records = recordService.list(recordQueryWrapper);
                    if (records.size() > 0) {
                        backupService.backupPassRecord(fileDoc, records);

                    }
                }
                if (backupVo.getBackupContent().contains("3")) {
                    QueryWrapper<Attend> attendQueryWrapper = new QueryWrapper<>();
                    attendQueryWrapper.eq("belong_id", belongId);
                    List<Attend> attends = attendService.list(attendQueryWrapper);
                    if (attends.size() > 0) {
                        backupService.backupAttend(fileDoc, attends);
                    }
                }
            }

            backupService.updateById(backupVo);


            return new ResultObj(Constast.OK,"messages.UPDATE_SUCCESS");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultObj(Constast.ERROR,"messages.UPDATE_ERROR");
        }
    }

    @RequestMapping("delete")
    public ResultObj delete(Integer id) {
        try {
            backupService.removeById(id);
            DynamicSchedule.cancel(id);
            return  new ResultObj(Constast.OK,"messages.DELETE_SUCCESS");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultObj(Constast.ERROR,"messages.DELETE_ERROR");
        }
    }

    @RequestMapping("recover")
    public ResultObj recover(Integer id) {
        try {
            Subject subject = SecurityUtils.getSubject();
            ActiverUser activerUser = (ActiverUser) subject.getPrincipal();
            Integer belongId = activerUser.getUser().getId();

            Backup backup = backupService.getById(id);
            if (backup.getBelongId() != belongId) {
                return new ResultObj(500, "no_auth");
            }

            if (backup.getBackupContent().contains("1")) {
                String filePath = AppFileUtils.UPLOAD_PATH + backup.getFilePath() + "/staff.xls";
                String[][] excelData = ExcelUtil.getExcelStr(filePath);

                QueryWrapper<Staff> staffQueryWrapper = new QueryWrapper<>();
                staffQueryWrapper.eq("belong_id", belongId);
                List<Staff> staffList = staffService.list(staffQueryWrapper);

                Collection<Serializable> idList = new ArrayList<Serializable>();
                for (Staff toremove : staffList) {
                    idList.add(toremove.getId());
                }
                if (idList.size() > 0) {
                    staffService.removeByIds(idList);
                }

                for (int i = 1; i < excelData.length; i++) {
                    if (!StringUtil.isEmpty(excelData[i][0])) {
                        String[] strs = excelData[i];
                        Staff staff = new Staff();
                        staff.setId(Integer.parseInt(strs[0]));
                        staff.setStaffNum(strs[1]);
                        staff.setStaffName(strs[2]);
                        if (!StringUtil.isEmpty(strs[3]) && !strs[3].equals("null")) {
                            staff.setDeptId(Integer.parseInt(strs[3]));
                        }
                        if (!StringUtil.isEmpty(strs[4]) && !strs[4].equals("null")) {
                            staff.setSex(Integer.parseInt(strs[4]));
                        }
                        staff.setIdNum(strs[5]);
                        staff.setPhone(strs[6]);
                        staff.setCardNum(strs[7]);
                        staff.setStaffPhoto(strs[8]);
                        if (!StringUtil.isEmpty(strs[9]) && !strs[9].equals("null")) {
                            staff.setStatus(Integer.parseInt(strs[9]));
                        }
                        staff.setBelongId(belongId);
                        staff.setRemark(strs[10]);
                        staff.setCreateTime(DateUtil.parseStringDayToDate(strs[11]));
                        staff.setUpdateTime(DateUtil.parseStringDayToDate(strs[12]));
                        staff.setAvailable(Integer.parseInt(strs[13]));
                        staff.setStaffJob(strs[14]);
                        if (!StringUtil.isEmpty(strs[15])) {
                            staff.setEntryDate(strs[15]);
                        }
                        staff.setEmail(strs[16]);

                        staffService.save(staff);
                    }
                }

            }

            if (backup.getBackupContent().contains("2")) {
                String filePath = AppFileUtils.UPLOAD_PATH + backup.getFilePath() + "/passRecords.xls";
                String[][] excelData = ExcelUtil.getExcelStr(filePath);

                QueryWrapper<PassRecord> recordQueryWrapper = new QueryWrapper<>();
                recordQueryWrapper.eq("belong_id", belongId);
                List<PassRecord> recordList = recordService.list(recordQueryWrapper);

                Collection<Serializable> idList = new ArrayList<Serializable>();
                for (PassRecord toremove : recordList) {
                    idList.add(toremove.getId());
                }
                if (idList.size() > 0) {
                    recordService.removeByIds(idList);
                }

                for (int i = 1; i < excelData.length; i++) {
                    if (!StringUtil.isEmpty(excelData[i][0])) {
                        String[] strs = excelData[i];
                        PassRecord record = new PassRecord();
                        record.setId(Integer.parseInt(strs[0]));
                        record.setPassTime(DateUtil.parseStringDayToDate(strs[1]));

                        if (!StringUtil.isEmpty(strs[2]) && !strs[2].equals("null")) {
                            record.setSex(Integer.parseInt(strs[2]));
                        }
                        record.setName(strs[3]);
                        record.setIdNum(strs[4]);
                        record.setPhone(strs[5] + "");
                        record.setCardNum(strs[6]);
                        record.setFacePhoto(strs[7]);
                        if (!StringUtil.isEmpty(strs[8]) && !strs[8].equals("null")) {
                            record.setGateId(Integer.parseInt(strs[8]));
                        }
                        if (!StringUtil.isEmpty(strs[9]) && !strs[9].equals("null")) {
                            record.setPassWay(Integer.parseInt(strs[9]));
                        }
                        if (!StringUtil.isEmpty(strs[10]) && !strs[10].equals("null")) {
                            record.setIdentityType(Integer.parseInt(strs[10]));
                        }
                        record.setTemperature(strs[11] + "");
                        if (!StringUtil.isEmpty(strs[12]) && !strs[12].equals("null")) {
                            record.setPassDirection(Integer.parseInt(strs[12]));
                        }
                        record.setBelongId(belongId);


                        recordService.save(record);

                    }
                }

            }

            if (backup.getBackupContent().contains("3")) {
                String filePath = AppFileUtils.UPLOAD_PATH + backup.getFilePath() + "/attends.xls";
                String[][] excelData = ExcelUtil.getExcelStr(filePath);

                QueryWrapper<Attend> attendQueryWrapper = new QueryWrapper<>();
                attendQueryWrapper.eq("belong_id", belongId);
                List<Attend> attendList = attendService.list(attendQueryWrapper);

                Collection<Serializable> idList = new ArrayList<Serializable>();
                for (Attend toremove : attendList) {
                    idList.add(toremove.getId());
                }
                if (idList.size() > 0) {
                    attendService.removeByIds(idList);
                }

                for (int i = 1; i < excelData.length; i++) {
                    if (!StringUtil.isEmpty(excelData[i][0])) {
                        String[] strs = excelData[i];
                        Attend attend = new Attend();
                        attend.setId(Integer.parseInt(strs[0]));
                        attend.setStaffId(Integer.parseInt(strs[1]));
                        attend.setStaffName(strs[2]);
                        if (!StringUtil.isEmpty(strs[3]) && !strs[3].equals("null")) {
                            attend.setDeptId(Integer.parseInt(strs[3]));
                        }
                        attend.setAttendDate(DateUtil.parseStringDayToDate(strs[4]));
                        if (!StringUtil.isEmpty(strs[5]) && !strs[5].equals("null")) {
                            attend.setOnStatus(Integer.parseInt(strs[5]));
                        }

                        if (!StringUtil.isEmpty(strs[6]) && !strs[6].equals("null")) {
                            attend.setAttendHour(Double.parseDouble(strs[6]));
                        }
                        if (!StringUtil.isEmpty(strs[7]) && !strs[7].equals("null")) {
                            attend.setOnTime(DateUtil.parseStringDayToDate(strs[7]));
                        }

                        if (!StringUtil.isEmpty(strs[8]) && !strs[8].equals("null")) {
                            attend.setOnGate(Integer.parseInt(strs[8]));
                        }
                        attend.setOnPhoto(strs[9]);
                        if (!StringUtil.isEmpty(strs[10]) && !strs[10].equals("null")) {
                            attend.setOffTime(DateUtil.parseStringDayToDate(strs[10]));
                        }
                        if (!StringUtil.isEmpty(strs[11]) && !strs[11].equals("null")) {
                            attend.setOffGate(Integer.parseInt(strs[11]));
                        }
                        attend.setOffPhoto(strs[12]);
                        if (!StringUtil.isEmpty(strs[13]) && !strs[13].equals("null")) {
                            attend.setOffStatus(Integer.parseInt(strs[13]));
                        }
                        attend.setBelongId(belongId);

                        attendService.save(attend);
                    }
                }
            }

            return new ResultObj(200, "use_backup_success");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultObj(500, "use_backup_failed");
        }
    }

}
