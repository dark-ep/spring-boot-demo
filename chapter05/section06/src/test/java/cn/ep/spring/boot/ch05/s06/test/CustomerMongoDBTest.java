package cn.ep.spring.boot.ch05.s06.test;

import cn.ep.spring.boot.ch05.s06.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataMongoTest
public class CustomerMongoDBTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void testMongoDB() {
        mongoTemplate.dropCollection(Customer.class);

        mongoTemplate.save(new Customer().withId("1").withFirstName("Jack").withLastName("Bauer"));
        mongoTemplate.save(new Customer().withId("2").withFirstName("Chloe").withLastName("O'Brian"));
        mongoTemplate.save(new Customer().withId("3").withFirstName("Kim").withLastName("Bauer"));
        mongoTemplate.save(new Customer().withId("4").withFirstName("David").withLastName("Palmer"));
        mongoTemplate.save(new Customer().withId("5").withFirstName("Michelle").withLastName("Dessler"));

        List<Customer> customers = mongoTemplate.findAll(Customer.class);
        assertThat(customers.size()).isEqualTo(5);

        // fetch an individual customer by ID
        Customer customer = mongoTemplate.findOne(Query.query(Criteria.where("id").is("1")), Customer.class);
        assertThat(customer).isNotNull();
        assertThat(customer.getFirstName()).isEqualTo("Jack");
        assertThat(customer.getLastName()).isEqualTo("Bauer");

        // fetch customers by last name
        List<Customer> bauers = mongoTemplate.find(Query.query(Criteria.where("lastName").is("Bauer")), Customer.class);
        assertThat(bauers.size()).isEqualTo(2);
        bauers.forEach(bauer -> assertThat(bauer.getFirstName()).isIn("Jack", "Kim"));
    }

}
