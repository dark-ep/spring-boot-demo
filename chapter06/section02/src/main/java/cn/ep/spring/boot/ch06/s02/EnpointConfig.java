package cn.ep.spring.boot.ch06.s02;

import org.springframework.context.annotation.*;

@Configuration
@ImportResource({"classpath:http-inbound-adapter.xml", "classpath:http-inbound-gateway.xml"})
public class EnpointConfig {

}
