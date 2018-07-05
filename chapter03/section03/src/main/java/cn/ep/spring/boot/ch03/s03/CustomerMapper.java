package cn.ep.spring.boot.ch03.s03;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CustomerMapper {

    @Insert("INSERT INTO customers(first_name, last_name) VALUES (#{firstName},#{lastName})")
    int save(Customer customer);

    @Select("SELECT id, first_name, last_name FROM customers")
    List<Customer> findAll();

    @Select("SELECT id, first_name, last_name FROM customers WHERE id = #{id}")
    Customer findById(Long id);

    @Select("SELECT id, first_name, last_name FROM customers WHERE last_name = #{lastName}")
    List<Customer> findByLastName(String lastName);
}
