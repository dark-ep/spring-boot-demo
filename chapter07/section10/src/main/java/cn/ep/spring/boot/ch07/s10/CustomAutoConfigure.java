package cn.ep.spring.boot.ch07.s10;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.*;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.*;

@Configuration
@ConditionalOnClass(CustomService.class)
@EnableConfigurationProperties(CustomConfig.class)
public class CustomAutoConfigure {

    @Autowired
    private CustomConfig customConfig;

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "custom.service", name = {"key", "value"})
    CustomService customService() {
        return new CustomService(customConfig.getKey(), customConfig.getValue());
    }

}
