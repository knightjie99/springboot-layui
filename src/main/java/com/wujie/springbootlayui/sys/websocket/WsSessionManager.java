package com.wujie.springbootlayui.sys.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.WebSocketSession;

import com.wujie.springbootlayui.bus.entity.Gate;
import com.wujie.springbootlayui.bus.mapper.GateMapper;
import com.wujie.springbootlayui.sys.common.DateUtil;
import com.wujie.springbootlayui.sys.common.SpringUtil;
import com.wujie.springbootlayui.sys.entity.Device;
import com.wujie.springbootlayui.sys.mapper.DeviceMapper;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

public class WsSessionManager {

	private static Logger logger = LoggerFactory.getLogger(WsSessionManager.class);
	/**
	 * 保存连接 session 的地方
	 */
	private static ConcurrentHashMap<String, ClientInfo> SESSION_POOL = new ConcurrentHashMap<String, ClientInfo>();

	/** 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。 */
	private static int onlineCount = 0;

	/**
	 * 添加 session
	 *
	 * @param key
	 */
	public static void add(String key, WebSocketSession session) {
		// 添加 session
		ClientInfo info = new ClientInfo();
		info.setSession(session);
		info.setAddTime(DateUtil.parseDateToString(new Date()));
		info.setDeviceSn(key);
		SESSION_POOL.put(key, info);
		addOnlineCount();
		logger.info("用户连接:" + key + ",当前在线人数为:" + getOnlineCount());
	}

	/**
	 * 删除 session,会返回删除的 session
	 *
	 * @param key
	 * @return
	 */
	// public static WebSocketSession remove(String key) {
	// // 删除 session
	// return SESSION_POOL.remove(key);
	// }

	public static ClientInfo remove(String key) {
		// 删除 session
		return SESSION_POOL.remove(key);
	}

	/**
	 * 删除并同步关闭连接
	 *
	 * @param key
	 */
	public static void removeAndClose(String key) {
		SESSION_POOL.remove(key);
		WebSocketSession session = get(key);
		if (session != null) {
			try {
				// 关闭连接
				session.close();
			} catch (IOException e) {
				// todo: 关闭出现异常处理
				e.printStackTrace();
			}
			delOnlineCount();
			logger.info("用户退出:" + key + ",当前在线人数为:" + getOnlineCount());
		}
	}

	/**
	 * 更新clientInfo
	 */
	public static void update(String key, WebSocketSession session) {
		ClientInfo info = new ClientInfo();
		info.setAddTime(DateUtil.parseDateToString(new Date()));
		info.setDeviceSn(key);
		info.setSession(session);
		SESSION_POOL.put(key, info);
	}

	/**
	 * 获得 session
	 *
	 * @param key
	 * @return
	 */
	public static WebSocketSession get(String key) {
		// 获得 session
		ClientInfo info = SESSION_POOL.get(key);
		if (info != null) {
			return info.getSession();
		} else {
			return null;
		}
	}

	/**
	 * 每一分钟检查一次ws状态
	 * 
	 * @return
	 */
	public static void checkWsStatus() {
		Set<Entry<String, ClientInfo>> set = SESSION_POOL.entrySet();
		Iterator<Entry<String, ClientInfo>> it = set.iterator();
		while (it.hasNext()) {
			Entry<String, ClientInfo> entry = (Entry<String, ClientInfo>) it.next();
			ClientInfo clientInfo = entry.getValue();
			int sec = DateUtil.secBetween(DateUtil.parseDateToString(new Date()), clientInfo.getAddTime());
			if (sec > 70) {
				String key = clientInfo.getDeviceSn();
				deviceOffline(key);
				removeAndClose(key);
				logger.info("用户:" + key + "连接已超时,删除并断开链路");
			}
		}
	}

	public static synchronized int getOnlineCount() {
		return onlineCount;
	}

	public static synchronized void addOnlineCount() {
		onlineCount++;
	}

	public static synchronized void delOnlineCount() {
		onlineCount--;
	}

	// 设备离线
	public static void deviceOffline(String deviceSn) {
		DeviceMapper deviceMapper = SpringUtil.getBean(DeviceMapper.class);
		Device device = deviceMapper.queryDeviceByDeviceSn(deviceSn);
		if (device != null) {
			device.setStatus(2);
			deviceMapper.updateById(device);
		}

		GateMapper gateMapper = SpringUtil.getBean(GateMapper.class);
		Integer geteId = gateMapper.getIdByDeviceSn(deviceSn);
		if (geteId != null) {
			Gate gate = gateMapper.getGateById(geteId);
			gate.setOnlineStatus(2);
			gateMapper.updateById(gate);
		}

	}

	// 设备上线
	public static void deviceOnline(String deviceSn) {
		DeviceMapper deviceMapper = SpringUtil.getBean(DeviceMapper.class);
		Device device = deviceMapper.queryDeviceByDeviceSn(deviceSn);
		if (device != null) {
			device.setStatus(1);
			deviceMapper.updateById(device);
		}
		GateMapper gateMapper = SpringUtil.getBean(GateMapper.class);
		Integer geteId = gateMapper.getIdByDeviceSn(deviceSn);
		if (geteId != null) {
			Gate gate = gateMapper.getGateById(geteId);
			gate.setOnlineStatus(1);
			gateMapper.updateById(gate);
		}
	}
}
