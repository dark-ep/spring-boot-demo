package cn.ep.spring.boot.ch02.s06;

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
            log.debug("logback debug");
            log.info("logback info");
            log.warn("logback warn");
            log.error("logback error");
            log.trace("logback trace");
        };
    }
}
