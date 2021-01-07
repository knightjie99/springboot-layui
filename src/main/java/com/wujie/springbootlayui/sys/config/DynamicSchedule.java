package com.wujie.springbootlayui.sys.config;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

import com.wujie.springbootlayui.sys.common.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wujie.springbootlayui.bus.entity.Attend;
import com.wujie.springbootlayui.bus.entity.Backup;
import com.wujie.springbootlayui.bus.entity.PassRecord;
import com.wujie.springbootlayui.bus.entity.Staff;
import com.wujie.springbootlayui.bus.mapper.AttendMapper;
import com.wujie.springbootlayui.bus.mapper.BackupMapper;
import com.wujie.springbootlayui.bus.mapper.PassRecordMapper;
import com.wujie.springbootlayui.bus.mapper.StaffMapper;
import com.wujie.springbootlayui.bus.service.IBackupService;
import com.wujie.springbootlayui.sys.common.AppFileUtils;
import com.wujie.springbootlayui.sys.common.SpringUtil;

@Configuration
@EnableScheduling
public class DynamicSchedule implements SchedulingConfigurer {
	
	private final static Logger logger = LoggerFactory.getLogger(DynamicSchedule.class);

	private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private static ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();

	private static Map<Integer, ScheduledFuture<?>> scheduledFutureMap = new LinkedHashMap<Integer, ScheduledFuture<?>>();

	//从数据库里取得所有要执行的定时任务
	private List<Backup> getAllTasks(){
		BackupMapper backupMapper =  SpringUtil.getBean(BackupMapper.class);
        QueryWrapper<Backup> queryWrapper = new QueryWrapper<Backup>();
        //备份方式  1-手动  2-自动
        queryWrapper.eq("backup_way",2);
        //状态  1-开启 2-关闭
        queryWrapper.eq("status",1);
        List<Backup> lists= backupMapper.selectList(queryWrapper);
		return lists;
	}
	
	static {
        threadPoolTaskScheduler.initialize();
    }

	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		//通过校验的数据执行定时任务
        int count = 0;

        List<Backup> tasks = getAllTasks();
        for(Backup task :tasks) {
        	//获取数据库的任务集合 主键ID,运行表达式  , 备份内容
        	if(!StringUtil.isEmpty(task.getCron())){
                start(task.getId(),task.getCron(),task.getBackupContent(),task.getBelongId());
                count++;
            }

        }
        logger.info("定时任务实际启动数量="+count+"; time="+sdf.format(new Date()));
	}


    /**
     * 启动定时任务
     * @param task
     * @param
     */
    public static void start(Integer id,String cron,String backupContent,Integer belongId){

        ScheduledFuture<?> scheduledFuture = threadPoolTaskScheduler.schedule(getRunnable(backupContent,belongId),getTrigger(cron));
        scheduledFutureMap.put(id,scheduledFuture);
        logger.info("启动定时任务" + id);

    }

    /**
     * 取消定时任务
     * @param task
     */
    public static void cancel(Integer id){

        ScheduledFuture<?> scheduledFuture = scheduledFutureMap.get(id);

        if(scheduledFuture != null && !scheduledFuture.isCancelled()){
            scheduledFuture.cancel(Boolean.FALSE);
        }

        scheduledFutureMap.remove(id);
        logger.info("取消定时任务" + id);

    }
	
    private static Trigger getTrigger(String cron){
        return new Trigger() {
            @Override
            public Date nextExecutionTime(TriggerContext triggerContext) {
                //将Cron 0/1 * * * * ? 输入取得下一次执行的时间
                CronTrigger trigger = new CronTrigger( cron);
                Date nextExec = trigger.nextExecutionTime(triggerContext);
                return nextExec;
            }
        };

    }
    
    private static Runnable getRunnable(String backupContent,Integer belongId){
    	
    	StaffMapper staffMapper = SpringUtil.getBean(StaffMapper.class);
    	
    	PassRecordMapper  recordMapper = SpringUtil.getBean(PassRecordMapper.class);
    	
    	AttendMapper attendMapper = SpringUtil.getBean(AttendMapper.class);
    	
    	IBackupService backupService = SpringUtil.getBean(IBackupService.class);
    	
        return new Runnable() {
            @Override
            public void run() {
    			String dirPath = "backup/" + belongId + "/" + new Date().getTime();
    			File fileDoc = new File(AppFileUtils.UPLOAD_PATH + dirPath);

    			if (!fileDoc.exists()) {
    				fileDoc.mkdirs();
    			}

    			if (backupContent.contains("1")) {
    				QueryWrapper<Staff> staffQueryWrapper = new QueryWrapper<>();
    				staffQueryWrapper.eq("belong_id", belongId);
    				List<Staff> staffList = staffMapper.selectList(staffQueryWrapper);
    				if (staffList.size() > 0) {
    					backupService.backupStaff(fileDoc, staffList);

    				}
    			}
    			if (backupContent.contains("2")) {
    				QueryWrapper<PassRecord> recordQueryWrapper = new QueryWrapper<>();
    				recordQueryWrapper.eq("belong_id", belongId);
    				List<PassRecord> records = recordMapper.selectList(recordQueryWrapper);
    				if (records.size() > 0) {
    					backupService.backupPassRecord(fileDoc, records);

    				}
    			}
    			if (backupContent.contains("3")) {
    				QueryWrapper<Attend> attendQueryWrapper = new QueryWrapper<>();
    				attendQueryWrapper.eq("belong_id", belongId);
    				List<Attend> attends = attendMapper.selectList(attendQueryWrapper);
    				if (attends.size() > 0) {
    					backupService.backupAttend(fileDoc, attends);
    				}
    			}
            }
        };
    }

}
