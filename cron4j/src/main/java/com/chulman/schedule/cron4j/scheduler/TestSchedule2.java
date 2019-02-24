package com.chulman.schedule.cron4j.scheduler;

import it.sauronsoftware.cron4j.Task;
import it.sauronsoftware.cron4j.TaskExecutionContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestSchedule2 extends Task {

    @Override
    public void execute(TaskExecutionContext taskExecutionContext) throws RuntimeException {
        log.info("execute start check {} : {}",this.getClass().getSimpleName(),taskExecutionContext.getScheduler().isStarted());
    }
}
