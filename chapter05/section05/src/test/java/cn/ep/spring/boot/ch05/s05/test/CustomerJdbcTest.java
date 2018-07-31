package cn.ep.spring.boot.ch05.s05.test;

import cn.ep.spring.boot.ch05.s05.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.*;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@JdbcTest
public class CustomerJdbcTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testJdbc() {
        jdbcTemplate.execute("DROP TABLE customer IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE customer(" + "id SERIAL, first_name VARCHAR(255), last_name VARCHAR(255))");

        // Split up the array of whole names into an array of first/last names
        List<Object[]> splitUpNames = Stream.of("Jack Bauer", "Chloe O'Brian", "Kim Bauer", "David Palmer", "Michelle Dessler")
                .map(name -> name.split(" "))
                .collect(Collectors.toList());

        // Uses JdbcTemplate's batchUpdate operation to bulk load data
        int[] batchUpdate = jdbcTemplate.batchUpdate("INSERT INTO customer(first_name, last_name) VALUES (?,?)", splitUpNames);

        assertThat(batchUpdate.length).isEqualTo(5);
        assertThat(batchUpdate[0]).isEqualTo(1);

        // fetch all customers
        List<Customer> customers = jdbcTemplate.query("SELECT id, first_name, last_name FROM customer",
                (rs, rowNum) -> new Customer(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"))
        );

        assertThat(batchUpdate.length).isEqualTo(5);

        // fetch an individual customer by ID
        Customer customer = jdbcTemplate.queryForObject("SELECT id, first_name, last_name FROM customer WHERE id = ?",
                (rs, rowNum) -> new Customer(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name")),
                1L);
        assertThat(customer).isNotNull();
        assertThat(customer.getFirstName()).isEqualTo("Jack");
        assertThat(customer.getLastName()).isEqualTo("Bauer");

        // fetch customers by last name
        jdbcTemplate.query(
                "SELECT id, first_name, last_name FROM customer WHERE last_name = ?", new Object[]{"Bauer"},
                (rs, rowNum) -> new Customer(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"))
        ).forEach(bauer -> assertThat(bauer.getLastName()).isEqualTo("Bauer"));
    }

}
