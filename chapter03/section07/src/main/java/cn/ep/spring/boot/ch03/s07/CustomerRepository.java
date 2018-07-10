package cn.ep.spring.boot.ch03.s07;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CustomerRepository extends MongoRepository<Customer, String> {

    List<Customer> findByLastName(String lastName);

}
