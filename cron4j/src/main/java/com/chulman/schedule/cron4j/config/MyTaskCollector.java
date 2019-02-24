package com.chulman.schedule.cron4j.config;

import it.sauronsoftware.cron4j.SchedulingPattern;
import it.sauronsoftware.cron4j.Task;
import it.sauronsoftware.cron4j.TaskCollector;
import it.sauronsoftware.cron4j.TaskTable;
import org.springframework.stereotype.Component;

@Component
public class MyTaskCollector implements TaskCollector {

    private TaskTable taskTable;

    public MyTaskCollector(){
        taskTable = new TaskTable();
    }

    @Override
    public TaskTable getTasks() {
        return taskTable;
    }

    public void add(String schedulePattern, Task task){
        SchedulingPattern pattern = new SchedulingPattern(schedulePattern);
        taskTable.add(pattern, task);
    }
}
