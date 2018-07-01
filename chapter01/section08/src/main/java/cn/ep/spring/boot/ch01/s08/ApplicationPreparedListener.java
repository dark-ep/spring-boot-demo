package cn.ep.spring.boot.ch01.s08;

import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.*;

/**
 * spring boot上下文context创建完成，但此时spring中的bean是没有完全加载完成的。
 */
public class ApplicationPreparedListener implements ApplicationListener<ApplicationPreparedEvent> {

    @Override
    public void onApplicationEvent(ApplicationPreparedEvent event) {
        ConfigurableApplicationContext context = event.getApplicationContext();
        System.out.println("Application Prepared，Spring context state is [" + context.isActive() + "]");
    }

}
