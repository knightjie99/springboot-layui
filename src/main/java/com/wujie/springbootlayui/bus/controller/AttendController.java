package com.wujie.springbootlayui.bus.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wujie.springbootlayui.bus.entity.*;
import com.wujie.springbootlayui.bus.service.*;
import com.wujie.springbootlayui.bus.vo.AttendSumVo;
import com.wujie.springbootlayui.bus.vo.AttendVo;
import com.wujie.springbootlayui.sys.common.*;
import com.wujie.springbootlayui.sys.config.MybatisPlusConfig;
import com.wujie.springbootlayui.sys.entity.Dept;
import com.wujie.springbootlayui.sys.service.IDeptService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/attend")
public class AttendController {

	@Autowired
	private IDeptService deptService;

    @Autowired
    private IAttendStaffService attendStaffService;

	@Autowired
	private IAttendService attendService;

	@Autowired
	private IStaffService staffService;

	@Autowired
	private IGateService gateService;

    @Autowired
    private IAttendRuleService ruleService;

	/**
	 * 查询所有的员工
	 * 
	 * @param staffVo
	 * @return
	 */
	@RequestMapping("loadAllAttendSum")
	public DataGridView loadAllAttendSum(AttendSumVo attendSumVo) {
		// 查询当前用户信息
		Subject subject = SecurityUtils.getSubject();
		ActiverUser activerUser = (ActiverUser) subject.getPrincipal();
		Integer belongId = activerUser.getUser().getId();
		// 1.声明一个分页page对象
		IPage<Staff> page = new Page<Staff>(attendSumVo.getPage(), attendSumVo.getLimit());
		QueryWrapper<Staff> queryWrapper = new QueryWrapper<Staff>();
		queryWrapper.eq("available", 1);
		queryWrapper.eq("status", 1);
		queryWrapper.eq("belong_id", belongId);

		queryWrapper.like(StringUtils.isNotBlank(attendSumVo.getStaffName()), "staff_name", attendSumVo.getStaffName());

		queryWrapper.eq(attendSumVo.getDeptId() != null, "dept_id", attendSumVo.getDeptId());
		staffService.page(page, queryWrapper);
		List<Staff> staffs = page.getRecords();
		
		List<AttendSum> sumList = new ArrayList<AttendSum>();
		String tName = "bus_attend_" + belongId;
		List<Integer> monthWeek =DateUtil.getSundays(new Date());
		for (Staff staff : staffs) {
			Integer staffId = staff.getId();


			attendSumVo.setStaffId(staffId);
			if (StringUtil.isEmpty(attendSumVo.getStartTime())) {
				attendSumVo.setStartTime(DateUtil.getFirstDayOfMonth());
			}
			if (StringUtil.isEmpty(attendSumVo.getEndTime())) {
				attendSumVo.setEndTime(new Date());
			}
			attendSumVo.settName(tName);
			AttendSum sum = attendService.getAttendSumByStaffId(attendSumVo);

			Integer deptId = sum.getDeptId();
			if (deptId != null) {
				// 先从缓存中去取，如果缓存中没有就去数据库中取
				Dept one = deptService.getDeptById(deptId);
				// 设置staff的部门名称
				if (one != null) {
					sum.setDeptName(one.getName());
				} else {
					sum.setDeptName("部门已删除");
				}

			}

			try {
			    int AttendSum=0;
                AttendStaff attendStaff = attendStaffService.getAttendStaffByStaffId(staffId);
                if(attendStaff!=null){
                   String[] cycle= attendStaff.getCycleDate().split(",");
                    for (int i = 0; i < cycle.length; i++) {
                        Integer day = Integer.parseInt(cycle[i]);
                        AttendSum+=monthWeek.get(day-1);
                    }
                }
                sum.setAttendDay((double) AttendSum);
                sum.setRestDay(sum.getAttendDay()-sum.getActualDay());



            }catch (Exception e){
			    e.printStackTrace();
            }



			sumList.add(sum);
		}

		return new DataGridView((long) page.getTotal(), sumList);
	}

	@RequestMapping("loadAllAttendByStaffId")
	public DataGridView loadAllAttendByStaffId(Integer staffId, String rangeStart, String rangeEnd) {
		Subject subject = SecurityUtils.getSubject();
		ActiverUser activerUser = (ActiverUser) subject.getPrincipal();
		Integer belongId = activerUser.getUser().getId();
		QueryWrapper<Attend> queryWrapper = new QueryWrapper<Attend>();
		IPage<Attend> page = new Page<>();

		queryWrapper.eq("staff_id", staffId);
		queryWrapper.orderByDesc("attend_date");

		if (StringUtil.isEmpty(rangeStart)) {
			queryWrapper.ge("attend_date", DateUtil.getFirstDayOfMonth());
		} else {
			queryWrapper.ge("attend_date", rangeStart);
		}
		// 如果结束时间为空，获取当前时间为节点
		if (StringUtil.isEmpty(rangeEnd)) {
			queryWrapper.le("attend_date", new Date());
		} else {
			queryWrapper.le("attend_date", rangeEnd);
		}
		String tName = "bus_attend_" + belongId;
		MybatisPlusConfig.attendTName.set(tName);
		attendService.page(page, queryWrapper);
		List<Attend> list = page.getRecords();

		for (int i = 0; i <list.size() ; i++) {
			Attend attend = list.get(i);
			Integer onGate = attend.getOnGate();
			Integer offGate = attend.getOffGate();

			if(onGate!=null){
				Gate gate =gateService.getGateById(onGate);
				if(gate!=null){
					attend.setOnGateName(gate.getGateName());
				}else{
					attend.setOnGateName("闸机已删除");
				}
			}
			if(offGate!=null){
				Gate gate =gateService.getGateById(offGate);
				if(gate!=null){
					attend.setOffGateName(gate.getGateName());
				}else{
					attend.setOffGateName("闸机已删除");
				}
			}

		}

		return new DataGridView((long) list.size(), list);
	}

	@RequestMapping("batchDelete")
	public ResultObj batchDelete(AttendVo attendVo) {
		Subject subject = SecurityUtils.getSubject();
		ActiverUser activerUser = (ActiverUser) subject.getPrincipal();
		Integer belongId = activerUser.getUser().getId();
		try {
			Collection<Serializable> idList = new ArrayList<Serializable>();
			for (Integer id : attendVo.getIds()) {
				idList.add(id);
			}
			String tName = "bus_attend_" + belongId;
			MybatisPlusConfig.attendTName.set(tName);
			attendService.removeByIds(idList);
			return new ResultObj(Constast.OK, "messages.DELETE_SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultObj(Constast.ERROR, "messages.DELETE_ERROR");
		}
	}

}
