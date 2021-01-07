package com.wujie.springbootlayui.bus.cache;


import com.wujie.springbootlayui.bus.entity.AttendRule;
import com.wujie.springbootlayui.bus.entity.AttendStaff;
import com.wujie.springbootlayui.bus.entity.Gate;
import com.wujie.springbootlayui.bus.entity.GateAuthor;
import com.wujie.springbootlayui.bus.entity.PassRecord;
import com.wujie.springbootlayui.bus.entity.Staff;
import com.wujie.springbootlayui.bus.entity.StaffCard;
import com.wujie.springbootlayui.sys.cache.CachePool;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

@Aspect
@Component
@EnableAspectJAutoProxy
public class BusinessCacheAspect {
    /**
     * 日志出处
     */
    private Log log = LogFactory.getLog(BusinessCacheAspect.class);

    /**
     * 声明一个缓存容器
     */
    private Map<String,Object> CACHE_CONTAINER = CachePool.CACHE_CONTAINER;

    /**
     * 声明考勤规则缓存
     */
    private static final String POINTCUT_ATTEND_STAFF_ADD="execution(* com.wujie.springbootlayui.bus.service.impl.AttendStaffServiceImpl.save(..))";
    private static final String POINTCUT_ATTEND_STAFF_UPDATE="execution(* com.wujie.springbootlayui.bus.service.impl.AttendStaffServiceImpl.updateById(..))";
    private static final String POINTCUT_ATTEND_STAFF_GET="execution(* com.wujie.springbootlayui.bus.service.impl.AttendStaffServiceImpl.getById(..))";
    private static final String POINTCUT_ATTEND_STAFF_DELETE="execution(* com.wujie.springbootlayui.bus.service.impl.AttendStaffServiceImpl.removeById(..))";
    private static final String CACHE_ATTEND_STAFF_PROFIX="attendStaff:";

    /**
     * 添加人员排班切入
     * @param joinPoint
     * @return
     */
    @Around(value = POINTCUT_ATTEND_STAFF_ADD)
    public Object cacheAttendStaffAdd(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
        AttendStaff object = (AttendStaff) joinPoint.getArgs()[0];
        Boolean res = (Boolean) joinPoint.proceed();
        if (res){
            CACHE_CONTAINER.put(CACHE_ATTEND_STAFF_PROFIX + object.getId(),object);
        }
        return res;
    }

    /**
     * 查询人员排班切入
     * @param joinPoint
     * @return
     */
    @Around(value = POINTCUT_ATTEND_STAFF_GET)
    public Object cacheAttendStaffGet(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
        Integer object = (Integer) joinPoint.getArgs()[0];
        //从缓存里面取
        Object res1 = CACHE_CONTAINER.get(CACHE_ATTEND_STAFF_PROFIX + object);
        if (res1!=null){
            log.info("已从缓存里面找到人员排班对象"+CACHE_ATTEND_STAFF_PROFIX + object);
            return res1;
        }else {
            log.info("未从缓存里面找到人员排班对象，从数据库中查询并放入缓存");
            AttendStaff res2 = (AttendStaff) joinPoint.proceed();
            CACHE_CONTAINER.put(CACHE_ATTEND_STAFF_PROFIX + res2.getId(),res2);
            return res2;
        }
    }


    /**
     * 更新人员排班切入
     * @param joinPoint
     * @return
     */
    @Around(value = POINTCUT_ATTEND_STAFF_UPDATE)
    public Object cacheAttendStaffUpdate(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
        AttendStaff attendStaffVo = (AttendStaff) joinPoint.getArgs()[0];
        Boolean isSuccess = (Boolean) joinPoint.proceed();
        if (isSuccess){
            AttendStaff attendStaff =(AttendStaff) CACHE_CONTAINER.get(CACHE_ATTEND_STAFF_PROFIX + attendStaffVo.getId());
            if (null == attendStaff){
                attendStaff = new AttendStaff();
            }
            BeanUtils.copyProperties(attendStaffVo,attendStaff);
            log.info("人员排班对象缓存已更新"+CACHE_ATTEND_STAFF_PROFIX + attendStaffVo.getId());
            CACHE_CONTAINER.put(CACHE_ATTEND_STAFF_PROFIX + attendStaff.getId(),attendStaff);
        }
        return isSuccess;
    }


