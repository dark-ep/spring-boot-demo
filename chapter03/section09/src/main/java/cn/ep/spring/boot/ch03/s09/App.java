package cn.ep.spring.boot.ch03.s09;

import org.slf4j.*;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.ldap.query.*;

import javax.naming.ldap.LdapName;
import java.util.Optional;

@SpringBootApplication
public class App {

    private static final Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        SpringApplication.run(App.class, args).close();
    }

    @Bean
    public CommandLineRunner demo(CustomerRepository repository) {
        return (String... args) -> {
            // fetch all customers
            log.info("Customer found with findAll():");
            log.info("-------------------------------");
            for (Customer customer : repository.findAll()) {
                log.info(customer.toString());
            }
            log.info("");

            // fetch an individual customer by ID
            Optional<Customer> customer = repository.findById(new LdapName("uid=1,ou=people,dc=ep,dc=cn"));
            log.info("Customer found with findOne(1L):");
            log.info("--------------------------------");
            if (customer.isPresent()) {
                log.info(customer.get().toString());
            } else {
                log.info("Customer not found by findOne(\"uid=1,ou=people,dc=ep,dc=cn\")");
            }
            log.info("");

            // fetch customers by last name
            log.info("Customer found with findByLastName('Bauer'):");
            log.info("--------------------------------------------");
            LdapQuery query = LdapQueryBuilder.query().where("sn").is("Bauer");
            for (Customer bauer : repository.findAll(query)) {
                log.info(bauer.toString());
            }
            log.info("");
        };
    }

}
