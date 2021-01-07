package com.wujie.springbootlayui.bus.controller;

import java.util.Date;
import java.util.List;

import com.wujie.springbootlayui.sys.common.Constast;
import com.wujie.springbootlayui.sys.common.ResultObj;
import com.wujie.springbootlayui.sys.entity.User;
import com.wujie.springbootlayui.sys.service.IUserService;
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
import com.wujie.springbootlayui.bus.entity.Staff;
import com.wujie.springbootlayui.bus.entity.StaffCard;
import com.wujie.springbootlayui.bus.service.IStaffCardService;
import com.wujie.springbootlayui.bus.service.IStaffService;
import com.wujie.springbootlayui.bus.vo.StaffCardVo;
import com.wujie.springbootlayui.sys.common.ActiverUser;
import com.wujie.springbootlayui.sys.common.DataGridView;

import javax.validation.Valid;

@RestController
@RequestMapping("/staffCard")
public class StaffCardController {
	
	@Autowired
	private IStaffCardService cardService;
	
	@Autowired
	private IStaffService staffService;
	@Autowired
	private IUserService userService;
	
	 /**
     * 查询所有的发卡记录
     * @param staffVo
     * @return
     */
    @RequestMapping("loadAllStaffCard")
    public DataGridView loadAllStaffCard(StaffCardVo staffCardVo){
    	//查询当前用户信息
    	Subject subject = SecurityUtils.getSubject();
    	ActiverUser activerUser = (ActiverUser) subject.getPrincipal();
    	Integer belongId = activerUser.getUser().getId();
        //1.声明一个分页page对象
        IPage<StaffCard> page = new Page<StaffCard>(staffCardVo.getPage(),staffCardVo.getLimit());
        //2.声明一个queryWrapper
        QueryWrapper<StaffCard> queryWrapper = new QueryWrapper<StaffCard>();
		queryWrapper.orderByDesc("create_time");
        //belongId为1时是超级管理员，可以查所有
        if(belongId != 1) {
        	queryWrapper.eq("belong_id", belongId);	
        }
        
        queryWrapper.eq(StringUtils.isNotBlank(staffCardVo.getCardNum()),"card_num",staffCardVo.getCardNum());
        
        cardService.page(page,queryWrapper);
        List<StaffCard> list = page.getRecords();
        for(StaffCard card:list) {
        	Integer cardBinder = card.getCardBinder();
        	if(cardBinder != null) {
        		Staff staff = staffService.getStaffById(cardBinder);
        		if(staff!=null){
					card.setBinderName(staff.getStaffName());
				}

        	}

        	Integer cardAuthor = card.getCardAuthor();
        	if(cardAuthor != null) {
        		User user = userService.getById(cardAuthor);
        		card.setAuthorName(user.getName());
        	}
        }
        return new DataGridView(page.getTotal(),list);
    }

	@RequestMapping("addStaffCard")
	public ResultObj addStaffCard(@Valid StaffCardVo staffCardVo, BindingResult bResult){
		try {
			if(bResult.hasErrors()) {
				String errrorMsg = bResult.getFieldError().getDefaultMessage();
				return new ResultObj(400,errrorMsg);
			}
			Subject subject = SecurityUtils.getSubject();
			ActiverUser activerUser = (ActiverUser) subject.getPrincipal();
			Integer belongId = activerUser.getUser().getId();
			staffCardVo.setBelongId(belongId);
			staffCardVo.setCardAuthor(belongId);
			staffCardVo.setCreateTime(new Date());
			staffCardVo.setUpdateTime(new Date());
			cardService.save(staffCardVo);
			return new ResultObj(Constast.OK,"messages.ADD_SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
			return  new ResultObj(Constast.ERROR,"messages.ADD_ERROR");
		}
	}

	@RequestMapping("deleteStaffCard")
	public ResultObj deleteStaffCard(Integer id){
		try {
			cardService.removeById(id);
			return new ResultObj(Constast.OK,"messages.DELETE_SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultObj(Constast.ERROR,"messages.DELETE_ERROR");
		}
	}

	@RequestMapping("updateStaffCard")
	public ResultObj updateDept(@Valid StaffCardVo vo, BindingResult bResult) {
		try {
			if(bResult.hasErrors()) {
				String errrorMsg = bResult.getFieldError().getDefaultMessage();
				return new ResultObj(400,errrorMsg);
			}
			cardService.updateById(vo);
			return new ResultObj(Constast.OK,"messages.UPDATE_SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultObj(Constast.ERROR,"messages.UPDATE_ERROR");
		}
	}



}