    /**
     * 删除人员排班切入
     * @param joinPoint
     * @return
     */
    @Around(value = POINTCUT_ATTEND_STAFF_DELETE)
    public Object cacheAttendStaffDelete(ProceedingJoinPoint joinPoint) throws Throwable {

        //取出第一个参数
        Integer id = (Integer) joinPoint.getArgs()[0];
        Boolean isSuccess = (Boolean) joinPoint.proceed();
        if (isSuccess){
            //删除缓存
            CACHE_CONTAINER.remove(CACHE_ATTEND_STAFF_PROFIX+id);
        }
        return isSuccess;
    }



    /**
     * 声明考勤规则缓存
     */
    private static final String POINTCUT_ATTEND_RULE_ADD="execution(* com.wujie.springbootlayui.bus.service.impl.AttendRuleServiceImpl.save(..))";
    private static final String POINTCUT_ATTEND_RULE_UPDATE="execution(* com.wujie.springbootlayui.bus.service.impl.AttendRuleServiceImpl.updateById(..))";
    private static final String POINTCUT_ATTEND_RULE_GET="execution(* com.wujie.springbootlayui.bus.service.impl.AttendRuleServiceImpl.getById(..))";
    private static final String POINTCUT_ATTEND_RULE_DELETE="execution(* com.wujie.springbootlayui.bus.service.impl.AttendRuleServiceImpl.removeById(..))";
    private static final String CACHE_ATTEND_RULE_PROFIX="attendRule:";

    /**
     * 添加考勤规则切入
     * @param joinPoint
     * @return
     */
    @Around(value = POINTCUT_ATTEND_RULE_ADD)
    public Object cacheAttendRuleAdd(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
        AttendRule object = (AttendRule) joinPoint.getArgs()[0];
        Boolean res = (Boolean) joinPoint.proceed();
        if (res){
            CACHE_CONTAINER.put(CACHE_ATTEND_RULE_PROFIX + object.getId(),object);
        }
        return res;
    }

    /**
     * 查询考勤规则切入
     * @param joinPoint
     * @return
     */
    @Around(value = POINTCUT_ATTEND_RULE_GET)
    public Object cacheAttendRuleGet(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
        Integer object = (Integer) joinPoint.getArgs()[0];
        //从缓存里面取
        Object res1 = CACHE_CONTAINER.get(CACHE_ATTEND_RULE_PROFIX + object);
        if (res1!=null){
            log.info("已从缓存里面找到考勤规则对象"+CACHE_ATTEND_RULE_PROFIX + object);
            return res1;
        }else {
            log.info("未从缓存里面找到考勤规则对象，从数据库中查询并放入缓存");
            AttendRule res2 =(AttendRule) joinPoint.proceed();
            CACHE_CONTAINER.put(CACHE_ATTEND_RULE_PROFIX+res2.getId(),res2);
            return res2;
        }
    }


    /**
     * 更新考勤规则切入
     * @param joinPoint
     * @return
     */
    @Around(value = POINTCUT_ATTEND_RULE_UPDATE)
    public Object cacheAttendRuleUpdate(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
        AttendRule ruleVo = (AttendRule) joinPoint.getArgs()[0];
        Boolean isSuccess = (Boolean) joinPoint.proceed();
        if (isSuccess){
            AttendRule rule =(AttendRule) CACHE_CONTAINER.get(CACHE_ATTEND_RULE_PROFIX + ruleVo.getId());
            if (null == rule){
                rule = new AttendRule();
            }
            BeanUtils.copyProperties(ruleVo,rule);
            log.info("考勤规则对象缓存已更新"+CACHE_ATTEND_RULE_PROFIX + ruleVo.getId());
            CACHE_CONTAINER.put(CACHE_ATTEND_RULE_PROFIX+rule.getId(),rule);
        }
        return isSuccess;
    }


