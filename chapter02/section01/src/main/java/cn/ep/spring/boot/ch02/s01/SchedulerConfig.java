package cn.ep.spring.boot.ch02.s01;

import org.quartz.spi.*;
import org.springframework.beans.factory.config.*;
import org.springframework.context.*;
import org.springframework.context.annotation.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.*;

import java.io.IOException;
import java.util.Properties;

@Configuration
public class SchedulerConfig {

    private static final String QUARTZ_PROPERTIES_PATH = "/quartz.properties";

    @Bean
    public JobFactory jobFactory(ApplicationContext applicationContext) {
        AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(JobFactory jobFactory) throws IOException {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setJobFactory(jobFactory);
        factory.setQuartzProperties(quartzProperties());
        return factory;
    }

    @Bean
    public Properties quartzProperties() throws IOException {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource(QUARTZ_PROPERTIES_PATH));
        propertiesFactoryBean.afterPropertiesSet();
        return propertiesFactoryBean.getObject();
    }

    public static class AutowiringSpringBeanJobFactory extends SpringBeanJobFactory implements
            ApplicationContextAware {

        private transient AutowireCapableBeanFactory beanFactory;

        @Override
        public void setApplicationContext(final ApplicationContext context) {
            beanFactory = context.getAutowireCapableBeanFactory();
        }

        @Override
        protected Object createJobInstance(final TriggerFiredBundle bundle) throws Exception {
            final Object job = super.createJobInstance(bundle);
            beanFactory.autowireBean(job);
            return job;
        }
    }

}
