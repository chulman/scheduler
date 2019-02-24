[![dependencies](https://img.shields.io/badge/springboot-2.1.3-blue.svg)]()
[![dependencies](https://img.shields.io/badge/quartz-2.3.0-blue.svg)](http://www.quartz-scheduler.org/)


# java scheduler

- compareison : https://dzone.com/articles/feature-comparison-java-job
 
|                                               | cron4j       | quartz       | spring-batch |
|-----------------------------------------------|--------------|--------------|--------------|
| 실시간 scheduler config 변경 및 job task 추가 | 기본구성없음 | 기본구성없음 |       X      |
|                  Monitor & UI                 |       X      |       X      |       X      |
|                   Clustering                  |       X      |       O      |       X      |
|               Dynamic Scheduling              |       O      |       O      |       X      |
|            Custom Calendar Support            |       X      |       O      |       X      |
|              Calendar                         |       X      |       O      |       O      |
|              Scheduling Precision             |    Minutes   |    Seconds   | MilliSeconds |


## Cron4j

- cron4j는 UNIX cron 데몬과 유사한 Java 플랫폼 용 스케줄러
- pure java

```java
    public class TestSchedule extends Task {
    
        @Override
        public void execute(TaskExecutionContext taskExecutionContext) throws RuntimeException {
            log.info("execute start check {} : {}",this.getClass().getSimpleName(),taskExecutionContext.getScheduler().isStarted());
        }
    }

```

## Quartz

- Tutorial :  http://www.quartz-scheduler.org/documentation/quartz-2.1.x/tutorials/

### feature
- Scheduler : 스케줄러와 상호 작용하기 위한 주요 API
- Trigger : 주어진 작업이 실행될 일정을 정의하는 구성 요소
- JobDetail : 작업의 인스턴스를 정의하는 데 사용
- Clustering :  Schedule 인스턴스들 간의 Clustering
- History를 보관하지 않음.



## Spring-batch


https://www.baeldung.com/spring-quartz-schedule

##TODO
- SpirngBatch
- Quartz + SpringBatch
