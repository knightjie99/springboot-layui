package com.wujie.springbootlayui.sys.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.StringUtils;

public class StringUtil extends StringUtils {

	/* 判断字符串为空 */
	public static boolean isEmpty(String str) {
		if ("".equals(str) || str == null) {
			return true;
		}
		String pattern = "\\S";
		Pattern p = Pattern.compile(pattern, 2 | Pattern.DOTALL);
		Matcher m = p.matcher(str);
		return !m.find();
	}

	public static String getRandom() {
		Random random = new Random();
		String result = "";
		for (int i = 0; i < 6; i++) {
			result += random.nextInt(10);
		}
		return result;
	}

	public static boolean isPhone(String phone) {
		if (isEmpty(phone)) {
			return false;
		}
		if (phone.length() != 11) {
			return false;
		}
		String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(phone);
		if (m.matches()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 获取当前时间的字符串
	 */
	public static String getCurrentTime() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = sdf.format(date);
		return time;
	}

	/**
	 * 获取时间差多少秒
	 * 
	 * @param args
	 */
	public static Integer compareToCurrentTime(String time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Integer seconds = 0;
		try {
			Date d1 = sdf.parse(time);
			Date d2 = new Date();
			long diff = d2.getTime() - d1.getTime();
			seconds = (int) diff / 1000;
		} catch (ParseException e) {
			System.out.println("抱歉，时间日期解析出错。");
		}
		return seconds;
	}

	/**
	 * 密码验证
	 * 
	 * @param pwd
	 * @return
	 */
	public static boolean checkStr(String pwd) {
		String regExp = "^[\\w_]{6,20}$";
		if (pwd.matches(regExp)) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		Set<String> set = new HashSet<String>();
		set.add("1");
		set.add("1");
		set.add("1");
		set.add("1");
		set.add("2");
		for (String aaa : set) {
			System.out.println(aaa);
		}
	}

}
