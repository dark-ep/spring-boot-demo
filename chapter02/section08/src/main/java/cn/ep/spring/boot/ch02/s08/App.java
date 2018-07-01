package cn.ep.spring.boot.ch02.s08;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableCaching
public class App {

    private static Logger log = LoggerFactory.getLogger(App.class);
    @Autowired
    private CustomerService customerService;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    public CommandLineRunner demo() {
        return (args) -> {
            String id = "1";
            Customer customer = customerService.save(id);
            log.info("save :" + customer);
            Customer customer1 = customerService.findOne(id);
            log.info("findOne :" + customer1);
            Customer customer2 = customerService.findOne(id);
            log.info("findOne :" + customer2);
            customerService.remove("2");
            log.info("remove : 2");
            Customer customer3 = customerService.findOne(id);
            log.info("findOne :" + customer3);
            customerService.remove(id);
            log.info("remove :" + id);
            Customer customer4 = customerService.findOne(id);
            log.info("findOne :" + customer4);
        };
    }

}