    /**
     * 删除考勤规则切入
     * @param joinPoint
     * @return
     */
    @Around(value = POINTCUT_ATTEND_RULE_DELETE)
    public Object cacheAttendRuleDelete(ProceedingJoinPoint joinPoint) throws Throwable {

        //取出第一个参数
        Integer id = (Integer) joinPoint.getArgs()[0];
        Boolean isSuccess = (Boolean) joinPoint.proceed();
        if (isSuccess){
            //删除缓存
            CACHE_CONTAINER.remove(CACHE_ATTEND_RULE_PROFIX+id);
        }
        return isSuccess;
    }



    /**
     * 声明通行记录缓存
     */
    private static final String POINTCUT_PASSRECORD_ADD="execution(* com.wujie.springbootlayui.bus.service.impl.PassRecordServiceImpl.save(..))";
    private static final String POINTCUT_PASSRECORD_UPDATE="execution(* com.wujie.springbootlayui.bus.service.impl.PassRecordServiceImpl.updateById(..))";
    private static final String POINTCUT_PASSRECORD_GET="execution(* com.wujie.springbootlayui.bus.service.impl.PassRecordServiceImpl.getById(..))";
    private static final String POINTCUT_PASSRECORD_DELETE="execution(* com.wujie.springbootlayui.bus.service.impl.PassRecordServiceImpl.removeById(..))";
    private static final String CACHE_PASSRECORD_PROFIX="passRecord:";

    /**
     * 添加通行记录切入
     * @param joinPoint
     * @return
     */
    @Around(value = POINTCUT_PASSRECORD_ADD)
    public Object cachePassRecordAdd(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
        PassRecord object = (PassRecord) joinPoint.getArgs()[0];
        Boolean res = (Boolean) joinPoint.proceed();
        if (res){
            CACHE_CONTAINER.put(CACHE_PASSRECORD_PROFIX + object.getId(),object);
        }
        return res;
    }

    /**
     * 查询通行记录切入
     * @param joinPoint
     * @return
     */
    @Around(value = POINTCUT_PASSRECORD_GET)
    public Object cachePassRecordGet(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
        Integer object = (Integer) joinPoint.getArgs()[0];
        //从缓存里面取
        Object res1 = CACHE_CONTAINER.get(CACHE_PASSRECORD_PROFIX + object);
        if (res1!=null){
            log.info("已从缓存里面找到通行记录对象"+CACHE_PASSRECORD_PROFIX + object);
            return res1;
        }else {
            log.info("未从缓存里面找到通行记录对象，从数据库中查询并放入缓存");
            PassRecord res2 =(PassRecord) joinPoint.proceed();
            CACHE_CONTAINER.put(CACHE_PASSRECORD_PROFIX+res2.getId(),res2);
            return res2;
        }
    }


    /**
     * 更新通行记录切入
     * @param joinPoint
     * @return
     */
    @Around(value = POINTCUT_PASSRECORD_UPDATE)
    public Object cachePassRecordUpdate(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
        PassRecord passRecordVo = (PassRecord) joinPoint.getArgs()[0];
        Boolean isSuccess = (Boolean) joinPoint.proceed();
        if (isSuccess){
            PassRecord reord =(PassRecord) CACHE_CONTAINER.get(CACHE_PASSRECORD_PROFIX + passRecordVo.getId());
            if (null == reord){
                reord = new PassRecord();
            }
            BeanUtils.copyProperties(passRecordVo,reord);
            log.info("通行记录对象缓存已更新"+CACHE_PASSRECORD_PROFIX + passRecordVo.getId());
            CACHE_CONTAINER.put(CACHE_PASSRECORD_PROFIX+reord.getId(),reord);
        }
        return isSuccess;
    }


    /**
     * 删除通行记录切入
     * @param joinPoint
     * @return
     */
    @Around(value = POINTCUT_PASSRECORD_DELETE)
    public Object cachePassRecordDelete(ProceedingJoinPoint joinPoint) throws Throwable {

        //取出第一个参数
        Integer id = (Integer) joinPoint.getArgs()[0];
        Boolean isSuccess = (Boolean) joinPoint.proceed();
        if (isSuccess){
            //删除缓存
            CACHE_CONTAINER.remove(CACHE_PASSRECORD_PROFIX+id);
        }
        return isSuccess;
    }

