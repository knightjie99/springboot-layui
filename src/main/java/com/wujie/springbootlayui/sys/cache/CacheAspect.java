package com.wujie.springbootlayui.sys.cache;


import com.wujie.springbootlayui.sys.entity.App;
import com.wujie.springbootlayui.sys.entity.Dept;
import com.wujie.springbootlayui.sys.entity.Device;
import com.wujie.springbootlayui.sys.entity.DeviceError;
import com.wujie.springbootlayui.sys.entity.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.util.Map;


@Aspect
@Component
@EnableAspectJAutoProxy
public class CacheAspect {

    /**
     * 日志出处
     */
    private Log log = LogFactory.getLog(CacheAspect.class);

    /**
     * 声明一个缓存容器
     */
    private Map<String,Object> CACHE_CONTAINER = CachePool.CACHE_CONTAINER;
    
    /**
     * 声明异常记录的切换表达式
     */
    private static final String POINTCUT_DEVICE_ERROR_ADD="execution(* com.wujie.springbootlayui.sys.service.impl.DeviceErrorServiceImpl.save(..))";
    private static final String POINTCUT_DEVICE_ERROR_UPDATE="execution(* com.wujie.springbootlayui.sys.service.impl.DeviceErrorServiceImpl.updateById(..))";
    private static final String POINTCUT_DEVICE_ERROR_GET="execution(* com.wujie.springbootlayui.sys.service.impl.DeviceErrorServiceImpl.getById(..))";
    private static final String POINTCUT_DEVICE_ERROR_DELETE="execution(* com.wujie.springbootlayui.sys.service.impl.DeviceErrorServiceImpl.removeById(..))";

    private static final String CACHE_DEVICE_ERROR_PROFIX="deviceError:"; 
    
    /**
     * 添加异常记录切入
     * @param joinPoint
     * @return
     */
    @Around(value = POINTCUT_DEVICE_ERROR_ADD)
    public Object cacheDeviceErrorAdd(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
    	DeviceError object = (DeviceError) joinPoint.getArgs()[0];
        Boolean res = (Boolean) joinPoint.proceed();
        if (res){
            CACHE_CONTAINER.put(CACHE_DEVICE_ERROR_PROFIX + object.getId(),object);
        }
        return res;
    }
    
    /**
     * 查询异常记录切入
     * @param joinPoint
     * @return
     */
    @Around(value = POINTCUT_DEVICE_ERROR_GET)
    public Object cacheDeviceErrorGet(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
        Integer object = (Integer) joinPoint.getArgs()[0];
        //从缓存里面取
        Object res1 = CACHE_CONTAINER.get(CACHE_DEVICE_ERROR_PROFIX + object);
        if (res1!=null){
            log.info("已从缓存里面找到异常记录对象"+CACHE_DEVICE_ERROR_PROFIX + object);
            return res1;
        }else {
            log.info("未从缓存里面找到异常记录对象，从数据库中查询并放入缓存");
            DeviceError res2 =(DeviceError) joinPoint.proceed();
            CACHE_CONTAINER.put(CACHE_DEVICE_ERROR_PROFIX+res2.getId(),res2);
            return res2;
        }
    }

    /**
     * 更新异常记录切入
     * @param joinPoint
     * @return
     */
    @Around(value = POINTCUT_DEVICE_ERROR_UPDATE)
    public Object cacheDeviceErrorUpdate(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
    	DeviceError deviceErrorVo = (DeviceError) joinPoint.getArgs()[0];
        Boolean isSuccess = (Boolean) joinPoint.proceed();
        if (isSuccess){
        	DeviceError deviceError =(DeviceError) CACHE_CONTAINER.get(CACHE_DEVICE_ERROR_PROFIX + deviceErrorVo.getId());
            if (null==deviceError){
            	deviceError = new DeviceError();
            }
            BeanUtils.copyProperties(deviceErrorVo,deviceError);
            log.info("异常记录对象缓存已更新"+CACHE_DEVICE_ERROR_PROFIX + deviceErrorVo.getId());
            CACHE_CONTAINER.put(CACHE_DEVICE_ERROR_PROFIX+deviceError.getId(),deviceError);
        }
        return isSuccess;
    }

    /**
     * 删除异常记录切入
     * @param joinPoint
     * @return
     */
    @Around(value = POINTCUT_DEVICE_ERROR_DELETE)
    public Object cacheDeviceErrorDelete(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
        Integer id = (Integer) joinPoint.getArgs()[0];
        Boolean isSuccess = (Boolean) joinPoint.proceed();
        if (isSuccess){
            //删除缓存
            CACHE_CONTAINER.remove(CACHE_DEVICE_ERROR_PROFIX+id);
        }
        return isSuccess;
    }
    
