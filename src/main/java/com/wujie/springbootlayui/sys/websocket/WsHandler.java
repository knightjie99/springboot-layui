package com.wujie.springbootlayui.sys.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wujie.springbootlayui.bus.entity.Gate;
import com.wujie.springbootlayui.bus.mapper.GateMapper;
import com.wujie.springbootlayui.sys.common.SpringUtil;
import com.wujie.springbootlayui.sys.common.StringUtil;
import com.wujie.springbootlayui.sys.entity.Device;
import com.wujie.springbootlayui.sys.mapper.DeviceMapper;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class WsHandler extends TextWebSocketHandler {

	private static Logger logger = LoggerFactory.getLogger(WsHandler.class);

	/**
	 * socket 建立成功事件
	 *
	 * @param session
	 * @throws Exception
	 */
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		Object deviceSn = session.getAttributes().get("wsToken");
		if (deviceSn != null) {
			// 用户连接成功，放入在线用户缓存
			WsSessionManager.deviceOnline(deviceSn.toString());
			WsSessionManager.add(deviceSn.toString(), session);
		} else {
			throw new RuntimeException("用户登录已经失效!");
		}
	}

	/**
	 * 接收消息事件
	 *
	 * @param session
	 * @param message
	 * @throws Exception
	 */
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		String token = (String) session.getAttributes().get("wsToken");

		// 获得客户端传来的消息
		String msg = message.getPayload();
		logger.info("用户消息:" + token + ",报文:" + msg);

		if (msg.contains("deviceSn")) {
			JSONObject jsonObject = JSON.parseObject(msg);
			String deviceSn = (String) jsonObject.get("deviceSn");
			// type 1-更新数据 2-心跳包
			String type = (String) jsonObject.get("type");
			if (!StringUtil.isEmpty(deviceSn)&&!StringUtil.isEmpty(type)) {
				switch (Integer.valueOf(type)) {
				case 1:
					String cpuId = (String) jsonObject.get("cpuId");
					this.updateCpu(cpuId, deviceSn);
					String currentVer = (String) jsonObject.get("currentVer");
					this.updateCurrentVer(currentVer, deviceSn);
					String verType = (String) jsonObject.get("verType");
					this.updateVerType(verType, deviceSn);
					break;

				case 2:
					WebSocketSession wbSession = WsSessionManager.get(deviceSn);
					if(wbSession == null) {
						WsSessionManager.add(deviceSn, session);
						WsSessionManager.deviceOnline(deviceSn);
					}else {
					    // 更新链路状态
					    WsSessionManager.update(deviceSn, session);
					}
					//回复心跳
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("code", 200);
					map.put("type", 4);
					map.put("msg", "OK");
					this.sendInfo(deviceSn, map.toString());
					break;
				default:
					break;
				}
			}

		}
	}

	/**
	 * 发送信息给指定用户
	 * 
	 * @param clientId
	 * @param message
	 * @return
	 */
	public boolean sendInfo(String wsToken, String message) {
		WebSocketSession session = WsSessionManager.get(wsToken);
		if (session == null) {
			return false;
		}
		try {
			session.sendMessage(new TextMessage(message));
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * socket 断开连接时
	 *
	 * @param session
	 * @param status
	 * @throws Exception
	 */
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {

		Object deviceSn = session.getAttributes().get("wsToken");
		if (deviceSn != null) {
			// 用户退出，移除缓存
			WsSessionManager.removeAndClose(deviceSn.toString());
			WsSessionManager.deviceOffline(deviceSn.toString());
		}
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {

		Object deviceSn = session.getAttributes().get("wsToken");
		if (deviceSn != null) {
			// 用户退出，移除缓存
			WsSessionManager.removeAndClose(deviceSn.toString());
			WsSessionManager.deviceOffline(deviceSn.toString());
		}
		logger.debug("传输出现异常，关闭websocket连接... ");
	}

	public void updateCpu(String cpuId, String deviceSn) {
		if (!StringUtil.isEmpty(cpuId)) {
			DeviceMapper mapper = SpringUtil.getBean(DeviceMapper.class);
			Device device = mapper.queryDeviceByDeviceSn(deviceSn);
			if (device != null) {
				device.setCpuId(cpuId);
				device.setUpdateTime(new Date());
				mapper.updateById(device);
			}
		}
	}

	public void updateCurrentVer(String currentVer, String deviceSn) {
		if (!StringUtil.isEmpty(currentVer)) {
			GateMapper mapper = SpringUtil.getBean(GateMapper.class);
			Integer gateId = mapper.getIdByDeviceSn(deviceSn);
			Gate gate = mapper.getGateById(gateId);
			if (gate != null) {
				gate.setCurrentVer(currentVer);
				gate.setUpdateTime(new Date());
				mapper.updateById(gate);
			}
		}
	}
	
	public void updateVerType(String verType , String deviceSn) {
		if(!StringUtil.isEmpty(verType)) {
			GateMapper mapper = SpringUtil.getBean(GateMapper.class);
			Integer gateId = mapper.getIdByDeviceSn(deviceSn);
			Gate gate = mapper.getGateById(gateId);
			if (gate != null) {
				Integer ver = Integer.valueOf(verType);
				gate.setVerType(ver);
				gate.setUpdateTime(new Date());
				mapper.updateById(gate);
			}
		}
	}

}