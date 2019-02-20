package com.chulman.cron4j;

import org.quartz.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

@SpringBootApplication
public class QuartzApplication {

    public static void main(String[] args) {

        Scheduler sched = null;
        SchedulerFactory schedFact = new org.quartz.impl.StdSchedulerFactory();
        try {
            sched = schedFact.getScheduler();
            sched.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

        // HelloJob 클래스로 job 정의
        JobDetail job = newJob(HelloJob.class)
                .withIdentity("myJob", "group1")
                //.usingJobData(jobDataMap) jobDataMap 주입 가능
                .build();

        // Trigger 생성
        Trigger trigger = newTrigger()
                .withIdentity("myTrigger", "group1")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(5)
                        .repeatForever())
                .build();

         //스케쥴러에 Trigger를 사용하는 Job 등록
        try{
            sched.scheduleJob(job, trigger);
        }catch (org.quartz.SchedulerException e){
                e.printStackTrace();
        }

        SpringApplication.run(QuartzApplication.class, args);
    }

}




