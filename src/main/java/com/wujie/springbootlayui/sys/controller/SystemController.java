package com.wujie.springbootlayui.sys.controller;

import com.wujie.springbootlayui.sys.common.ActiverUser;
import com.wujie.springbootlayui.sys.common.StringUtil;
import com.wujie.springbootlayui.sys.common.WebUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@Controller
@RequestMapping("sys")
public class SystemController {
	/**
	 * 跳转到重置密码
	 */
	@RequestMapping("toResetPassword")
	public String toResetPassword() {
		return "system/password/forgetPassword";
	}

	/**
	 * 跳转到注册
	 */
	@RequestMapping("toRegister")
	public String toRegister() {
		return "system/register/register";
	}

	/**
	 * 跳转到注册成功界面
	 */
	@RequestMapping("registerSuccess")
	public String registerSuccess() {
		return "system/register/registerSuccess";
	}

	/**
	 * 跳转到注册管理界面
	 */
	@RequestMapping("toRegisterManager")
	public String toRegisterManager() {
		return "system/register/registerManager";
	}

	/**
	 * 跳转到应用管理界面
	 */
	@RequestMapping("toAppManager")
	public String toAppManager() {
		return "system/app/appManager";
	}

	/**
	 * 跳转到设备管理界面
	 */
	@RequestMapping("toDeviceManager")
	public String toDeviceManager() {
		return "system/device/deviceManager";
	}

	/**
	 * 跳转到异常记录界面
	 */
	@RequestMapping("toDeviceErrorManager")
	public String toDeviceErrorManager() {
		return "system/deviceError/deviceErrorManager";
	}

	/**
	 * 跳转到登陆页面
	 * 
	 * @return
	 */
	@RequestMapping("toLogin")
	public String toLogin(HttpServletRequest request, Model model) {
		Subject subject = SecurityUtils.getSubject();

		if (WebUtils.getSession().getAttribute("user") != null) {
			WebUtils.getSession().removeAttribute("user");
			subject.logout();
		}

		String lang = request.getParameter("lang");
		if (StringUtil.isEmpty(lang)) {
			Locale.setDefault(Locale.CHINA);
			model.addAttribute("lang", 1);
		} else {
			String[] split = lang.split("_");
			String country = split[1];
			if (country.equals("US")) {
				model.addAttribute("lang", 2);
			} else {
				model.addAttribute("lang", 1);
			}
		}
		return "system/index/login";
	}

	@RequestMapping("select")
	public String select(Model model) {
		Subject subject = SecurityUtils.getSubject();
		ActiverUser activerUser = (ActiverUser) subject.getPrincipal();
		Integer belongId = activerUser.getUser().getId();
		Integer lang = activerUser.getUser().getLoginLang();
		model.addAttribute(activerUser.getUser().getLoginLang());

		if (lang != null && lang == 2) {
			model.addAttribute("lang", 2);
		} else {
			model.addAttribute("lang", 1);
		}

		if (belongId != null && belongId == 1) {
			return "system/index/index";
		} else {
			return "system/index/systemSel";
		}

	}

	/**
	 * 登出
	 */
	// @RequestMapping("toLogout")
	// public String toLogout(Model model){
	// Subject subject = SecurityUtils.getSubject();
	// subject.logout();
	// WebUtils.getSession().removeAttribute("user");
	// model.addAttribute("lang",1);
	// return "system/index/login";
	// }

	/**
	 * 跳转到个人资料页面
	 * 
	 * @return
	 */
	@RequestMapping("toUserInfo")
	public String toUserInfo() {
		return "system/user/userInfo";
	}

	/**
	 * 跳转到修改密码页面
	 * 
	 * @return
	 */
	@RequestMapping("toChangePassword")
	public String toChangePassword() {
		return "system/user/changePassword";
	}

	/**
	 * 跳转到首页
	 * 
	 * @return
	 */
	@RequestMapping("index")
	public String index(Integer role) {
		if (role == null) {
			return "system/index/login";
		}
		Subject subject = SecurityUtils.getSubject();
		ActiverUser activerUser = (ActiverUser) subject.getPrincipal();
		activerUser.setLoginRole(role);

		return "system/index/index";
	}

	/**
	 * 跳转到登陆台
	 * 
	 * @return
	 */
	@RequestMapping("toDeskManager")
	public String toDeskManager() {
		return "system/index/deskManager";
	}

	/**
	 * 跳转到日志管理
	 * 
	 * @return
	 */
	@RequestMapping("toLoginfoManager")
	public String toLoginfoManager() {
		return "system/loginfo/loginfoManager";
	}

	/**
	 * 跳转到公告管理
	 * 
	 * @return
	 */
	@RequestMapping("toNoticeManager")
	public String toNoticeManager() {
		return "system/notice/noticeManager";
	}

	/**
	 * 跳转到部门管理
	 * 
	 * @return
	 */
	@RequestMapping("toDeptManager")
	public String toDeptManager() {
		return "system/dept/deptManager";
	}

	/**
	 * 跳转到部门管理--left
	 * 
	 * @return
	 */
	@RequestMapping("toDeptLeft")
	public String toDeptLeft() {
		return "system/dept/deptLeft";
	}

	/**
	 * 跳转到部门管理--right
	 * 
	 * @return
	 */
	@RequestMapping("toDeptRight")
	public String toDeptRight() {
		return "system/dept/deptRight";
	}

	/**
	 * 跳转到菜单管理
	 * 
	 * @return
	 */
	@RequestMapping("toMenuManager")
	public String toMenuManager() {
		return "system/menu/menuManager";
	}

	/**
	 * 跳转到菜单管理--left
	 * 
	 * @return
	 */
	@RequestMapping("toMenuLeft")
	public String toMenuLeft() {
		return "system/menu/menuLeft";
	}

	/**
	 * 跳转到菜单管理--right
	 * 
	 * @return
	 */
	@RequestMapping("toMenuRight")
	public String toMenuRight() {
		return "system/menu/menuRight";
	}

	/**
	 * 跳转到权限管理
	 * 
	 * @return
	 */
	@RequestMapping("toPermissionManager")
	public String toPermissionManager() {
		return "system/permission/permissionManager";
	}

	/**
	 * 跳转到权限管理--left
	 * 
	 * @return
	 */
	@RequestMapping("toPermissionLeft")
	public String toPermissionLeft() {
		return "system/permission/permissionLeft";
	}

	/**
	 * 跳转到权限管理--right
	 * 
	 * @return
	 */
	@RequestMapping("toPermissionRight")
	public String toPermissionRight() {
		return "system/permission/permissionRight";
	}

	/**
	 * 跳转到角色管理
	 * 
	 * @return
	 */
	@RequestMapping("toRoleManager")
	public String toRoleManager() {
		return "system/role/roleManager";
	}

	/**
	 * 跳转到用户管理
	 * 
	 * @return
	 */
	@RequestMapping("toUserManager")
	public String toUserManager() {
		return "system/user/userManager";
	}

	/**
	 * 跳转到缓存管理
	 * 
	 * @return
	 */
	@RequestMapping("toCacheManager")
	public String toCacheManager() {
		return "system/cache/cacheManager";
	}

}
