package com.wujie.springbootlayui.bus.controller;

import java.util.*;

import com.wujie.springbootlayui.sys.common.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wujie.springbootlayui.bus.entity.Gate;
import com.wujie.springbootlayui.bus.entity.GateAuthor;
import com.wujie.springbootlayui.bus.service.IGateAuthorService;
import com.wujie.springbootlayui.bus.service.IGateService;
import com.wujie.springbootlayui.bus.service.IStaffService;
import com.wujie.springbootlayui.bus.vo.GateAuthorVo;
import com.wujie.springbootlayui.sys.websocket.WsHandler;

@RestController
@RequestMapping("/gateAuthor")
public class GateAuthorController {

    @Autowired
    private IGateAuthorService authorService;

    @Autowired
    private IStaffService staffService;

    @Autowired
    private IGateService gateService;

    @Autowired
    private WsHandler wsHandler;

    /**
     * 查询所有的闸机
     *
     * @param gateVo
     * @return
     */
    @RequestMapping("loadAllGateAuthor")
    public DataGridView loadAllGateAuthor(GateAuthorVo gateAuthorVo) {
        //查询当前用户信息
        Subject subject = SecurityUtils.getSubject();
        ActiverUser activerUser = (ActiverUser) subject.getPrincipal();
        Integer belongId = activerUser.getUser().getId();
        //1.声明一个分页page对象
        IPage<GateAuthor> page = new Page<GateAuthor>(gateAuthorVo.getPage(), gateAuthorVo.getLimit());
        //2.声明一个queryWrapper
        QueryWrapper<GateAuthor> queryWrapper = new QueryWrapper<GateAuthor>();
        queryWrapper.orderByDesc("create_time");
        //belongId为1时是超级管理员，可以查所有
        if (belongId != 1) {
            queryWrapper.eq("belong_id", belongId);
        }
        queryWrapper.eq(gateAuthorVo.getGateId() != null, "gate_id", gateAuthorVo.getGateId());
        authorService.page(page, queryWrapper);
        List<GateAuthor> list = page.getRecords();

        for (GateAuthor author : list) {
            Integer gateId = author.getGateId();
            if (gateId != null) {
                Gate gate = gateService.getGateById(gateId);
                if (gate != null) {
                    author.setGateName(gate.getGateName());
                } else {
                    author.setGateName("闸机已删除");
                }
            }
            String staffList = author.getStaffList();
            if (!StringUtil.isEmpty(staffList)) {
                List<String> ids = Arrays.asList(staffList.split(","));
                String names = staffService.getStaffNameListByIds(ids);
                if(!StringUtil.isEmpty(names)){
                    if (ids.size() > 6) {
                        ids = ids.subList(0, 5);
                        author.setNameList(names + "等");
                    } else {
                        author.setNameList(names);
                    }
                }
            }

        }
        return new DataGridView(page.getTotal(), list);
    }


    /**
     * 新增闸机授权
     *
     * @param gateAuthorVo
     * @return
     */
    @RequestMapping("addGateAuthor")
    public ResultObj addGateAuthor(GateAuthorVo gateAuthorVo) {
        try {
            Subject subject = SecurityUtils.getSubject();
            ActiverUser activerUser = (ActiverUser) subject.getPrincipal();
            Integer belongId = activerUser.getUser().getId();

            String listStr = gateAuthorVo.getStaffList();

            if (!StringUtil.isEmpty(listStr) && listStr.endsWith(",")) {

                gateAuthorVo.setStaffList(listStr.substring(0, listStr.length() - 1));
            }
            gateAuthorVo.setBelongId(belongId);

            gateAuthorVo.setCreateTime(new Date());
            gateAuthorVo.setUpdateTime(new Date());
            authorService.save(gateAuthorVo);
            
            Integer gateId = gateAuthorVo.getGateId();
            Gate gate = gateService.getGateById(gateId);
            String deviceSn = gate.getDeviceSn();
            if (!StringUtil.isEmpty(deviceSn)) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("code", 200);
                //type 1-人员 2-闸机  3-下发更新包 4-心跳回复
                map.put("type", 1);
                map.put("msg", "人员信息已更新!");
                wsHandler.sendInfo(deviceSn, map.toString());
            }
            return new ResultObj(Constast.OK,"messages.ADD_SUCCESS");
        } catch (Exception e) {
            e.printStackTrace();
            return  new ResultObj(Constast.ERROR,"messages.ADD_ERROR");
        }
    }

    /**
     * 更新闸机授权
     *
     * @param gateAuthorVo
     * @return
     */
    @RequestMapping("updateGateAuthor")
    public ResultObj updateGateAuthor(GateAuthorVo gateAuthorVo) {
        try {

            String listStr = gateAuthorVo.getStaffList();
            if (!StringUtil.isEmpty(listStr) && listStr.endsWith(",")) {
                gateAuthorVo.setStaffList(listStr.substring(0, listStr.length() - 1));
            }

            GateAuthor author = authorService.getGateAuthorById(gateAuthorVo.getId());
            Gate gate = gateService.getGateById(author.getGateId());
            String deviceSn = gate.getDeviceSn();
            if (!StringUtil.isEmpty(deviceSn)) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("code", 200);
                //type 1-人员 2-闸机  3-下发更新包 4-心跳回复
                map.put("type", 1);
                map.put("msg", "人员信息已更新!");
                wsHandler.sendInfo(deviceSn, map.toString());
            }

            gateAuthorVo.setUpdateTime(new Date());
            authorService.updateById(gateAuthorVo);
            return new ResultObj(Constast.OK,"messages.UPDATE_SUCCESS");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultObj(Constast.ERROR,"messages.UPDATE_ERROR");
        }
    }

    @RequestMapping("deleteGateAuthor")
    public ResultObj deleteGateAuthor(Integer id) {
        try {
        	
        	GateAuthor author = authorService.getGateAuthorById(id);
			Gate gate = gateService.getGateById(author.getGateId());
			String deviceSn = gate.getDeviceSn();
			if (!StringUtil.isEmpty(deviceSn)) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("code", 200);
				//type 1-人员 2-闸机  3-下发更新包 4-心跳回复
				map.put("type", 1);
				map.put("msg", "人员信息已更新!");
				wsHandler.sendInfo(deviceSn, map.toString());
			}
            authorService.removeById(id);
            return  new ResultObj(Constast.OK,"messages.DELETE_SUCCESS");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultObj(Constast.ERROR,"messages.DELETE_ERROR");
        }
    }
}
