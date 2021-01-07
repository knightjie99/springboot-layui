package com.wujie.springbootlayui.sys.controller;

import java.io.File;
import java.util.Date;
import java.util.List;

import com.wujie.springbootlayui.sys.common.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wujie.springbootlayui.bus.entity.Gate;
import com.wujie.springbootlayui.bus.service.IGateService;
import com.wujie.springbootlayui.sys.entity.App;
import com.wujie.springbootlayui.sys.service.IAppService;
import com.wujie.springbootlayui.sys.vo.AppVo;

import javax.validation.Valid;

@RestController
@RequestMapping("app")
public class AppController {

	@Autowired
	private IAppService appService;
	
	@Autowired
	private IGateService gateService;

	/**
	 * 查询所有应用的数据
	 * 
	 * @param appVo
	 * @return
	 */
	@RequestMapping("loadAllApp")
	public DataGridView loadAllApp(AppVo appVo) {
		IPage<App> page = new Page<>(appVo.getPage(), appVo.getLimit());
		// 进行模糊查询
		QueryWrapper<App> queryWrapper = new QueryWrapper<>();
		queryWrapper.like(StringUtils.isNotBlank(appVo.getAppName()), "app_name", appVo.getAppName());
		// 进行查询
		appService.page(page, queryWrapper);
		// 返回DataGridView
		return new DataGridView(page.getTotal(), page.getRecords());
	}
	
	/**
     * 添加应用
     * @param deptVo
     * @return
     */
    @RequestMapping("addApp")
    public ResultObj addApp(@Valid AppVo appVo, BindingResult bResult){
        try {
			if(bResult.hasErrors()) {
				String errrorMsg = bResult.getFieldError().getDefaultMessage();
				return new ResultObj(400,errrorMsg);
			}

        	if(appVo.getMf() != null ) {
        		String newName = AppFileUtils.renameFile(appVo.getMf());
        		//文件名为20181120_V1.2.3_1.apk  版本号_版本名称.apk
        		String fileDoc = AppFileUtils.UPLOAD_PATH;
        		String appName = appVo.getAppName();
        		int index = appName.lastIndexOf("_")+1;
        		String sub  = appName.substring(index, index+1);
        		Integer verType = Integer.valueOf(sub);
        		appVo.setVerType(verType);
        		//文件存放路径
        		String filePath = fileDoc + newName;
        		File file = new File(filePath);
        		appVo.setAppUrl(newName);
        		appVo.setAppSize((double)file.length()/1000);
        		}
        	appVo.setStatus(1);
        	appVo.setCreateTime(new Date());
        	appVo.setUpdateTime(new Date());
            appService.save(appVo);
            return new ResultObj(Constast.OK,"messages.ADD_SUCCESS");
        } catch (Exception e) {
            e.printStackTrace();
            return  new ResultObj(Constast.ERROR,"messages.ADD_ERROR");
        }
    }
    
	/**
	 * 发布通知即更新闸机的最新版本字段
	 */
    @RequestMapping("publishApp")
    public ResultObj publishApp(Integer id){
    	App app = appService.getAppById(id);
    	app.setStatus(2);
    	app.setUpdateTime(new Date());
    	appService.updateById(app);
    	Integer verType = app.getVerType();
    	String appVer = app.getAppVer();
    	List<Gate> gates =	gateService.getAllGatesByVerType(verType);
    	for(Gate gate : gates) {
    		gate.setNewestVer(appVer);
    		gateService.updateById(gate);
    	}
    	
    	return new ResultObj(Constast.OK,"messages.NOTICE_SUCCESS");
    }
    