    /**
     * 声明闸机授权的切面表达式
     */
    private static final String POINTCUT_GATE_AUTHOR_ADD="execution(* com.wujie.springbootlayui.bus.service.impl.GateAuthorServiceImpl.save(..))";
    private static final String POINTCUT_GATE_AUTHOR_UPDATE="execution(* com.wujie.springbootlayui.bus.service.impl.GateAuthorServiceImpl.updateById(..))";
    private static final String POINTCUT_GATE_AUTHOR_GET="execution(* com.wujie.springbootlayui.bus.service.impl.GateAuthorServiceImpl.getById(..))";
    private static final String POINTCUT_GATE_AUTHOR_DELETE="execution(* com.wujie.springbootlayui.bus.service.impl.GateAuthorServiceImpl.removeById(..))";

    private static final String CACHE_GATE_AUTHOR_PROFIX="gateAuthor:";

    /**
     * 添加闸机授权切入
     * @param joinPoint
     * @return
     */
    @Around(value = POINTCUT_GATE_AUTHOR_ADD)
    public Object cacheGateAuthorAdd(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
        GateAuthor object = (GateAuthor) joinPoint.getArgs()[0];
        Boolean res = (Boolean) joinPoint.proceed();
        if (res){
            CACHE_CONTAINER.put(CACHE_GATE_AUTHOR_PROFIX + object.getId(),object);
        }
        return res;
    }


    /**
     * 查询闸机授权切入
     * @param joinPoint
     * @return
     */
    @Around(value = POINTCUT_GATE_AUTHOR_GET)
    public Object cacheGateAuthorGet(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
        Integer object = (Integer) joinPoint.getArgs()[0];
        //从缓存里面取
        Object res1 = CACHE_CONTAINER.get(CACHE_GATE_AUTHOR_PROFIX + object);
        if (res1!=null){
            log.info("已从缓存里面找到闸机授权对象"+CACHE_GATE_AUTHOR_PROFIX + object);
            return res1;
        }else {
            log.info("未从缓存里面找到闸机授权对象，从数据库中查询并放入缓存");
            GateAuthor res2 =(GateAuthor) joinPoint.proceed();
            CACHE_CONTAINER.put(CACHE_GATE_AUTHOR_PROFIX+res2.getId(),res2);
            return res2;
        }
    }


    /**
     * 更新闸机授权切入
     * @param joinPoint
     * @return
     */
    @Around(value = POINTCUT_GATE_AUTHOR_UPDATE)
    public Object cacheGateAuthorUpdate(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
        GateAuthor authorVo = (GateAuthor) joinPoint.getArgs()[0];
        Boolean isSuccess = (Boolean) joinPoint.proceed();
        if (isSuccess){
            GateAuthor author =(GateAuthor) CACHE_CONTAINER.get(CACHE_GATE_AUTHOR_PROFIX + authorVo.getId());
            if (null == author){
                author = new GateAuthor();
            }
            BeanUtils.copyProperties(authorVo,author);
            log.info("闸机授权对象缓存已更新"+CACHE_GATE_AUTHOR_PROFIX + authorVo.getId());
            CACHE_CONTAINER.put(CACHE_GATE_AUTHOR_PROFIX+author.getId(),author);
        }
        return isSuccess;
    }

    /**
     * 删除闸机授权切入
     * @param joinPoint
     * @return
     */
    @Around(value = POINTCUT_GATE_AUTHOR_DELETE)
    public Object cacheGateAuthorDelete(ProceedingJoinPoint joinPoint) throws Throwable {

        //取出第一个参数
        Integer id = (Integer) joinPoint.getArgs()[0];
        Boolean isSuccess = (Boolean) joinPoint.proceed();
        if (isSuccess){
            //删除缓存
            CACHE_CONTAINER.remove(CACHE_GATE_AUTHOR_PROFIX+id);
        }
        return isSuccess;
    }


