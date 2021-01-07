package com.wujie.springbootlayui.bus.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wujie.springbootlayui.bus.entity.StaffError;
import com.wujie.springbootlayui.bus.service.IStaffErrorService;
import com.wujie.springbootlayui.bus.vo.StaffErrorVo;
import com.wujie.springbootlayui.sys.common.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;


@RestController
@RequestMapping("/staffError")
public class StaffErrorController {

    @Autowired
    private IStaffErrorService errorService;

    /**
     * 查询错误导入记录
     * @param goodsVo
     * @return
     */
    @RequestMapping("loadAllStaffError")
    public DataGridView loadAllGoods(StaffErrorVo errorVo) {
        Subject subject = SecurityUtils.getSubject();
        ActiverUser activerUser = (ActiverUser) subject.getPrincipal();
        Integer belongId = activerUser.getUser().getId();
        if(belongId==null){
            return null;
        }

        IPage<StaffError> page = new Page<>(errorVo.getPage(), errorVo.getLimit());
        QueryWrapper<StaffError> queryWrapper = new QueryWrapper<StaffError>();
        queryWrapper.orderByDesc("id");
        queryWrapper.eq("belong_id",belongId);
        queryWrapper.orderByDesc("create_time");

        queryWrapper.ge(errorVo.getStartTime()!=null,"create_time",errorVo.getStartTime());
        queryWrapper.le(errorVo.getEndTime()!=null,"create_time",errorVo.getEndTime());
        errorService.page(page, queryWrapper);
        return new DataGridView(page.getTotal(), page.getRecords());
    }

    @RequestMapping("batchdeleteStaffError")
    public ResultObj batchDeleteStaff(StaffErrorVo vo) {
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

