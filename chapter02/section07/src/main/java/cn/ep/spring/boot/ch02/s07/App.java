package cn.ep.spring.boot.ch02.s07;

import org.slf4j.*;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class App {

    private Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    public CommandLineRunner demo() {
        return (args) -> {
            log.debug("log4j2 debug");
            log.info("log4j2 info");
            log.warn("log4j2 warn");
            log.error("log4j2 error");
            log.trace("log4j2 trace");
        };
    }
}
