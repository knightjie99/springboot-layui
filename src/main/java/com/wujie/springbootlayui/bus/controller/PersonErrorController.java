package com.wujie.springbootlayui.bus.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.wujie.springbootlayui.sys.common.Constast;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wujie.springbootlayui.bus.entity.PersonError;
import com.wujie.springbootlayui.bus.service.IPersonErrorService;
import com.wujie.springbootlayui.bus.vo.PersonErrorVo;
import com.wujie.springbootlayui.sys.common.ActiverUser;
import com.wujie.springbootlayui.sys.common.DataGridView;
import com.wujie.springbootlayui.sys.common.ResultObj;

@RestController
@RequestMapping("/personError")
public class PersonErrorController {
	
	@Autowired
	private IPersonErrorService errorService;
	
	 /**
     * 查询所有的通行记录
     * @param staffVo
     * @return
     */
    @RequestMapping("loadAllPersonError")
    public DataGridView loadAllPersonError(PersonErrorVo errorVo){
    	//查询当前用户信息
    	Subject subject = SecurityUtils.getSubject();
    	ActiverUser activerUser = (ActiverUser) subject.getPrincipal();
    	Integer belongId = activerUser.getUser().getId();
    	 //1.声明一个分页page对象
        IPage<PersonError> page = new Page<PersonError>(errorVo.getPage(),errorVo.getLimit());
        //2.声明一个queryWrapper
        QueryWrapper<PersonError> queryWrapper = new QueryWrapper<PersonError>();
        queryWrapper.orderByDesc("create_time");
        //belongId为1时是超级管理员，可以查所有
        if(belongId != 1) {
        	queryWrapper.eq("belong_id", belongId);	
        }
        //根据时间进行模糊查询
        queryWrapper.ge(errorVo.getStartTime()!=null,"create_time",errorVo.getStartTime());
        queryWrapper.le(errorVo.getEndTime()!=null,"create_time",errorVo.getEndTime());
        queryWrapper.orderByDesc("id");
        errorService.page(page,queryWrapper);
        List<PersonError> list =  page.getRecords();
        return new DataGridView(page.getTotal(),list);
    }
    
    /**
     * 删除一个异常记录
     * @param id 记录的ID
     * @return
     */
    @RequestMapping("deletePersonError")
    public ResultObj deletePersonError(Integer id){
        try {
        	errorService.removeById(id);
            return  new ResultObj(Constast.OK,"messages.DELETE_SUCCESS");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultObj(Constast.ERROR,"messages.DELETE_ERROR");
        }
    }

    @RequestMapping("batchdeletePersonError")
    public ResultObj batchDeleteStaff(PersonErrorVo vo) {
        try {
            Collection<Serializable> idList = new ArrayList<Serializable>();
            for (Integer id : vo.getIds()) {
                idList.add(id);
            }
            errorService.removeByIds(idList);
            return  new ResultObj(Constast.OK,"messages.DELETE_SUCCESS");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultObj(Constast.ERROR,"messages.DELETE_ERROR");
        }
    }

}
