package cn.ep.spring.boot.ch03.s05;

import cn.ep.spring.boot.ch03.s05.dto.Customer;
import cn.ep.spring.boot.ch03.s05.service.CustomerService;
import org.slf4j.*;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class App {

    private static final Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        SpringApplication.run(App.class);
    }

    @Bean
    public CommandLineRunner demo(CustomerService service) {
        return (args) -> {
            service.clearAll();

            // save a couple of customers
            service.save(new Customer().withId(1L).withFirstName("Jack").withLastName("Bauer"));
            service.save(new Customer().withId(2L).withFirstName("Chloe").withLastName("O'Brian"));
            service.save(new Customer().withId(3L).withFirstName("Kim").withLastName("Bauer"));
            service.save(new Customer().withId(4L).withFirstName("David").withLastName("Palmer"));
            service.save(new Customer().withId(5L).withFirstName("Michelle").withLastName("Dessler"));

            // fetch all customers
            log.info("Customers found with findAll():");
            log.info("-------------------------------");
            for (Customer customer : service.findAll()) {
                log.info(String.valueOf(customer));
            }
            log.info("");

            // fetch an individual customer by ID
            Customer customer = service.findOne(1L);
            log.info("Customer found with findOne(1L):");
            log.info("--------------------------------");
            log.info(String.valueOf(customer));
            log.info("");

            // fetch customers by last name
            log.info("Customer found with findByLastName('Bauer'):");
            log.info("--------------------------------------------");
            for (Customer bauer : service.findByLastName("Bauer")) {
                log.info(String.valueOf(bauer));
            }
            log.info("");
        };
    }

}
