package com.wujie.springbootlayui.bus.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wujie.springbootlayui.bus.entity.*;
import com.wujie.springbootlayui.bus.service.IGateService;
import com.wujie.springbootlayui.bus.service.IPassRecordService;
import com.wujie.springbootlayui.bus.vo.PassRecordVo;
import com.wujie.springbootlayui.sys.common.*;
import com.wujie.springbootlayui.sys.config.MybatisPlusConfig;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/passRecord")
public class PassRecordController {

    @Autowired
    private IPassRecordService recordService;

    @Autowired
    private IGateService gateService;

    /**
     * 查询所有的通行记录
     *
     * @param staffVo
     * @return
     */
    @RequestMapping("loadAllPassRecord")
    public DataGridView loadAllPassRecord(PassRecordVo recordVo) {
        //查询当前用户信息
        Subject subject = SecurityUtils.getSubject();
        ActiverUser activerUser = (ActiverUser) subject.getPrincipal();
        Integer belongId = activerUser.getUser().getId();
        //1.声明一个分页page对象
        IPage<PassRecord> page = new Page<PassRecord>(recordVo.getPage(), recordVo.getLimit());
        //2.声明一个queryWrapper
        QueryWrapper<PassRecord> queryWrapper = new QueryWrapper<PassRecord>();
        //所要查询的表名
        String tName = "bus_pass_record_" + belongId;
        MybatisPlusConfig.passRecordTName.set(tName);
        queryWrapper.orderByDesc("pass_time");
        //3.查询条件---姓名
        queryWrapper.like(StringUtils.isNotBlank(recordVo.getName()), "name", recordVo.getName());
        queryWrapper.eq(recordVo.getGateId() != null, "gate_id", recordVo.getGateId());
        queryWrapper.like(StringUtils.isNotBlank(recordVo.getPhone()), "phone", recordVo.getPhone());

        //根据时间进行模糊查询
        queryWrapper.ge(recordVo.getStartTime() != null, "pass_time", recordVo.getStartTime());
        queryWrapper.le(recordVo.getEndTime() != null, "pass_time", recordVo.getEndTime());
        queryWrapper.orderByDesc("pass_time");
        recordService.page(page, queryWrapper);
        List<PassRecord> list = page.getRecords();
        for (PassRecord passRecord : list) {
            Integer gateId = passRecord.getGateId();
            if (gateId != null) {
                // 先从缓存中去取，如果缓存中没有就去数据库中取
                Gate one = gateService.getGateById(gateId);
                if (one != null) {
                    // 设置闸机的名称
                    passRecord.setGateName(one.getGateName());
                } else {
                    passRecord.setGateName("闸机已删除");
                }
            }
        }
        return new DataGridView(page.getTotal(), list);
    }

    /**
     * 添加一个通行记录
     *
     * @param recordVo
     * @return
     */
    @RequestMapping("addPassRecord")
    public ResultObj addPassRecord(PassRecordVo recordVo) {
        try {
            Subject subject = SecurityUtils.getSubject();
            ActiverUser activerUser = (ActiverUser) subject.getPrincipal();
            Integer belongId = activerUser.getUser().getId();
            recordVo.setBelongId(belongId);
            recordService.save(recordVo);
            return new ResultObj(Constast.OK, "messages.ADD_SUCCESS");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultObj(Constast.ERROR, "messages.ADD_ERROR");
        }
    }

    @RequestMapping("batchDeletePassRecord")
    public ResultObj batchDeletePassRecord(PassRecordVo passRecordVo) {
        try {
            Subject subject = SecurityUtils.getSubject();
            ActiverUser activerUser = (ActiverUser) subject.getPrincipal();
            Integer belongId = activerUser.getUser().getId();

            Collection<Serializable> idList = new ArrayList<Serializable>();
            for (Integer id : passRecordVo.getIds()) {
                idList.add(id);
            }
            QueryWrapper<PassRecord> queryWrapper = new QueryWrapper<PassRecord>();
            String tName = "bus_pass_record_" + belongId;
            MybatisPlusConfig.passRecordTName.set(tName);
            queryWrapper.in("id", idList);
            recordService.remove(queryWrapper);

            return new ResultObj(Constast.OK, "messages.DELETE_SUCCESS");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultObj(Constast.ERROR, "messages.DELETE_ERROR");
        }
    }

    @RequestMapping("selectHour")
    public Object selectHour(SelectHourParam param) {
        try {
            Subject subject = SecurityUtils.getSubject();
            ActiverUser activerUser = (ActiverUser) subject.getPrincipal();
            Integer belongId = activerUser.getUser().getId();
            String tName = "bus_pass_record_" + belongId;


            List<HourPass> list = recordService.selectByHour(tName, belongId, param.getTimeRange(), param.getGateId());
//            List<HourPass> result = new ArrayList<>();
            Integer[] result = new Integer[24];

            for (int i = 0; i <24 ; i++) {
                result[i] =0;
            }

            for (int i = 0; i <list.size() ; i++) {
                HourPass hp = list.get(i);
                Integer index= hp.getHours();
                result[index]=hp.getCount();
            }


            return result;

        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }

    @RequestMapping("loadRank")
    public Object loadRank(TimeLimit param) {
        try {
            Subject subject = SecurityUtils.getSubject();
            ActiverUser activerUser = (ActiverUser) subject.getPrincipal();
            Integer belongId = activerUser.getUser().getId();
            String tName = "bus_pass_record_" + belongId;

            //防止空字符串被传到sql语句里面
            if(StringUtil.isEmpty(param.getStart())){
                param.setStart(null);
            }else{
                param.setStart(param.getStart()+" 00:00:00");
            }
            if(StringUtil.isEmpty(param.getEnd())){
                param.setEnd(null);
            }else{
                param.setEnd(param.getEnd()+" 23:59:59");
            }


            List<recordRank> list = recordService.recordsRank(tName,param.getStart(),param.getEnd());

            return list;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e.toString());
        }


        return null;
    }
}
