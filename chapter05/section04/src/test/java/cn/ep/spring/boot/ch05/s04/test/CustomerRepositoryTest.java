package cn.ep.spring.boot.ch05.s04.test;

import cn.ep.spring.boot.ch05.s04.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CustomerRepository repository;

    @Test
    public void testJPA() {
        entityManager.persist(new Customer("Jack", "Bauer"));
        entityManager.persist(new Customer("Chloe", "O'Brian"));
        entityManager.persist(new Customer("Kim", "Bauer"));
        entityManager.persist(new Customer("David", "Palmer"));
        entityManager.persist(new Customer("Michelle", "Dessler"));

        Optional<Customer> jack = repository.findById(1L);
        assertThat(jack.isPresent()).isTrue();
        assertThat(jack.get().getFirstName()).isEqualTo("Jack");
        assertThat(jack.get().getLastName()).isEqualTo("Bauer");

        List<Customer> customers = repository.findByLastName("Bauer");
        assertThat(customers.size()).isEqualTo(2);

        boolean includeDavid = false;
        for (Customer customer : repository.findAll()) {
            includeDavid = includeDavid || "David".equalsIgnoreCase(customer.getFirstName());
        }
        assertThat(includeDavid).isTrue();
    }

}
