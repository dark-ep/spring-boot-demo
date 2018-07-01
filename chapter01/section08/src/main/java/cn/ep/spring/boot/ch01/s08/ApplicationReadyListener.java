package cn.ep.spring.boot.ch01.s08;

import org.slf4j.*;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

/**
 * spring boot所有数据已经准备完毕
 */
public class ApplicationReadyListener implements ApplicationListener<ApplicationReadyEvent> {

    private static Logger log = LoggerFactory.getLogger(ApplicationReadyListener.class);

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        System.out.println("Application Ready![" + event.getTimestamp() + "]");
    }

}
