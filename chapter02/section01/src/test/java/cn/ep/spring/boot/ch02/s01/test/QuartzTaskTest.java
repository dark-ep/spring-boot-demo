package cn.ep.spring.boot.ch02.s01.test;

import cn.ep.spring.boot.ch02.s01.SampleJob;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.quartz.CronScheduleBuilder.cronSchedule;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuartzTaskTest {

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    @Test
    public void scheduleJobs() throws Exception {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();

        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("name", "world");
        JobDetail jobDetail = JobBuilder.newJob(SampleJob.class)
                .setJobData(jobDataMap)
                .withDescription("demo")
                .withIdentity("demo-job", "demo-group")
                .build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withSchedule(cronSchedule("0/5 * * * * ?"))
                .build();
        if (!scheduler.checkExists(JobKey.jobKey("demo-job", "demo-group"))) {
            scheduler.scheduleJob(jobDetail, trigger);
        }
        scheduler.start();
        Thread.sleep(15 * 1000);
    }
}
