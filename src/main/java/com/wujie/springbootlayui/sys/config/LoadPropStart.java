package com.wujie.springbootlayui.sys.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.wujie.springbootlayui.sys.common.AppFileUtils;

@Component
public class LoadPropStart implements ApplicationRunner{
	
private final static Logger logger = LoggerFactory.getLogger(LoadPropStart.class);
	
    @Value("${spring.profiles.active}")
	private String env;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		  logger.info("开始读取配置文件信息......");
		  StringBuilder sb = new StringBuilder();
	      sb.append("file-").append(env).append(".properties");
	      String str = sb.toString();
	      AppFileUtils.load(str);
	      logger.info("读取配置文件信息完成......");
	}
}
