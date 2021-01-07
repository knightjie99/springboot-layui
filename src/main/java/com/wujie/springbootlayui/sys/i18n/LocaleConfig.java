package com.wujie.springbootlayui.sys.i18n;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.wujie.springbootlayui.sys.common.StringUtil;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 配置国际化语言
 */
@Configuration
public class LocaleConfig implements WebMvcConfigurer {

	/**
	 * 默认解析器 其中locale表示默认语言 Locale.US 英文 Locale.CHINA 中文
	 * 设置后台返回
	 */
	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		localeResolver.setDefaultLocale(Locale.CHINA);
		return localeResolver;
	}

	/**
	 * 默认拦截器 其中lang表示切换语言的参数名
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		LocaleChangeInterceptor localeInterceptor = new LocaleChangeInterceptor();
		localeInterceptor.setParamName("lang");
		registry.addInterceptor(localeInterceptor);
	}
	
	/**
	 * 设置前端请求
	 * @return
	 */
	@Bean
	public LocaleResolver reqLocaleResolver() {
		return new LocaleResolver() {

			@Override
			public Locale resolveLocale(HttpServletRequest request) {
				String language = request.getParameter("lang");
		        Locale locale = Locale.getDefault();
		        System.out.println(language);
		        if (!StringUtil.isEmpty(language)){
		            String[] split = language.split("_");
		            locale = new Locale(split[0], split[1]);
		        }

		        return locale;
			}

			@Override
			public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
				
			}
			
		};
	}
	
}