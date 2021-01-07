package com.wujie.springbootlayui.sys.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import com.wujie.springbootlayui.sys.common.*;
import com.wujie.springbootlayui.sys.entity.DeskParam;
import com.wujie.springbootlayui.sys.entity.Loginfo;
import com.wujie.springbootlayui.sys.entity.User;
import com.wujie.springbootlayui.sys.service.ILoginfoService;
import com.wujie.springbootlayui.sys.service.IUserService;
import com.wujie.springbootlayui.sys.vo.UserVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("login")
public class LoginController {

    @Autowired
    private ILoginfoService loginfoService;


    @Autowired
    private IUserService userService;


    @RequestMapping("login")
    public ResultObj login(HttpServletRequest request,UserVo userVo, String code, HttpSession session) {
        Integer language = userVo.getLoginLang();
        System.out.println("login lang: "+language);

        //获得存储在session中的验证码
        String sessionCode = (String) session.getAttribute("code");
        if (code != null && sessionCode.equals(code)) {
            Subject subject = SecurityUtils.getSubject();
            AuthenticationToken token = new UsernamePasswordToken(userVo.getLoginName(), userVo.getPwd());
            try {
                //对用户进行认证登陆
                subject.login(token);
                //通过subject获取以认证活动的user
                ActiverUser activerUser = (ActiverUser) subject.getPrincipal();
                String photoUrl = activerUser.getUser().getUserPhoto();
                if(!StringUtil.isEmpty(photoUrl)){
                  File file = new File(AppFileUtils.UPLOAD_PATH+photoUrl);
                  if(!file.exists()){
                      activerUser.getUser().setUserPhoto("face.jpg");
                  }
                }else {
                    activerUser.getUser().setUserPhoto("face.jpg");
                }

                activerUser.getUser().setLoginLang(language);
                System.out.println("68: activerUser.getUser().setLoginLang(language) "+language);

                //将user存储到session中
                WebUtils.getSession().setAttribute("user", activerUser.getUser());
                //查询当前用户拥有的角色
               /* List<Integer> currentUserRoleIds = roleService.queryUserRoleIdsByUid(activerUser.getUser().getId());
                WebUtils.getSession().setAttribute("roles",activerUser.getRoles());
                System.out.println("roles:"+activerUser.getRoles());*/
                User user = activerUser.getUser();
                user.setLoginTimes(user.getLoginTimes() + 1);
                userService.updateById(user);
                //记录登陆日志
                Loginfo entity = new Loginfo();
                entity.setLoginname(activerUser.getUser().getName() + "-" + activerUser.getUser().getLoginName());
                entity.setLoginip(WebUtils.getRequest().getRemoteAddr());
                entity.setLogintime(new Date());
                loginfoService.save(entity);

                return new ResultObj(Constast.OK,"messages.LOGIN_SUCCESS");
            } catch (AuthenticationException e) {
                e.printStackTrace();
                return new ResultObj(Constast.ERROR,"messages.LOGIN_ERROR_PASS");
            }
        } else {
            return new ResultObj(Constast.ERROR,"messages.LOGIN_ERROR_CODE");
        }

    }

    /**
     * 得到登陆验证码
     *
     * @param response
     * @param session
     * @throws IOException
     */
    @RequestMapping("getCode")
    public void getCode(HttpServletResponse response, HttpSession session) throws IOException {
        //定义图形验证码的长和宽
        response.setHeader("Connection", "keep-alive");
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(116, 36, 4, 5);
        session.setAttribute("code", lineCaptcha.getCode());
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            lineCaptcha.write(outputStream);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("getDeskData")
    public Map<String, Object> getDeskData() {
        Subject subject = SecurityUtils.getSubject();
        ActiverUser activerUser = (ActiverUser) subject.getPrincipal();
        Integer belongId = activerUser.getUser().getId();
        List<String> weeks = DateUtil.getDays(7);
         Integer[] attends=new Integer[7];
         Integer[] lateSum=new Integer[7];
         Integer[] earlySum=new Integer[7];
         Integer[] noClock=new Integer[7];
         Integer gates=0;
         Integer onGate=0;
         Integer offGate=0;
         Integer[] records=new Integer[7];
         Integer[] visitor=new Integer[7];
         Integer[] highTemp=new Integer[7];

        //Integer[] c = new Integer[3];

        for (int i = 0; i <weeks.size() ; i++) {
            DeskParam d = userService.getDesk(belongId,weeks.get(i)+"%");
            attends[i]=d.getAttends();
            lateSum[i]=d.getLateSum();
            earlySum[i]=d.getEarlySum();
            records[i] =d.getRecords();
            visitor[i]=d.getVisitor();
            highTemp[i]=d.getHighTemp();
            noClock[i]=d.getNoClock();
            if(i==0){
                gates=d.getGates();
                onGate=d.getOnGate();
                offGate=d.getOffGate();
            }

        }

        Map<String,Object> map = new HashMap<>();
        map.put("attends",attends);
        map.put("lateSum",lateSum);
        map.put("earlySum",earlySum);
        map.put("records",records);
        map.put("visitor",visitor);
        map.put("highTemp",highTemp);
        map.put("gates",gates);
        map.put("onGate",onGate);
        map.put("offGate",offGate);
        map.put("noClock",noClock);


        return map;
    }

}
