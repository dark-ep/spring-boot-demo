package cn.ep.spring.boot.ch06.s02;

import org.slf4j.*;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private final Map<Long, Customer> customerStorage = new HashMap<>();

    private final Logger log = LoggerFactory.getLogger(this.getClass().getName());

    @PostConstruct
    public void init() {
        Customer jack = new Customer(1L, "Jill", "Doe");
        Customer peter = new Customer(2L, "Joe", "Doe");

        customerStorage.put(jack.getId(), jack);
        customerStorage.put(peter.getId(), peter);
    }

    public void insert(Customer customer) {
        customerStorage.put(customer.getId(), customer);
        log.info("Customers after POST:");
        for (Map.Entry<Long, Customer> entry : customerStorage.entrySet()) {
            log.info(entry.getValue().toString());
        }
    }

    public List<Customer> getAll() {
        return customerStorage.entrySet().stream()
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        customerStorage.remove(id);
        log.info("Customers after DELETE:");
        for (Map.Entry<Long, Customer> entry : customerStorage.entrySet()) {
            log.info(entry.getValue().toString());
        }
    }

    public void change(Customer newCust) {
        customerStorage.put(newCust.getId(), newCust);
        log.info("Customers after PUT:");
        for (Map.Entry<Long, Customer> entry : customerStorage.entrySet()) {
            log.info(entry.getValue().toString());
        }
    }
}
