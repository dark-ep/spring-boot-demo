package cn.ep.spring.boot.ch02.s02;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.*;

import java.util.Date;

@Configuration
@EnableScheduling
public class SchedulingTask {

    @Scheduled(cron = "0/10 * * * * *")
    public void task1() {
        System.out.println("MyTask.task1()," + new Date());
    }

    @Scheduled(cron = "0 0/1 * * * *")
    public void task2() {
        System.out.println("MyTask.task2()," + new Date());
    }

}
