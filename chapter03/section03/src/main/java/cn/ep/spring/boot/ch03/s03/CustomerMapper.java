package cn.ep.spring.boot.ch03.s03;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CustomerMapper {

    @Insert("INSERT INTO customer(first_name, last_name) VALUES (#{firstName},#{lastName})")
    int save(Customer customer);

    @Select("SELECT id, first_name, last_name FROM customer")
    List<Customer> findAll();

    @Select("SELECT id, first_name, last_name FROM customer WHERE id = #{id}")
    Customer findById(Long id);

    @Select("SELECT id, first_name, last_name FROM customer WHERE last_name = #{lastName}")
    List<Customer> findByLastName(String lastName);
}
