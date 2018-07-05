package cn.ep.spring.boot.ch03.s04;

import org.slf4j.*;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.stream.*;

@SpringBootApplication
public class App {

    private static final Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        SpringApplication.run(App.class);
    }

    @Bean
    public CommandLineRunner demo(CustomerMapper customerMapper) {
        return (args) -> {
            log.info("Customers clear with clearAll():");
            customerMapper.clearAll();

            // Split up the array of whole names into an array of first/last names
            List<Object[]> splitUpNames = Stream.of("Jack Bauer", "Chloe O'Brian", "Kim Bauer", "David Palmer", "Michelle Dessler")
                    .map(name -> name.split(" "))
                    .collect(Collectors.toList());

            splitUpNames.forEach(name -> customerMapper.save(new Customer(String.valueOf(name[0]), String.valueOf(name[1]))));

            // fetch all customers
            log.info("Customers found with findAll():");
            log.info("-------------------------------");
            for (Customer customer : customerMapper.findAll()) {
                log.info(customer.toString());
            }
            log.info("");

            // fetch an individual customer by ID
            Customer customer = customerMapper.findById(1L);
            log.info("Customer found with findOne(1L):");
            log.info("--------------------------------");
            if (customer != null) {
                log.info(customer.toString());
            } else {
                log.info("Customer not found by findOne(1L)");
            }
            log.info("");

            // fetch customers by last name
            log.info("Customers found with findByLastName('Bauer'):");
            log.info("--------------------------------------------");
            for (Customer bauer : customerMapper.findByLastName("Bauer")) {
                log.info(bauer.toString());
            }
            log.info("");
        };
    }

}
