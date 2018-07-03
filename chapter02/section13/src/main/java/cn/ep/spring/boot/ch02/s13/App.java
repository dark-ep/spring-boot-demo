package cn.ep.spring.boot.ch02.s13;

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
            Customer customer1 = new Customer();
            customer1.setId("1");
            customer1.setFirstName("a");
            customer1.setLastName("c");
            log.info("customer1[{}]", customer1);
            Customer customer2 = new Customer();
            customer2.setId("1");
            customer2.setFirstName("aa");
            customer2.setLastName("c");
            log.info("customer1[{}] == customer2[{}] is [{}]", customer1, customer2, customer1.equals(customer2));
        };
    }
}
