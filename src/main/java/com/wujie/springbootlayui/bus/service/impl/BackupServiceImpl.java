package com.wujie.springbootlayui.bus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wujie.springbootlayui.bus.entity.Attend;
import com.wujie.springbootlayui.bus.entity.Backup;
import com.wujie.springbootlayui.bus.entity.PassRecord;
import com.wujie.springbootlayui.bus.entity.Staff;
import com.wujie.springbootlayui.bus.mapper.BackupMapper;
import com.wujie.springbootlayui.bus.service.IBackupService;
import com.wujie.springbootlayui.sys.common.DateUtil;
import com.wujie.springbootlayui.sys.common.ExcelUtil;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Service
public class BackupServiceImpl extends ServiceImpl<BackupMapper,Backup> implements IBackupService {
	
	@Override
	public boolean save(Backup record) {
		return super.save(record);
	}

	@Override
	public boolean updateById(Backup record) {
		return super.updateById(record);
	}

	@Override
	public boolean removeById(Serializable id) {
		return super.removeById(id);
	}

	@Override
	public Backup getById(Serializable id) {
		return super.getById(id);
	}

	@Override
	public boolean removeByIds(Collection<? extends Serializable> idList) {
		return super.removeByIds(idList);
	}

	@Override
	public void backupStaff(File fileDoc, List<Staff> staffList) {
		System.out.println("start backup at "+fileDoc.getAbsolutePath());
		OutputStream stream = null;
		String[] title = { "id", "staff_num", "staff_name", "dept_id", "sex", "id_num", "phone", "card_num",
				"staff_photo", "status", "remark", "create_time", "update_time", "available", "staff_job",
				"entry_date", "email" };
		String[][] content = new String[staffList.size()][17];
		for (int i = 0; i < staffList.size(); i++) {
			Staff staff = staffList.get(i);
			content[i][0] = staff.getId() + "";
			content[i][1] = staff.getStaffNum();
			content[i][2] = staff.getStaffName();
			content[i][3] = staff.getDeptId() + "";
			content[i][4] = staff.getSex() + "";
			content[i][5] = staff.getIdNum();
			content[i][6] = staff.getPhone();
			content[i][7] = staff.getCardNum();
			content[i][8] = staff.getStaffPhoto();
			content[i][9] = staff.getStatus() + "";
			content[i][10] = staff.getRemark();
			content[i][11] = DateUtil.parseDateToString(staff.getCreateTime());
			content[i][12] = DateUtil.parseDateToString(staff.getUpdateTime());
			content[i][13] = staff.getAvailable() + "";
			content[i][14] = staff.getStaffJob();
		    content[i][15] =staff.getEntryDate();
			content[i][16] = staff.getEmail();

		}

		HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook("员工", title, content, null);
		File file = new File(fileDoc.getAbsolutePath() + "/staff.xls");
		try {
			stream = new FileOutputStream(file);
			wb.write(stream);
		} catch (Exception e) {
            e.printStackTrace();
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
	}

	@Override
	public void backupPassRecord(File fileDoc, List<PassRecord> records) {
		System.out.println("start backup at "+fileDoc.getAbsolutePath());
		OutputStream stream = null;
		String[] title = { "id", "pass_time", "sex", "name", "id_num", "phone", "card_num", "face_photo",
				"gate_id", "pass_way", "identity_type", "temperature", "pass_direction" };
		String[][] content = new String[records.size()][13];
		for (int i = 0; i < records.size(); i++) {
			PassRecord record = records.get(i);

			content[i][0] = record.getId() + "";
			content[i][1] = DateUtil.parseDateToString(record.getPassTime());
			content[i][2] = record.getSex() + "";
			content[i][3] = record.getName();
			content[i][4] = record.getIdNum();
			content[i][5] = record.getPhone();
			content[i][6] = record.getCardNum();
			content[i][7] = record.getFacePhoto();
			content[i][8] = record.getGateId() + "";
			content[i][9] = record.getPassWay() + "";
			content[i][10] = record.getIdentityType() + "";
			content[i][11] = record.getTemperature();
			content[i][12] = record.getPassDirection() + "";

		}

		HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook("通行记录", title, content, null);
		File file = new File(fileDoc.getAbsolutePath() + "/passRecords.xls");
		try {
			stream = new FileOutputStream(file);
			wb.write(stream);
		} catch (Exception e) {

		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	@Override
	public void backupAttend(File fileDoc, List<Attend> attends) {
		System.out.println("start backup at "+fileDoc.getAbsolutePath());
		OutputStream stream = null;
		String[] title = { "id", "staff_id", "staff_name", "dept_id", "attend_date", "status",
				"attend_hour", "on_time", "on_gate", "on_photo", "off_time", "off_gate", "off_photo" };
		String[][] content = new String[attends.size()][13];
		for (int i = 0; i < attends.size(); i++) {
			Attend attend = attends.get(i);
			content[i][0] = attend.getId() + "";
			content[i][1] = attend.getStaffId() + "";
			content[i][2] = attend.getStaffName();
			content[i][3] = attend.getDeptId() + "";
			content[i][4] = DateUtil.parseDateToString(attend.getAttendDate());
			content[i][5] = attend.getOnStatus() + "";
			content[i][6] = attend.getAttendHour() + "";
			if (attend.getOnTime() != null) {
				content[i][7] = DateUtil.parseDateToString(attend.getOnTime());
			}
			content[i][8] = attend.getOnGate() + "";
			content[i][9] = attend.getOnPhoto();

			if (attend.getOffTime() != null) {
				content[i][10] = DateUtil.parseDateToString(attend.getOffTime());
			}
			content[i][11] = attend.getOffGate() + "";
			content[i][12] = attend.getOffPhoto();

			content[i][13] = attend.getOffStatus() + "";
		}
		HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook("考勤记录", title, content, null);
		File file = new File(fileDoc.getAbsolutePath() + "/attends.xls");
		try {
			stream = new FileOutputStream(file);
			wb.write(stream);
		} catch (Exception e) {

		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
		
}
