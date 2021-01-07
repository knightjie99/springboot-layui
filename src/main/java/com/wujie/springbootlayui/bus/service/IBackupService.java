package com.wujie.springbootlayui.bus.service;

import java.io.File;
import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wujie.springbootlayui.bus.entity.Attend;
import com.wujie.springbootlayui.bus.entity.Backup;
import com.wujie.springbootlayui.bus.entity.PassRecord;
import com.wujie.springbootlayui.bus.entity.Staff;

public interface IBackupService extends IService<Backup>{
	
	public void backupStaff(File fileDoc, List<Staff> list);
	
	public void backupPassRecord(File fileDoc, List<PassRecord> list);
	
	public void backupAttend(File fileDoc, List<Attend> list);

}
