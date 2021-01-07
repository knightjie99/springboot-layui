package com.wujie.springbootlayui.sys.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * @author wuj
 */
@Configuration
@EnableWebSocket
public class WsSocketConfig implements WebSocketConfigurer {

    @Autowired
    private WsHandler wsHandler;
    
    @Autowired
    private WsInterceptor wsInterceptor;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(wsHandler, "websocket/{deviceSn}")
                .addInterceptors(wsInterceptor)
                .setAllowedOrigins("*");
    }
}