    /**
     * 声明设备的切面表达式
     */
    private static final String POINTCUT_DEVICE_ADD="execution(* com.wujie.springbootlayui.sys.service.impl.DeviceServiceImpl.save(..))";
    private static final String POINTCUT_DEVICE_UPDATE="execution(* com.wujie.springbootlayui.sys.service.impl.DeviceServiceImpl.updateById(..))";
    private static final String POINTCUT_DEVICE_GET="execution(* com.wujie.springbootlayui.sys.service.impl.DeviceServiceImpl.getById(..))";
    private static final String POINTCUT_DEVICE_DELETE="execution(* com.wujie.springbootlayui.sys.service.impl.DeviceServiceImpl.removeById(..))";

    private static final String CACHE_DEVICE_PROFIX="device:";
    
    /**
     * 添加设备切入
     * @param joinPoint
     * @return
     */
    @Around(value = POINTCUT_DEVICE_ADD)
    public Object cacheDeviceAdd(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
    	Device object = (Device) joinPoint.getArgs()[0];
        Boolean res = (Boolean) joinPoint.proceed();
        if (res){
            CACHE_CONTAINER.put(CACHE_DEVICE_PROFIX + object.getId(),object);
        }
        return res;
    }

    /**
     * 查询设备切入
     * @param joinPoint
     * @return
     */
    @Around(value = POINTCUT_DEVICE_GET)
    public Object cacheDeviceGet(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
        Integer object = (Integer) joinPoint.getArgs()[0];
        //从缓存里面取
        Object res1 = CACHE_CONTAINER.get(CACHE_DEVICE_PROFIX + object);
        if (res1!=null){
            log.info("已从缓存里面找到设备对象"+CACHE_DEVICE_PROFIX + object);
            return res1;
        }else {
            log.info("未从缓存里面找到设备对象，从数据库中查询并放入缓存");
            Device res2 =(Device) joinPoint.proceed();
            CACHE_CONTAINER.put(CACHE_DEVICE_PROFIX+res2.getId(),res2);
            return res2;
        }
    }

    /**
     * 更新设备切入
     * @param joinPoint
     * @return
     */
    @Around(value = POINTCUT_DEVICE_UPDATE)
    public Object cacheDeviceUpdate(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
    	Device deviceVo = (Device) joinPoint.getArgs()[0];
        Boolean isSuccess = (Boolean) joinPoint.proceed();
        if (isSuccess){
            Device device =(Device) CACHE_CONTAINER.get(CACHE_DEVICE_PROFIX + deviceVo.getId());
            if (null==device){
            	device = new Device();
            }
            BeanUtils.copyProperties(deviceVo,device);
            log.info("设备对象缓存已更新"+CACHE_DEVICE_PROFIX + deviceVo.getId());
            CACHE_CONTAINER.put(CACHE_DEVICE_PROFIX+device.getId(),device);
        }
        return isSuccess;
    }

    /**
     * 删除设备切入
     * @param joinPoint
     * @return
     */
    @Around(value = POINTCUT_DEVICE_DELETE)
    public Object cacheDeviceDelete(ProceedingJoinPoint joinPoint) throws Throwable {

        //取出第一个参数
        Integer id = (Integer) joinPoint.getArgs()[0];
        Boolean isSuccess = (Boolean) joinPoint.proceed();
        if (isSuccess){
            //删除缓存
            CACHE_CONTAINER.remove(CACHE_DEVICE_PROFIX+id);
        }
        return isSuccess;
    }
    

    
    /**
     * 声明App应用的切面表达式
     */
    private static final String POINTCUT_APP_ADD="execution(* com.wujie.springbootlayui.sys.service.impl.AppServiceImpl.save(..))";
    private static final String POINTCUT_APP_UPDATE="execution(* com.wujie.springbootlayui.sys.service.impl.AppServiceImpl.updateById(..))";
    private static final String POINTCUT_APP_GET="execution(* com.wujie.springbootlayui.sys.service.impl.AppServiceImpl.getById(..))";
    private static final String POINTCUT_APP_DELETE="execution(* com.wujie.springbootlayui.sys.service.impl.AppServiceImpl.removeById(..))";

    private static final String CACHE_APP_PROFIX="app:";
    
