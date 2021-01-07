package com.wujie.springbootlayui.sys.i18n;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import org.springframework.web.servlet.support.RequestContext;
import com.wujie.springbootlayui.sys.common.ResultObj;
import javax.servlet.http.HttpServletRequest;

/**
 * 拦截返回数据，返回国际化信息
 */
@RestControllerAdvice(basePackages = {"com.wujie.springbootlayui"})
public class I18nRespBodyAdvice implements ResponseBodyAdvice<Object> {
	
	private Logger logger = LoggerFactory.getLogger(I18nRespBodyAdvice.class);

    @Override
    public Object beforeBodyWrite(Object obj, MethodParameter method, MediaType type, Class<? extends HttpMessageConverter<?>> converter, ServerHttpRequest request, ServerHttpResponse response) {
        try {
            if (obj instanceof ResultObj) {
            	ResultObj result = (ResultObj)obj;
                String msg = result.getMsg();
                if (msg != null) {
                    HttpServletRequest req = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
                    String i18nMsg = this.getMessage(req, msg);
                    if (!StringUtils.isEmpty(i18nMsg)) {
                        result.setMsg(i18nMsg);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("返回值国际化拦截异常", e);
        }
        return obj;
    }

    @Override
    public boolean supports(MethodParameter arg0, Class<? extends HttpMessageConverter<?>> arg1) {
        return true;
    }

    /**
     * 返回国际化的值
     * 
     * @param request
     * @param key
     * @return
     */
    public String getMessage(HttpServletRequest request, String key) {
        String value = "";
        try {
            RequestContext requestContext = new RequestContext(request);
            value = requestContext.getMessage(key);
        }
        catch (Exception e) {
        	logger.error(e.getMessage(), e);
            value = "";
        }
        return value;
    }
}