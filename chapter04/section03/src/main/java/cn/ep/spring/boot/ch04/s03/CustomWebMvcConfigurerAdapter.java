package cn.ep.spring.boot.ch04.s03;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class CustomWebMvcConfigurerAdapter implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CustomHandlerInterceptor()).addPathPatterns("/**");
    }
}
