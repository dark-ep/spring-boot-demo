package cn.ep.spring.boot.ch01.s07;

import org.springframework.boot.Banner;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;

public class CustomApplicationListener implements ApplicationListener<ApplicationStartingEvent> {

    @Override
    public void onApplicationEvent(ApplicationStartingEvent event) {
        //关闭启动Banner
        event.getSpringApplication().setBannerMode(Banner.Mode.OFF);
    }
}
