package com.chulman.schedule.quartz.config;

import com.chulman.schedule.quartz.job.HelloJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.spi.JobFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.annotation.PreDestroy;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

@Configuration
@Slf4j
public class QuartzSchedulerConfiguerer {

    private SchedulerFactory schedulerFactory;
    private Scheduler scheduler;

    @Value("${hellojob.pattern}")
    private String pattern;


    @Bean
    public JobFactory jobFactory(ApplicationContext applicationContext) {
        AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
    }

    @Bean
    public SchedulerFactory getSchedulerFactory() {
        schedulerFactory = new StdSchedulerFactory();
        return schedulerFactory;
    }

    @Bean
    public Scheduler schedulerFactoryBean(DataSource dataSource, JobFactory jobFactory) throws Exception {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        // this allows to update triggers in DB when updating settings in config file
        factory.setOverwriteExistingJobs(true);
        factory.setDataSource(dataSource);
        // use specify jobFactory to create jobDetail
        factory.setJobFactory(jobFactory);

        factory.setQuartzProperties(quartzProperties());
        factory.afterPropertiesSet();

        scheduler = factory.getScheduler();
        scheduler.setJobFactory(jobFactory);

        JobDetail job = newJob(HelloJob.class)
                .withIdentity("myJob", "group1")
                //.usingJobData(jobDataMap) jobDataMap 주입 가능
                .build();

        // Trigger 생성
        Trigger trigger = newTrigger()
                .withIdentity("myTrigger", "group1")
                .startNow()
//              .withSchedule(SimpleScheduleBuilder.simpleSchedule()
//              .withIntervalInSeconds(5)
//              .repeatForever())
                .withSchedule(CronScheduleBuilder.cronSchedule(pattern).withMisfireHandlingInstructionDoNothing())
                .build();

        scheduler.scheduleJob(job, trigger);
        scheduler.start();
        return scheduler;
    }

    @Bean
    public Properties quartzProperties() throws IOException {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
        propertiesFactoryBean.afterPropertiesSet();
        return propertiesFactoryBean.getObject();
    }


    //GraceFul Shutdown
    @PreDestroy
    public void stopJobs() {
        if (scheduler != null) {
            try {
                scheduler.shutdown(false);
            } catch (SchedulerException e) {
                log.error("Error while closing scheduler", e);
            }
        }
    }
}
