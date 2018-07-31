package cn.ep.spring.boot.ch06.s01;

import org.slf4j.*;
import org.springframework.batch.item.ItemProcessor;

public class CustomerItemProcessor implements ItemProcessor<Customer, Customer> {

    private static final Logger log = LoggerFactory.getLogger(CustomerItemProcessor.class);

    @Override
    public Customer process(final Customer customer) {
        final String firstName = customer.getFirstName().toUpperCase();
        final String lastName = customer.getLastName().toUpperCase();

        final Customer transformedPerson = new Customer(firstName, lastName);

        log.info("Converting (" + customer + ") into (" + transformedPerson + ")");

        return transformedPerson;
    }

}
