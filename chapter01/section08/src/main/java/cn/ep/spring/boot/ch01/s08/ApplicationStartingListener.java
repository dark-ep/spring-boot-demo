package cn.ep.spring.boot.ch01.s08;

import org.springframework.boot.Banner;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;

/**
 * spring boot启动开始时执行的事件
 */
public class ApplicationStartingListener implements ApplicationListener<ApplicationStartingEvent> {

    @Override
    public void onApplicationEvent(ApplicationStartingEvent event) {
        //关闭启动Banner
        event.getSpringApplication().setBannerMode(Banner.Mode.OFF);
        System.out.println("Application Starting!");
    }

}
