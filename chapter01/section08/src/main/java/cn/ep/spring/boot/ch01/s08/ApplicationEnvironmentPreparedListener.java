package cn.ep.spring.boot.ch01.s08;

import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.util.StringUtils;

/**
 * spring boot 对应Enviroment已经准备完毕，但此时上下文context还没有创建。
 */
public class ApplicationEnvironmentPreparedListener implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {

    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
        ConfigurableEnvironment environment = event.getEnvironment();
        String[] activeProfiles = environment.getActiveProfiles();
        System.out.println("Application Environment Prepared,profiles is [" + StringUtils.arrayToDelimitedString(activeProfiles, ",") + "]");
    }

}
