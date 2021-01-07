package com.wujie.springbootlayui.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wujie.springbootlayui.sys.common.Constast;
import com.wujie.springbootlayui.sys.common.DataGridView;
import com.wujie.springbootlayui.sys.common.ResultObj;
import com.wujie.springbootlayui.sys.entity.Loginfo;
import com.wujie.springbootlayui.sys.service.ILoginfoService;
import com.wujie.springbootlayui.sys.vo.LoginfoVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;


@RestController
@RequestMapping("loginfo")
public class LoginfoController {

    @Autowired
    private ILoginfoService loginfoService;

    /**
     * 查询所有登陆日志的信息
     * @param loginfoVo
     * @return
     */
    @RequestMapping("loadAllLoginfo")
    public DataGridView loadAllLoginfo(LoginfoVo loginfoVo){
        IPage<Loginfo> page = new Page<Loginfo>(loginfoVo.getPage(),loginfoVo.getLimit());
        QueryWrapper<Loginfo> queryWrapper = new QueryWrapper<Loginfo>();
        //进行模糊查询
        queryWrapper.like(StringUtils.isNotBlank(loginfoVo.getLoginname()),"loginname",loginfoVo.getLoginname());
        queryWrapper.like(StringUtils.isNotBlank(loginfoVo.getLoginip()),"loginip",loginfoVo.getLoginip());
        //数据库中登陆时间要大于用户输入的开始时间且小于用户登陆的结束时间
        queryWrapper.ge(loginfoVo.getStartTime()!=null,"logintime",loginfoVo.getStartTime());
        queryWrapper.le(loginfoVo.getEndTime()!=null,"logintime",loginfoVo.getEndTime());
        //根据登陆时间进行降序排序
        queryWrapper.orderByDesc("logintime");
        loginfoService.page(page,queryWrapper);
        return new DataGridView(page.getTotal(),page.getRecords());
    }

    /**
     * 删除单条日志
     * @param id
     * @return
     */
    @RequestMapping("deleteLoginfo")
    public ResultObj deleteLoginfo(Integer id){
        try {
            loginfoService.removeById(id);
            return new ResultObj(Constast.OK,"messages.DELETE_SUCCESS");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultObj(Constast.ERROR,"messages.DELETE_ERROR");
        }
    }

    /**
     * 批量删除
     * @param loginfoVo
     * @return
     */
    @RequestMapping("batchDeleteLoginfo")
    public ResultObj batchDeleteLoginfo(LoginfoVo loginfoVo){
        try {
            Collection<Serializable> idList = new ArrayList<Serializable>();
            for (Integer id : loginfoVo.getIds()) {
                idList.add(id);
            }
            this.loginfoService.removeByIds(idList);
            return new ResultObj(Constast.OK,"messages.DELETE_SUCCESS");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultObj(Constast.ERROR,"messages.DELETE_ERROR");
        }
    }

}