    /**
     * 取消发布
     * 获取当前的取消版本号的信息:
     * 1.如果当前的版本(闸机列表中的currentVer)大于或等于取消的版本号，最新版本(newsetVer)不回退，不操作，
     * 2.如果当前的版本(闸机列表中的currentVer)小于取消的版本号,回退上一个版本号：
     *     2.1 如果上一版本号为空,即只有一个版本,设置currentVer与newsetVer相同。
     *     2.2 如果上一版本号不为空，对比上一版本号与当前的版本号  ，如果当前版本号小于或等于上一版本号，设置最新版本为上一版本
     *         如果当前版本号大于上一版本号，设置currentVer与newsetVer相同。
     */
    @RequestMapping("publishCancelApp")
    public ResultObj publishCancelApp(Integer id){
    	App app = appService.getAppById(id);
    	Integer verType = app.getVerType();
    	List<Gate> gates =	gateService.getAllGatesByVerType(verType);
    	for(Gate gate:gates ) {
    		String currrentVer = gate.getCurrentVer();
    		if(!StringUtil.isEmpty(currrentVer)) {
    			int oldNum = Integer.parseInt(currrentVer);
    			String appVer = app.getAppVer();
    			int newNum =  Integer.parseInt(appVer);
    			if(oldNum < newNum) {
    				Integer preId = appService.getPreviousId(id);
    				if(preId != null) {
    					App appPre = appService.getAppById(id);
    					int preNum = Integer.parseInt(appPre.getAppVer());
    					if(oldNum <= preNum) {
    						 gate.setNewestVer(appPre.getAppVer());
    						 gateService.updateById(gate);
    					}else {
    						gate.setNewestVer(currrentVer);
    						gateService.updateById(gate);
    					}
    				}else {
    					gate.setNewestVer(currrentVer);
						gateService.updateById(gate);
    					
    				}
    				
    			}else {
    				gate.setNewestVer(currrentVer);
    				gateService.updateById(gate);
    			}
    		}
    	}
    	app.setStatus(1);
    	appService.updateById(app);
    	return new ResultObj(Constast.OK,"messages.NOTICE_SUCCESS");
    }
    
	/**
     * 更新应用信息
     * @param deptVo
     * @return
     */
    @RequestMapping("updateApp")
    public ResultObj updateApp(@Valid AppVo appVo, BindingResult bResult){
        try {
			if(bResult.hasErrors()) {
				String errrorMsg = bResult.getFieldError().getDefaultMessage();
				return new ResultObj(400,errrorMsg);
			}

			if(appVo.getMf() != null ) {
				Integer id =appVo.getId();
				App app = appService.getAppById(id);
				File oldFile = new File(AppFileUtils.UPLOAD_PATH+app.getAppUrl());
				if(oldFile.exists()){
					oldFile.delete();
				}

				String newName = AppFileUtils.renameFile(appVo.getMf());
				//文件名为20181120_V1.2.3_1.apk  版本号_版本名称.apk
				String fileDoc = AppFileUtils.UPLOAD_PATH;
				String appName = appVo.getAppName();
				int index = appName.lastIndexOf("_")+1;
				String sub  = appName.substring(index, index+1);
				Integer verType = Integer.valueOf(sub);
				appVo.setVerType(verType);
				//文件存放路径
				String filePath = fileDoc + newName;
				File file = new File(filePath);
				appVo.setAppUrl(newName);
				appVo.setAppSize((double)file.length()/1000);
			}


        	appVo.setUpdateTime(new Date());
        	appService.updateById(appVo);
            return new ResultObj(Constast.OK,"messages.UPDATE_SUCCESS");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultObj(Constast.ERROR,"messages.UPDATE_ERROR");
        }
    }
    
    /**
     * 删除一个应用
     * @param id 访客的ID
     * @return
     */
    @RequestMapping("deleteApp")
    public ResultObj deleteApp(Integer id){
        try {
        	App app = appService.getAppById(id);
        	appService.removeById(id);
        	File file = new File(AppFileUtils.UPLOAD_PATH+app.getAppUrl());
        	if(file.exists()){
        		file.delete();
			}

            return new ResultObj(Constast.OK,"messages.DELETE_SUCCESS");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultObj(Constast.ERROR,"messages.DELETE_ERROR");
        }
    }

}
