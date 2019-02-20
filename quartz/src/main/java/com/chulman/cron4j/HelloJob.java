package com.chulman.cron4j;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

@Slf4j
public class HelloJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        //JobExcutionContext에서 정보를 가져옴
        log.info("Job ** {} ** fired @ {}", context.getJobDetail().getKey().getName(), context.getFireTime());
        log.info("### Hello ###");
        log.info("Next job scheduled @ {}", context.getNextFireTime());
    }


}
