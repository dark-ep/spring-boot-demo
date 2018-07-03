package cn.ep.spring.boot.ch02.s12;

import org.slf4j.*;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.*;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class SpringAsyncConfig implements AsyncConfigurer {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(4);
        executor.afterPropertiesSet();//需要先初始化
        return executor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        logger.info("测试异常处理");
        return (ex, method, params) -> {
            logger.info("Method Name::" + method.getName());
            logger.info("Exception occurred::" + ex);
        };
    }

}
