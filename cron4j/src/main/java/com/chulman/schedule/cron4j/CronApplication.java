package com.chulman.schedule.cron4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//cron4j는 UNIX cron 데몬과 매우 유사한 Java 플랫폼 용 스케줄러
@SpringBootApplication
public class CronApplication {
    public static void main(String[] args) {
        SpringApplication.run(CronApplication.class, args);
    }

}




