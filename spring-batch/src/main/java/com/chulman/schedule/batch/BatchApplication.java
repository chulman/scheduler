package com.chulman.schedule.batch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

//batch 기능 활성화
@EnableBatchProcessing
@SpringBootApplication
public class BatchApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(BatchApplication.class, args);
//        JobRegistry jobRegistry = context.getBean(JobRegistry.class);
//        JobLauncher jobLauncher = context.getBean(JobLauncher.class);
//        JobRepository jobRepository = context.getBean(JobRepository.class);
//
//        System.out.println("JobRegistry: " + jobRegistry);
//        System.out.println("JobLauncher: " + jobLauncher);
//        System.out.println("JobRepository: " + jobRepository);
    }

}




