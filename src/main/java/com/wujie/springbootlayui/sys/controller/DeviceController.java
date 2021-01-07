package com.wujie.springbootlayui.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wujie.springbootlayui.bus.entity.Gate;
import com.wujie.springbootlayui.bus.service.IGateService;
import com.wujie.springbootlayui.sys.common.*;
import com.wujie.springbootlayui.sys.entity.Device;
import com.wujie.springbootlayui.sys.entity.DeviceError;
import com.wujie.springbootlayui.sys.entity.User;
import com.wujie.springbootlayui.sys.service.IDeviceErrorService;
import com.wujie.springbootlayui.sys.service.IDeviceService;
import com.wujie.springbootlayui.sys.service.IUserService;
import com.wujie.springbootlayui.sys.vo.DeviceVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("device")
public class DeviceController {

	@Autowired
	private IDeviceService deviceService;

	@Autowired
	private IUserService userService;

	@Autowired
	private IGateService gateService;
	@Autowired
	private IDeviceErrorService errorService;

	/**
	 * 查询所有设备的数据
	 * 
	 * @param deviceVo
	 * @return
	 */
	@RequestMapping("loadAllDevice")
	public DataGridView loadAllDevice(DeviceVo deviceVo) {
		IPage<Device> page = new Page<>(deviceVo.getPage(), deviceVo.getLimit());
		// 进行模糊查询
		QueryWrapper<Device> queryWrapper = new QueryWrapper<Device>();
		queryWrapper.like(StringUtils.isNotBlank(deviceVo.getDeviceSn()), "device_sn", deviceVo.getDeviceSn());

		queryWrapper.eq(deviceVo.getBelongId() != null, "belong_id", deviceVo.getBelongId());

		// 进行查询
		deviceService.page(page, queryWrapper);
		List<Device> list = page.getRecords();
		for (Device device : list) {
			Integer belongId = device.getBelongId();
			if (belongId != null) {
				User user = userService.getById(belongId);
				if(user!=null){
					device.setBelongName(user.getName());
				}else{
					device.setBelongName("用户已删除");
				}
			}
		}
		// 返回DataGridView
		return new DataGridView(page.getTotal(), list);
	}

	/**
	 * 新增设备
	 * 
	 * @param deviceVo
	 * @return
	 */
	@RequestMapping("addDevice")
	public ResultObj addDevice(DeviceVo deviceVo) {
		try {


			String dataBaseSN = deviceService.selectSnGroup();
			List<String> dblist = new ArrayList<String>();
			if (!StringUtil.isEmpty(dataBaseSN)) {
				dblist = Arrays.asList(dataBaseSN.split(","));
			}

			// 1-批量导入
			if (deviceVo.getInputWay() == 1) {
				
				if (deviceVo.getMf() != null) {
					String newName = AppFileUtils.renameFile(deviceVo.getMf());
					String fileDoc = AppFileUtils.UPLOAD_PATH;
					// 解析xml文件
					String filePath = fileDoc + newName;
					String[][] excelData = ExcelUtil.getExcelStr(filePath);
					if (excelData == null) {
						return new ResultObj(500, "excel_error");
					}

					//List<String> list = new ArrayList<String>();
					int count =0;
					for (int i = 1; i < excelData.length; i++) {
						String[] strs = excelData[i];
						if(!StringUtil.isEmpty(strs[1])){
							QueryWrapper<Device> queryWrapper = new QueryWrapper<>();
							queryWrapper.eq("device_sn",strs[1]);
							if(deviceService.list(queryWrapper).size()==0){
								Device device = new Device();
								device.setDeviceSn(strs[1]);
								device.setCreateTime(new Date());
								device.setUpdateTime(new Date());
								device.setStatus(2);
								deviceService.save(device);
								count++;
							}else{
								DeviceError error = new DeviceError();
								error.setDeviceSn(strs[1]);
								error.setRemark("SN号已被使用");
								error.setUpdateTime(new Date());
								error.setCreateTime(new Date());
								errorService.save(error);
							}

						}
					}


					if(count>0){
						return new ResultObj(200,"successfully_import");
					}else{
						return new ResultObj(200,"no_valid_sn");
					}
				}
			} else {
				if (dblist.contains(deviceVo.getDeviceSn())) {
					return  new ResultObj(Constast.ERROR,"messages.ADD_ERROR");
				} else {
					deviceVo.setCreateTime(new Date());
					deviceVo.setUpdateTime(new Date());
					// 1-在线 2-离线
					deviceVo.setStatus(2);
					deviceService.save(deviceVo);
				}
			}
			return new ResultObj(Constast.OK,"messages.ADD_SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
			return  new ResultObj(Constast.ERROR,"messages.ADD_ERROR");
		}
	}

	/**
	 * 修改一个设备
	 * 
	 * @param deviceVo
	 * @return
	 */
	@RequestMapping("updateDevice")
	public ResultObj updateDevice(DeviceVo deviceVo) {
		try {
			deviceVo.setUpdateTime(new Date());
			deviceService.updateById(deviceVo);
			return new ResultObj(Constast.OK,"messages.UPDATE_SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultObj(Constast.ERROR,"messages.UPDATE_ERROR");
		}
	}

	/**
	 * 删除一个设备
	 * 
	 * @param id
	 *            设备的ID
	 * @return
	 */
	@RequestMapping("deleteDevice")
	public ResultObj deleteDevice(Integer id) {
		try {
			Device device = deviceService.getById(id);
			if(device==null){
				return new ResultObj(Constast.ERROR,"messages.DELETE_ERROR");
			}

			QueryWrapper<Gate> queryWrapper = new QueryWrapper<>();
			queryWrapper.eq("device_sn",device.getDeviceSn());
			queryWrapper.eq("available",1);
			if(gateService.list(queryWrapper).size()>0){
				return new ResultObj(500,"delete_gate_before_device");
			}

			deviceService.removeById(id);
			return new ResultObj(Constast.OK,"messages.DELETE_SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultObj(Constast.ERROR,"messages.DELETE_ERROR");
		}
	}

}
