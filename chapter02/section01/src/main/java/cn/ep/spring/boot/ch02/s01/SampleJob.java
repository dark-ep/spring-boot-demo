package cn.ep.spring.boot.ch02.s01;

import org.quartz.JobExecutionContext;
import org.slf4j.*;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class SampleJob extends QuartzJobBean {

    private static final Logger log = LoggerFactory.getLogger(SampleJob.class);

    private String name;

    // Invoked if a Job data map entry with that name
    public void setName(String name) {
        this.name = name;
    }

    @Override
    protected void executeInternal(JobExecutionContext context) {
        log.info("Hello {}!", this.name);
    }

}
