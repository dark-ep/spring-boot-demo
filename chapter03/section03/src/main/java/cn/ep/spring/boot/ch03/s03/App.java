package cn.ep.spring.boot.ch03.s03;

import org.slf4j.*;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.stream.*;

@SpringBootApplication
public class App {

    private static final Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        SpringApplication.run(App.class);
    }

    @Bean
    public CommandLineRunner demo(JdbcTemplate jdbcTemplate, CustomerMapper customerMapper) {
        return (args) -> {
            log.info("Creating tables");

            jdbcTemplate.execute("DROP TABLE customers IF EXISTS");
            jdbcTemplate.execute("CREATE TABLE customers(" + "id SERIAL, first_name VARCHAR(255), last_name VARCHAR(255))");

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
            log.info("Customer found with findByLastName('Bauer'):");
            log.info("--------------------------------------------");
            for (Customer bauer : customerMapper.findByLastName("Bauer")) {
                log.info(bauer.toString());
            }
            log.info("");
        };
    }

}
