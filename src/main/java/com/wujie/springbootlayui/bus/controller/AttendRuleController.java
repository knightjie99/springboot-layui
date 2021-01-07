package com.wujie.springbootlayui.bus.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import com.wujie.springbootlayui.bus.entity.AttendStaff;
import com.wujie.springbootlayui.bus.service.IAttendStaffService;
import com.wujie.springbootlayui.sys.common.Constast;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wujie.springbootlayui.bus.entity.AttendRule;
import com.wujie.springbootlayui.bus.service.IAttendRuleService;
import com.wujie.springbootlayui.bus.vo.AttendRuleVo;
import com.wujie.springbootlayui.sys.common.ActiverUser;
import com.wujie.springbootlayui.sys.common.DataGridView;
import com.wujie.springbootlayui.sys.common.ResultObj;

@RestController
@RequestMapping("/attendRule")
public class AttendRuleController {

	@Autowired
	private IAttendRuleService ruleService;
	
	@Autowired
	private IAttendStaffService attendStaffService;

	/**
	 * 查询所有考勤规则
	 * 
	 * @param ruleVo
	 * @return
	 */
	@RequestMapping("loadAllAttendRule")
	public DataGridView loadAllAttendRule(AttendRuleVo ruleVo) {
		// 查询当前用户信息
		Subject subject = SecurityUtils.getSubject();
		ActiverUser activerUser = (ActiverUser) subject.getPrincipal();
		Integer belongId = activerUser.getUser().getId();
		// 1.声明一个分页page对象
		IPage<AttendRule> page = new Page<AttendRule>(ruleVo.getPage(), ruleVo.getLimit());
		// 2.声明一个queryWrapper
		QueryWrapper<AttendRule> queryWrapper = new QueryWrapper<AttendRule>();
		queryWrapper.orderByDesc("create_time");
		// belongId为1时是超级管理员，可以查所有
		if (belongId != 1) {
			queryWrapper.eq("belong_id", belongId);
		}
		ruleService.page(page, queryWrapper);
		return new DataGridView(page.getTotal(), page.getRecords());
	}

	/**
	 * 添加考勤规则
	 * 
	 * @param ruleVo
	 * @return
	 */
	@RequestMapping("addAttendRule")
	public ResultObj addAttendRule(@Valid AttendRuleVo ruleVo,BindingResult bResult) {
		try {
			
			if(bResult.hasErrors()) {
				String errrorMsg = bResult.getFieldError().getDefaultMessage();
			    return new ResultObj(400,errrorMsg);
			}
			Subject subject = SecurityUtils.getSubject();
			ActiverUser activerUser = (ActiverUser) subject.getPrincipal();
			Integer belongId = activerUser.getUser().getId();
			ruleVo.setBelongId(belongId);
			ruleVo.setCreateTime(new Date());
			ruleVo.setUpdateTime(new Date());
			ruleService.save(ruleVo);
			return new ResultObj(Constast.OK,"messages.ADD_SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
			return  new ResultObj(Constast.ERROR,"messages.ADD_ERROR");
		}
	}

	/**
	 * 修改考勤规则
	 * 
	 * @param ruleVo
	 * @return
	 */
	@RequestMapping("updateAttendRule")
	public ResultObj updateAttendRule(AttendRuleVo ruleVo) {
		try {
			ruleVo.setUpdateTime(new Date());
			ruleService.updateById(ruleVo);
			return new ResultObj(Constast.OK,"messages.UPDATE_SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultObj(Constast.ERROR,"messages.UPDATE_ERROR");
		}
	}

	/**
	 * 删除考勤规则
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("deleteAttendRule")
	public ResultObj deleteAttendRule(Integer id) {

		QueryWrapper<AttendStaff> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("rule_id",id);
		List<AttendStaff> attendStaffList = attendStaffService.list(queryWrapper);
		if(attendStaffList.size()>0){
			return new ResultObj(500,"need_to_delete_attendStaff");
		}

		try {
			ruleService.removeById(id);
			return  new ResultObj(Constast.OK,"messages.DELETE_SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultObj(Constast.ERROR,"messages.DELETE_ERROR");
		}
	}

}
