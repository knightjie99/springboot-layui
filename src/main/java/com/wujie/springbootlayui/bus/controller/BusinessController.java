package com.wujie.springbootlayui.bus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("bus")
public class BusinessController {
	
	@RequestMapping("toPersonErrorManager")
	public String toPersonErrorManager(){
		return "business/personError/personErrorManager";
	}
	
	/**
	 * 跳转到员工管理页面
	 * @return
	 */
	@RequestMapping("toStaffManager")
    public String toStaffManager(){
        return "business/staff/staffManager";
    }
	
	/**
	 * 跳转到访客管理页面
	 * @return
	 */
	@RequestMapping("toVisitorManager")
    public String toVisitorManager(){
        return "business/visitor/visitorManager";
    }

	@RequestMapping("toVipVisitorManager")
	public String toVipVisitorManager(){
		return "business/visitor/vipVisitorManager";
	}

	@RequestMapping("toBlacklistManager")
	public String toBlacklistManager(){
		return "business/blacklist/blacklistManager";
	}
	
	/**
	 * 跳转到闸机管理界面
	 * @return
	 */
	@RequestMapping("toGateManager")
	public String toGateManager(){
        return "business/gate/gateManager";
    }
	
	/**
	 * 跳转到闸机授权界面
	 */
	@RequestMapping("toGateAuthorManager")
	public String toGateAuthorManager() {
		return "business/gateAuthor/gateAuthorManager";
	}
	
	/**
	 *跳转到通行记录 
	 */
	@RequestMapping("toPassRecordManager")
	public String toPassRecordManager(){
        return "business/passRecord/passRecordManager";
    }
	
	/**
	 * 跳转到考勤记录
	 */
	@RequestMapping("toAttendManager")
	public String toAttendManager(){
        return "business/attend/attendManager";
    }
	
	/**
	 * 跳转到考勤规则
	 */
	@RequestMapping("toAttendRuleManager")
	public String toAttendRuleManager() {
		return "business/attendRule/attendRuleManager";
	}
	
	/**
	 * 跳转到人员排班
	 */
	@RequestMapping("toAttendStaffManager")
	public String toAttendStaffManager() {
		return "business/attendStaff/attendStaffManager";
	}
	
	/**
	 * 跳转到发卡管理
	 */
	@RequestMapping("toStaffCardManager")
	public String toStaffCardManager(){
        return "business/staffCard/staffCardManager";
    }
	
	@RequestMapping("toStaffError")
	public String toStaffError(){
		return "business/staffError/staffErrorManager";
	}


	@RequestMapping("toBackupManager")
	public String toBackup(){
		return "business/backup/backupManager";
	}


}