    /**
     * 声明闸机的切面表达式
     */
    private static final String POINTCUT_GATE_ADD="execution(* com.wujie.springbootlayui.bus.service.impl.GateServiceImpl.save(..))";
    private static final String POINTCUT_GATE_UPDATE="execution(* com.wujie.springbootlayui.bus.service.impl.GateServiceImpl.updateById(..))";
    private static final String POINTCUT_GATE_GET="execution(* com.wujie.springbootlayui.bus.service.impl.GateServiceImpl.getById(..))";
    private static final String POINTCUT_GATE_DELETE="execution(* com.wujie.springbootlayui.bus.service.impl.GateServiceImpl.removeById(..))";
    private static final String POINTCUT_GATE_BATCHDELETE="execution(* com.wujie.springbootlayui.bus.service.impl.GateServiceImpl.removeByIds(..))";

    private static final String CACHE_GATE_PROFIX="gate:";


    /**
     * 添加闸机切入
     * @param joinPoint
     * @return
     */
    @Around(value = POINTCUT_GATE_ADD)
    public Object cacheGateAdd(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
        Gate object = (Gate) joinPoint.getArgs()[0];
        Boolean res = (Boolean) joinPoint.proceed();
        if (res){
            CACHE_CONTAINER.put(CACHE_GATE_PROFIX + object.getId(),object);
        }
        return res;
    }


    /**
     * 查询闸机切入
     * @param joinPoint
     * @return
     */
    @Around(value = POINTCUT_GATE_GET)
    public Object cacheGateGet(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
        Integer object = (Integer) joinPoint.getArgs()[0];
        //从缓存里面取
        Object res1 = CACHE_CONTAINER.get(CACHE_GATE_PROFIX + object);
        if (res1!=null){
            log.info("已从缓存里面找到闸机对象"+CACHE_GATE_PROFIX + object);
            return res1;
        }else {
            log.info("未从缓存里面找到闸机对象，从数据库中查询并放入缓存");
            Gate res2 =(Gate) joinPoint.proceed();
            CACHE_CONTAINER.put(CACHE_GATE_PROFIX + res2.getId(),res2);
            return res2;
        }
    }


    /**
     * 更新闸机切入
     * @param joinPoint
     * @return
     */
    @Around(value = POINTCUT_GATE_UPDATE)
    public Object cacheGateUpdate(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
        Gate gateVo = (Gate) joinPoint.getArgs()[0];
        Boolean isSuccess = (Boolean) joinPoint.proceed();
        if (isSuccess){
            Gate gate =(Gate) CACHE_CONTAINER.get(CACHE_GATE_PROFIX + gateVo.getId());
            if (null == gate){
                gate = new Gate();
            }
            BeanUtils.copyProperties(gateVo,gate);
            log.info("闸机对象缓存已更新"+CACHE_GATE_PROFIX + gateVo.getId());
            CACHE_CONTAINER.put(CACHE_GATE_PROFIX+gate.getId(),gate);
        }
        return isSuccess;
    }

    /**
     * 删除闸机切入
     * @param joinPoint
     * @return
     */
    @Around(value = POINTCUT_GATE_DELETE)
    public Object cacheGateDelete(ProceedingJoinPoint joinPoint) throws Throwable {

        //取出第一个参数
        Integer id = (Integer) joinPoint.getArgs()[0];
        Boolean isSuccess = (Boolean) joinPoint.proceed();
        if (isSuccess){
            //删除缓存
            CACHE_CONTAINER.remove(CACHE_GATE_PROFIX+id);
        }
        return isSuccess;
    }

    /**
     * 批量删除闸机切入
     *
     * @throws Throwable
     */
    @Around(value = POINTCUT_GATE_BATCHDELETE)
    public Object cacheGateBatchDelete(ProceedingJoinPoint joinPoint) throws Throwable {
        // 取出第一个参数
        @SuppressWarnings("unchecked")
        Collection<Serializable> idList = (Collection<Serializable>) joinPoint.getArgs()[0];
        Boolean isSuccess = (Boolean) joinPoint.proceed();
        if (isSuccess) {
            for (Serializable id : idList) {
                // 删除缓存
                CACHE_CONTAINER.remove(CACHE_GATE_PROFIX + id);
                log.info("设备对象缓存已删除" + CACHE_GATE_PROFIX + id);
            }
        }
        return isSuccess;
    }

