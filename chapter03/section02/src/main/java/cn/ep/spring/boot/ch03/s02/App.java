package cn.ep.spring.boot.ch03.s02;

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
    public CommandLineRunner demo(JdbcTemplate jdbcTemplate) {
        return (args) -> {
            log.info("Creating tables");

            jdbcTemplate.execute("DROP TABLE customer IF EXISTS");
            jdbcTemplate.execute("CREATE TABLE customer(" + "id SERIAL, first_name VARCHAR(255), last_name VARCHAR(255))");

            // Split up the array of whole names into an array of first/last names
            List<Object[]> splitUpNames = Stream.of("Jack Bauer", "Chloe O'Brian", "Kim Bauer", "David Palmer", "Michelle Dessler")
                    .map(name -> name.split(" "))
                    .collect(Collectors.toList());

            splitUpNames.forEach(name -> log.info(String.format("Inserting customer record for %s %s", name[0], name[1])));

            // Uses JdbcTemplate's batchUpdate operation to bulk load data
            jdbcTemplate.batchUpdate("INSERT INTO customer(first_name, last_name) VALUES (?,?)", splitUpNames);

            // fetch all customers
            log.info("Customers found with all:");
            log.info("-------------------------------");
            jdbcTemplate.query("SELECT id, first_name, last_name FROM customer",
                    (rs, rowNum) -> new Customer(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"))
            ).forEach(customer -> log.info(customer.toString()));
            log.info("");

            // fetch an individual customer by ID
            log.info("Customer found with id = 1L:");
            log.info("--------------------------------");
            Customer customer = jdbcTemplate.queryForObject("SELECT id, first_name, last_name FROM customer WHERE id = ?",
                    (rs, rowNum) -> new Customer(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name")),
                    1L);
            log.info(customer.toString());
            log.info("");

            // fetch customers by last name
            log.info("Customers found with last_name = 'Bauer':");
            log.info("--------------------------------------------");
            jdbcTemplate.query(
                    "SELECT id, first_name, last_name FROM customer WHERE last_name = ?", new Object[]{"Bauer"},
                    (rs, rowNum) -> new Customer(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"))
            ).forEach(bauer -> log.info(bauer.toString()));
            log.info("");
        };
    }

}