    /**
     * 添加应用切入
     * @param joinPoint
     * @return
     */
    @Around(value = POINTCUT_APP_ADD)
    public Object cacheAppAdd(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
        App object = (App) joinPoint.getArgs()[0];
        Boolean res = (Boolean) joinPoint.proceed();
        if (res){
            CACHE_CONTAINER.put(CACHE_APP_PROFIX + object.getId(),object);
        }
        return res;
    }

    /**
     * 查询应用切入
     * @param joinPoint
     * @return
     */
    @Around(value = POINTCUT_APP_GET)
    public Object cacheAppGet(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
        Integer object = (Integer) joinPoint.getArgs()[0];
        //从缓存里面取
        Object res1 = CACHE_CONTAINER.get(CACHE_APP_PROFIX + object);
        if (res1!=null){
            log.info("已从缓存里面找到应用对象"+CACHE_APP_PROFIX + object);
            return res1;
        }else {
            log.info("未从缓存里面找到应用对象，从数据库中查询并放入缓存");
            Dept res2 =(Dept) joinPoint.proceed();
            CACHE_CONTAINER.put(CACHE_APP_PROFIX+res2.getId(),res2);
            return res2;
        }
    }

    /**
     * 更新应用切入
     * @param joinPoint
     * @return
     */
    @Around(value = POINTCUT_APP_UPDATE)
    public Object cacheAppUpdate(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
        App appVo = (App) joinPoint.getArgs()[0];
        Boolean isSuccess = (Boolean) joinPoint.proceed();
        if (isSuccess){
            App app =(App) CACHE_CONTAINER.get(CACHE_APP_PROFIX + appVo.getId());
            if (null==app){
            	app = new App();
            }
            BeanUtils.copyProperties(appVo,app);
            log.info("应用对象缓存已更新"+CACHE_APP_PROFIX + appVo.getId());
            CACHE_CONTAINER.put(CACHE_APP_PROFIX+app.getId(),app);
        }
        return isSuccess;
    }

    /**
     * 删除应用切入
     * @param joinPoint
     * @return
     */
    @Around(value = POINTCUT_APP_DELETE)
    public Object cacheAppDelete(ProceedingJoinPoint joinPoint) throws Throwable {

        //取出第一个参数
        Integer id = (Integer) joinPoint.getArgs()[0];
        Boolean isSuccess = (Boolean) joinPoint.proceed();
        if (isSuccess){
            //删除缓存
            CACHE_CONTAINER.remove(CACHE_APP_PROFIX+id);
        }
        return isSuccess;
    }
    

    /**
     * 声明部门的切面表达式
     */
    private static final String POINTCUT_DEPT_ADD="execution(* com.wujie.springbootlayui.sys.service.impl.DeptServiceImpl.save(..))";
    private static final String POINTCUT_DEPT_UPDATE="execution(* com.wujie.springbootlayui.sys.service.impl.DeptServiceImpl.updateById(..))";
    private static final String POINTCUT_DEPT_GET="execution(* com.wujie.springbootlayui.sys.service.impl.DeptServiceImpl.getById(..))";
    private static final String POINTCUT_DEPT_DELETE="execution(* com.wujie.springbootlayui.sys.service.impl.DeptServiceImpl.removeById(..))";

    private static final String CACHE_DEPT_PROFIX="dept:";

    /**
     * 添加部门切入
     * @param joinPoint
     * @return
     */
    @Around(value = POINTCUT_DEPT_ADD)
    public Object cacheDeptAdd(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
        Dept object = (Dept) joinPoint.getArgs()[0];
        Boolean res = (Boolean) joinPoint.proceed();
        if (res){
            CACHE_CONTAINER.put(CACHE_DEPT_PROFIX + object.getId(),object);
        }
        return res;
    }

    /**
     * 查询部门切入
     * @param joinPoint
     * @return
     */
    @Around(value = POINTCUT_DEPT_GET)
    public Object cacheDeptGet(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
        Integer object = (Integer) joinPoint.getArgs()[0];
        //从缓存里面取
        Object res1 = CACHE_CONTAINER.get(CACHE_DEPT_PROFIX + object);
        if (res1!=null){
            log.info("已从缓存里面找到部门对象"+CACHE_DEPT_PROFIX + object);
            return res1;
        }else {
            log.info("未从缓存里面找到部门对象，从数据库中查询并放入缓存");
            Dept res2 =(Dept) joinPoint.proceed();
            CACHE_CONTAINER.put(CACHE_DEPT_PROFIX+res2.getId(),res2);
            return res2;
        }
    }