    /**
     * 声明发卡管理的切面表达式
     */
    private static final String POINTCUT_STAFF_CARD_ADD="execution(* com.wujie.springbootlayui.bus.service.impl.StaffCardServiceImpl.save(..))";
    private static final String POINTCUT_STAFF_CARD_UPDATE="execution(* com.wujie.springbootlayui.bus.service.impl.StaffCardServiceImpl.updateById(..))";
    private static final String POINTCUT_STAFF_CARD_GET="execution(* com.wujie.springbootlayui.bus.service.impl.StaffCardServiceImpl.getById(..))";
    private static final String POINTCUT_STAFF_CARD_DELETE="execution(* com.wujie.springbootlayui.bus.service.impl.StaffCardServiceImpl.removeById(..))";

    private static final String CACHE_STAFF_CARD_PROFIX="staffCard:";

    /**
     * 添加发卡切入
     * @param joinPoint
     * @return
     */
    @Around(value = POINTCUT_STAFF_CARD_ADD)
    public Object cacheStaffCardAdd(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
        StaffCard object = (StaffCard) joinPoint.getArgs()[0];
        Boolean res = (Boolean) joinPoint.proceed();
        if (res){
            CACHE_CONTAINER.put(CACHE_STAFF_CARD_PROFIX + object.getId(),object);
        }
        return res;
    }

    /**
     * 查询卡片切入
     * @param joinPoint
     * @return
     */
    @Around(value = POINTCUT_STAFF_CARD_GET)
    public Object cacheStaffCardGet(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
        Integer object = (Integer) joinPoint.getArgs()[0];
        //从缓存里面取
        Object res1 = CACHE_CONTAINER.get(CACHE_STAFF_CARD_PROFIX + object);
        if (res1!=null){
            log.info("已从缓存里面找到卡片对象"+CACHE_STAFF_CARD_PROFIX + object);
            return res1;
        }else {
            log.info("未从缓存里面找到卡片对象，从数据库中查询并放入缓存");
            StaffCard res2 =(StaffCard) joinPoint.proceed();
            CACHE_CONTAINER.put(CACHE_STAFF_CARD_PROFIX+res2.getId(),res2);
            return res2;
        }
    }

    /**
     * 更新员工切入
     * @param joinPoint
     * @return
     */
    @Around(value = POINTCUT_STAFF_CARD_UPDATE)
    public Object cacheStaffCardUpdate(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
        StaffCard staffCardVo = (StaffCard) joinPoint.getArgs()[0];
        Boolean isSuccess = (Boolean) joinPoint.proceed();
        if (isSuccess){
            StaffCard staffCard =(StaffCard) CACHE_CONTAINER.get(CACHE_STAFF_CARD_PROFIX + staffCardVo.getId());
            if (null==staffCard){
                staffCard = new StaffCard();
            }
            BeanUtils.copyProperties(staffCardVo,staffCard);
            log.info("卡片对象缓存已更新"+CACHE_STAFF_CARD_PROFIX + staffCardVo.getId());
            CACHE_CONTAINER.put(CACHE_STAFF_CARD_PROFIX + staffCard.getId(),staffCard);
        }
        return isSuccess;
    }

    /**
     * 删除卡片切入
     * @param joinPoint
     * @return
     */
    @Around(value = POINTCUT_STAFF_CARD_DELETE)
    public Object cacheStaffCardDelete(ProceedingJoinPoint joinPoint) throws Throwable {

        //取出第一个参数
        Integer id = (Integer) joinPoint.getArgs()[0];
        Boolean isSuccess = (Boolean) joinPoint.proceed();
        if (isSuccess){
            //删除缓存
            CACHE_CONTAINER.remove(CACHE_STAFF_CARD_PROFIX + id);
        }
        return isSuccess;
    }


