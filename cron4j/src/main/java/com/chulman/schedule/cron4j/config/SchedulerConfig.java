package com.chulman.schedule.cron4j.config;

import com.chulman.schedule.cron4j.scheduler.TestSchedule;
import com.chulman.schedule.cron4j.scheduler.TestSchedule2;
import it.sauronsoftware.cron4j.Scheduler;
import it.sauronsoftware.cron4j.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class SchedulerConfig {

    @Autowired
    private MyTaskCollector taskCollector;

    @Value("${testschedule.pattern}")
    private String pattern1;

    @Value("${testschedule2.pattern}")
    private String pattern2;

    private Scheduler scheduler;

    @Bean
    public Scheduler getScheduler() {
        scheduler = new Scheduler();
        scheduler.addTaskCollector(taskCollector);
        scheduler.start();

        return scheduler;
    }

    @PostConstruct
    public void addScheduler(){
        addSchedule(pattern1, new TestSchedule());
        addSchedule(pattern2, new TestSchedule2());
    }

    private void addSchedule(String schedulePattern, Task task){
        taskCollector.add(schedulePattern,task);
    }
}