    /**
     * 更新部门切入
     * @param joinPoint
     * @return
     */
    @Around(value = POINTCUT_DEPT_UPDATE)
    public Object cacheDeptUpdate(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
        Dept deptVo = (Dept) joinPoint.getArgs()[0];
        Boolean isSuccess = (Boolean) joinPoint.proceed();
        if (isSuccess){
            Dept dept =(Dept) CACHE_CONTAINER.get(CACHE_DEPT_PROFIX + deptVo.getId());
            if (null==dept){
                dept=new Dept();
            }
            BeanUtils.copyProperties(deptVo,dept);
            log.info("部门对象缓存已更新"+CACHE_DEPT_PROFIX + deptVo.getId());
            CACHE_CONTAINER.put(CACHE_DEPT_PROFIX+dept.getId(),dept);
        }
        return isSuccess;
    }

    /**
     * 删除部门切入
     * @param joinPoint
     * @return
     */
    @Around(value = POINTCUT_DEPT_DELETE)
    public Object cacheDeptDelete(ProceedingJoinPoint joinPoint) throws Throwable {

        //取出第一个参数
        Integer id = (Integer) joinPoint.getArgs()[0];
        Boolean isSuccess = (Boolean) joinPoint.proceed();
        if (isSuccess){
            //删除缓存
            CACHE_CONTAINER.remove(CACHE_DEPT_PROFIX+id);
        }
        return isSuccess;
    }

    /**
     * 声明用户的切面表达式
     */
    private static final String POINTCUT_USER_UPDATE="execution(* com.wujie.springbootlayui.sys.service.impl.UserServiceImpl.updateById(..))";
    private static final String POINTCUT_USER_ADD="execution(* com.wujie.springbootlayui.sys.service.impl.UserServiceImpl.updateById(..))";
    private static final String POINTCUT_USER_GET="execution(* com.wujie.springbootlayui.sys.service.impl.UserServiceImpl.getById(..))";
    private static final String POINTCUT_USER_DELETE="execution(* com.wujie.springbootlayui.sys.service.impl.UserServiceImpl.removeById(..))";

    private static final String CACHE_USER_PROFIX="user:";

    /**
     * 添加用户切入
     * @param joinPoint
     * @return
     */
    @Around(value = POINTCUT_USER_ADD)
    public Object cacheUserAdd(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
        User object = (User) joinPoint.getArgs()[0];
        Boolean res = (Boolean) joinPoint.proceed();
        if (res){
            CACHE_CONTAINER.put(CACHE_USER_PROFIX + object.getId(),object);
        }
        return res;
    }

    /**
     * 查询用户切入
     * @param joinPoint
     * @return
     */
    @Around(value = POINTCUT_USER_GET)
    public Object cacheUserGet(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
        Integer object = (Integer) joinPoint.getArgs()[0];
        //从缓存里面取
        Object res1 = CACHE_CONTAINER.get(CACHE_USER_PROFIX + object);
        if (res1!=null){
            log.info("已从缓存里面找到用户对象"+CACHE_USER_PROFIX + object);
            return res1;
        }else {
            log.info("未从缓存里面找到用户对象，从数据库中查询并放入缓存");
            User res2 =(User) joinPoint.proceed();
            CACHE_CONTAINER.put(CACHE_USER_PROFIX+res2.getId(),res2);
            return res2;
        }
    }

    /**
     * 更新用户切入
     * @param joinPoint
     * @return
     */
    @Around(value = POINTCUT_USER_UPDATE)
    public Object cacheUserUpdate(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
        User userVo = (User) joinPoint.getArgs()[0];
        Boolean isSuccess = (Boolean) joinPoint.proceed();
        if (isSuccess){
            User user =(User) CACHE_CONTAINER.get(CACHE_USER_PROFIX + userVo.getId());
            if (null==user){
                user=new User();
            }
            BeanUtils.copyProperties(userVo,user);
            log.info("用户对象缓存已更新"+CACHE_USER_PROFIX + userVo.getId());
            CACHE_CONTAINER.put(CACHE_USER_PROFIX+user.getId(),user);
        }
        return isSuccess;
    }

    /**
     * 删除用户切入
     * @param joinPoint
     * @return
     */
    @Around(value = POINTCUT_USER_DELETE)
    public Object cacheUserDelete(ProceedingJoinPoint joinPoint) throws Throwable {

        //取出第一个参数
        Integer id = (Integer) joinPoint.getArgs()[0];
        Boolean isSuccess = (Boolean) joinPoint.proceed();
        if (isSuccess){
            //删除缓存
            CACHE_CONTAINER.remove(CACHE_USER_PROFIX+id);
        }
        return isSuccess;
    }

}