    /**
     * 声明员工的切面表达式
     */
    private static final String POINTCUT_STAFF_ADD="execution(* com.wujie.springbootlayui.bus.service.impl.StaffServiceImpl.save(..))";
    private static final String POINTCUT_STAFF_UPDATE="execution(* com.wujie.springbootlayui.bus.service.impl.StaffServiceImpl.updateById(..))";
    private static final String POINTCUT_STAFF_GET="execution(* com.wujie.springbootlayui.bus.service.impl.StaffServiceImpl.getById(..))";
    private static final String POINTCUT_STAFF_DELETE="execution(* com.wujie.springbootlayui.bus.service.impl.StaffServiceImpl.removeById(..))";
    private static final String POINTCUT_STAFF_BATCHDELETE="execution(* com.wujie.springbootlayui.bus.service.impl.StaffServiceImpl.removeByIds(..))";

    private static final String CACHE_STAFF_PROFIX="staff:";

    /**
     * 添加员工切入
     * @param joinPoint
     * @return
     */
    @Around(value = POINTCUT_STAFF_ADD)
    public Object cacheStaffAdd(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
        Staff object = (Staff) joinPoint.getArgs()[0];
        Boolean res = (Boolean) joinPoint.proceed();
        if (res){
            CACHE_CONTAINER.put(CACHE_STAFF_PROFIX + object.getId(),object);
        }
        return res;
    }

    /**
     * 查询员工切入
     * @param joinPoint
     * @return
     */
    @Around(value = POINTCUT_STAFF_GET)
    public Object cacheStaffGet(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
        Integer object = (Integer) joinPoint.getArgs()[0];
        //从缓存里面取
        Object res1 = CACHE_CONTAINER.get(CACHE_STAFF_PROFIX + object);
        if (res1!=null){
            log.info("已从缓存里面找到员工对象"+ CACHE_STAFF_PROFIX + object);
            return res1;
        }else {
            log.info("未从缓存里面找到员工对象，从数据库中查询并放入缓存");
            Staff res2 =(Staff) joinPoint.proceed();
            CACHE_CONTAINER.put(CACHE_STAFF_PROFIX + res2.getId(),res2);
            return res2;
        }
    }

    /**
     * 更新员工切入
     * @param joinPoint
     * @return
     */
    @Around(value = POINTCUT_STAFF_UPDATE)
    public Object cacheStaffUpdate(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
        Staff staffVo = (Staff) joinPoint.getArgs()[0];
        Boolean isSuccess = (Boolean) joinPoint.proceed();
        if (isSuccess){
            Staff staff =(Staff) CACHE_CONTAINER.get(CACHE_STAFF_PROFIX + staffVo.getId());
            if (null==staff){
                staff = new Staff();
            }
            BeanUtils.copyProperties(staffVo,staff);
            log.info("员工对象缓存已更新"+CACHE_STAFF_PROFIX + staffVo.getId());
            CACHE_CONTAINER.put(CACHE_STAFF_PROFIX+staff.getId(),staff);
        }
        return isSuccess;
    }

    /**
     * 删除客户切入
     * @param joinPoint
     * @return
     */
    @Around(value = POINTCUT_STAFF_DELETE)
    public Object cacheStaffDelete(ProceedingJoinPoint joinPoint) throws Throwable {

        //取出第一个参数
        Integer id = (Integer) joinPoint.getArgs()[0];
        Boolean isSuccess = (Boolean) joinPoint.proceed();
        if (isSuccess){
            //删除缓存
            CACHE_CONTAINER.remove(CACHE_STAFF_PROFIX+id);
        }
        return isSuccess;
    }

    /**
     * 批量删除客户切入
     *
     * @throws Throwable
     */
    @Around(value = POINTCUT_STAFF_BATCHDELETE)
    public Object cacheStaffBatchDelete(ProceedingJoinPoint joinPoint) throws Throwable {
        // 取出第一个参数
        @SuppressWarnings("unchecked")
        Collection<Serializable> idList = (Collection<Serializable>) joinPoint.getArgs()[0];
        Boolean isSuccess = (Boolean) joinPoint.proceed();
        if (isSuccess) {
            for (Serializable id : idList) {
                // 删除缓存
                CACHE_CONTAINER.remove(CACHE_STAFF_PROFIX + id);
                log.info("员工对象缓存已删除" + CACHE_STAFF_PROFIX + id);
            }
        }
        return isSuccess;
    }
}
