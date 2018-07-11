package cn.ep.spring.boot.ch03.s08;

import org.slf4j.*;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

@SpringBootApplication
public class App {

    private static final Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    public CommandLineRunner demo(CustomerRepository repository) {
        return args -> {
            repository.deleteAll();

            // save a couple of customers
            log.info("Customers save");
            repository.save(new Customer().withId("1").withFirstName("Jack").withLastName("Bauer"));
            repository.save(new Customer().withId("2").withFirstName("Chloe").withLastName("O'Brian"));
            repository.save(new Customer().withId("3").withFirstName("Kim").withLastName("Bauer"));
            repository.save(new Customer().withId("4").withFirstName("David").withLastName("Palmer"));
            repository.save(new Customer().withId("5").withFirstName("Michelle").withLastName("Dessler"));

            // fetch all customers
            log.info("Customers found with findAll():");
            log.info("-------------------------------");
            for (Customer customer : repository.findAll()) {
                log.info(String.valueOf(customer));
            }
            log.info("");

            // fetch an individual customer by ID
            Optional<Customer> customer = repository.findById("1");
            log.info("Customer found with findOne(1L):");
            log.info("--------------------------------");
            if (customer.isPresent()) {
                log.info(customer.get().toString());
            } else {
                log.info("Customer not found by findOne(\"1\")");
            }
            log.info("");

            // fetch customers by last name
            log.info("Customer found with findByLastName('Bauer'):");
            log.info("--------------------------------------------");
            for (Customer bauer : repository.findByLastName("Bauer")) {
                log.info(bauer.toString());
            }
            log.info("");
        };
    }

}
