package com.wujie.springbootlayui.sys.config;

import com.wujie.springbootlayui.bus.entity.PassRecord;
import com.wujie.springbootlayui.bus.entity.Staff;
import com.wujie.springbootlayui.bus.service.IAttendService;
import com.wujie.springbootlayui.bus.service.IPassRecordService;
import com.wujie.springbootlayui.bus.service.IStaffService;
import com.wujie.springbootlayui.sys.websocket.WsSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@Configuration // 1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling // 2.开启定时任务
public class ScheduleTask {

	private final static Logger logger = LoggerFactory.getLogger(ScheduleTask.class);

	@Autowired
	private IStaffService staffService;

	@Autowired
	private IPassRecordService passRecordService;

	@Autowired
	private IAttendService attendService;

	// 3.添加定时任务(每天晚上十一点执行)
	@Scheduled(cron = "0 30 23 * * ?")
	// 或直接指定时间间隔，例如：5秒
//	 @Scheduled(fixedRate=5000)
	private void attendTasks() {
		logger.info("开始考勤汇总........");
		// 获取所有在职员工
		List<Staff> staffs = staffService.getAllWorkStaffs();
		for (Staff staff : staffs) {
			List<PassRecord> records = passRecordService.getCurrentDayPassRecord(staff);
			//0-无记录 1-条记录
			int size = records.size();
			String tName2 = "bus_attend_" + staff.getBelongId();
			MybatisPlusConfig.attendTName.set(tName2);
			if(size == 0) {
				System.out.println(staff.getStaffName()+" "+0);
				attendService.addAttendNoPassrecord(staff);	
			}
			if(size == 1) {
				System.out.println(staff.getStaffName()+" "+1);
				PassRecord record = records.get(0);
				attendService.addAttendOnePassrecord(staff,record);
			}
			
			if(size >=2) {
				System.out.println(staff.getStaffName()+" "+2);
				attendService.addAttendTwoPassrecord(staff,records);
			}
			
		}
		logger.info("结束考勤汇总........");
	}

	// 3.添加定时任务(每天晚上十一点执行)
	@Scheduled(cron = "0 */1 * * * ?")
	// 或直接指定时间间隔，例如：5秒
	// @Scheduled(fixedRate=5000)
	private void checkMapTasks() {
		logger.info("开始检查长链接........");
		WsSessionManager.checkWsStatus();
		logger.info("结束检查长链接........");
	}
}
