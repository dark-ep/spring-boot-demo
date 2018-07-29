package cn.ep.spring.boot.ch04.s15;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.RequestContextFilter;

@Configuration
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(RequestContextFilter.class);
        //配置restful package.
        packages(this.getClass().getPackage().getName());
    }

}
