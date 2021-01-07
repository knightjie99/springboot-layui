package com.wujie.springbootlayui.sys.websocket;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.server.HandshakeInterceptor;
import com.wujie.springbootlayui.sys.common.SpringUtil;
import com.wujie.springbootlayui.sys.entity.Device;
import com.wujie.springbootlayui.sys.mapper.DeviceMapper;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 * @author wuj
 */
@Component
public class WsInterceptor implements HandshakeInterceptor {
	

    /**
     * 握手前
     *
     * @param request
     * @param response
     * @param wsHandler
     * @param attributes
     * @return
     * @throws Exception
     */
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        System.out.println("握手开始");
        
        // 获取HttpSession
        HttpServletRequest servletRequest = ((ServletServerHttpRequest) request).getServletRequest();
        String uri = servletRequest.getRequestURI();
        List<String> str =  Arrays.asList(uri.split("/"));
        if(!str.get(1).contains("websocket")) {
        	return false;
        }
        String deviceSn = str.get(2);
        DeviceMapper deviceMapper = SpringUtil.getBean(DeviceMapper.class);
        Device device = deviceMapper.queryDeviceByDeviceSn(deviceSn);
        if(device == null) {
            return  false;
        }
        WebSocketSession session = WsSessionManager.get(deviceSn);
        if(session != null) {
        	WsSessionManager.removeAndClose(deviceSn);
        }
        
//        HttpSession session = servletRequest.getSession();
//        String sessionId = session.getId();
        attributes.put("wsToken",deviceSn);
        return true;
    }

    /**
     * 握手后
     *
     * @param request
     * @param response
     * @param wsHandler
     * @param exception
     */
    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
        System.out.println("握手完成");
    }

}
