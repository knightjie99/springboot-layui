package com.wujie.springbootlayui.sys.i18n;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;


/**
 * accept-language拦截器
 * @author wuj
 * 国际化拦截器只能拦截url参数，返回对应的语言包
 * 因此此拦截器用于将accept-language参数写到url
 * 然后再转发一次，达到有?lang=xx参数的效果
 */

@Configuration
public class HeaderFilter {

	@SuppressWarnings({"rawtypes", "unchecked"})
    @Bean
    public FilterRegistrationBean modifyParametersFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new ModifyParametersFilter());
        registration.addUrlPatterns("/*");              // 拦截路径
        registration.setName("modifyParametersFilter"); // 拦截器名称
        registration.setOrder(1);                       // 顺序
        return registration;
    }

    /**
     * 自定义拦截器
     * 1.拦截accept-language，添加到url参数?lang=xxx，以供国际化拦截输出
     * 2.只有cn、tw、us，              才可以在url添加?lang=xxx
     */
    class ModifyParametersFilter extends OncePerRequestFilter {
        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
            // 请求头 语言区域
            String language = request.getHeader("accept-language");
            if (language == null) {
                // finish
                filterChain.doFilter(request, response);
                return;
            }
            String cn = Locale.CHINA+"";
            String us = Locale.US+"";
            if (!(language.equals(cn) || language.equals(us))) {
                filterChain.doFilter(request, response);
                return;
            }
            //path请求前的URL
            String path=request.getRequestURI();
            if (path.indexOf("lang=")<=0) {
                if(path.indexOf("?") < 0){
                	//path修改后的URL
                    path = path + "?lang=" + language;
                    System.out.println("修改后的URL:"+path);
                    request.getRequestDispatcher(path).forward(request,response);
                } else {
                	//path修改后的URL
                    path = path + "&lang=" +language;
                    request.getRequestDispatcher(path).forward(request,response);
                }
            }
        }
    }
}
