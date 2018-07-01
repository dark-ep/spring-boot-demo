package cn.ep.spring.boot.ch02.s03;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.annotation.*;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

import java.util.Date;

@Configuration
@EnableScheduling
public class TaskCronChange implements SchedulingConfigurer {

    private static String cron;

    public TaskCronChange() {
        //默认情况是：每5秒执行一次.
        cron = "0/5 * * * * *";
        // 开启新线程模拟外部更改了任务执行周期.
        new Thread(() -> {
            try {
                // 让线程睡眠 15秒.
                Thread.sleep(15000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //修改为：每10秒执行一次.
            cron = "0/10 * * * * *";
            System.err.println("cron change to:" + cron);
        }).start();
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        Runnable task = () -> {
            //任务逻辑代码部分.
            System.out.println("TaskCronChange task is running ... " + new Date());
        };
        Trigger trigger = triggerContext -> {
            //任务触发，可修改任务的执行周期.
            CronTrigger cronTrigger = new CronTrigger(cron);
            return cronTrigger.nextExecutionTime(triggerContext);
        };
        taskRegistrar.addTriggerTask(task, trigger);
    }
}
