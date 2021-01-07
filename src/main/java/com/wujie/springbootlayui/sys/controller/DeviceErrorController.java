package com.wujie.springbootlayui.sys.controller;

import com.wujie.springbootlayui.sys.common.Constast;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wujie.springbootlayui.sys.common.DataGridView;
import com.wujie.springbootlayui.sys.common.ResultObj;
import com.wujie.springbootlayui.sys.entity.DeviceError;
import com.wujie.springbootlayui.sys.service.IDeviceErrorService;
import com.wujie.springbootlayui.sys.vo.DeviceErrorVo;


@RestController
@RequestMapping("deviceError")
public class DeviceErrorController {

	@Autowired
	private  IDeviceErrorService errorService;
	
	/**
	 * 查询所有异常记录的数据
	 * 
	 * @param deviceVo
	 * @return
	 */
	@RequestMapping("loadAllDeviceError")
	public DataGridView loadAllDeviceError(DeviceErrorVo deviceErrorVo) {
		IPage<DeviceError> page = new Page<>(deviceErrorVo.getPage(), deviceErrorVo.getLimit());
		// 进行模糊查询
		QueryWrapper<DeviceError> queryWrapper = new QueryWrapper<DeviceError>();
		queryWrapper.like(StringUtils.isNotBlank(deviceErrorVo.getDeviceSn()), "device_sn", deviceErrorVo.getDeviceSn());
		
		// 进行查询
		errorService.page(page, queryWrapper);
		// 返回DataGridView
        return new DataGridView(page.getTotal(), page.getRecords());
	}
	
	/**
     * 修改一个异常记录
     * @param deviceVo
     * @return
     */
    @RequestMapping("updateDeviceError")
    public ResultObj updateDeviceError(DeviceErrorVo deviceErrorVo){
        try {
        	errorService.updateById(deviceErrorVo);
            return new ResultObj(Constast.OK,"messages.UPDATE_SUCCESS");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultObj(Constast.ERROR,"messages.UPDATE_ERROR");
        }
    }
    
    /**
     * 删除一个设备
     * @param id 设备的ID
     * @return
     */
    @RequestMapping("deleteDeviceError")
    public ResultObj deleteDeviceError(Integer id){
        try {
        	errorService.removeById(id);
            return new ResultObj(Constast.OK,"messages.DELETE_SUCCESS");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultObj(Constast.ERROR,"messages.DELETE_ERROR");
        }
    }
}
