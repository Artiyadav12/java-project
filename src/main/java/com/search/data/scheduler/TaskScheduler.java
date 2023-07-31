package com.search.data.scheduler;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Configuration
@EnableScheduling
public class TaskScheduler {
    //second, minute, hour, day of the month, month, and day of the week
//    @Scheduled(cron = "*/1 * * ? * *")
//    //@Scheduled(fixedRate = 1000)
//    // Method
//    // To trigger the scheduler every 3 seconds with
//    // an initial delay of 5 seconds.
//    //@Scheduled(fixedDelay = 3000, initialDelay = 5000)
//    public void scheduleTask() {
//        System.out.println("cron");
//    }

   // @Scheduled(cron = "*/5 * * ? * *")
    @Scheduled(fixedRate = 1000)
    public void autoGenerate() {
        System.out.println("cron started");
    }
}
