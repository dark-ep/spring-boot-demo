package cn.ep.spring.boot.ch03.s05.service;

import cn.ep.spring.boot.ch03.s05.dao.CustomerMapper;
import cn.ep.spring.boot.ch03.s05.dto.*;
import cn.ep.spring.boot.ch03.s05.dynamic.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerMapper mapper;

    @DataSource(DataSourceKey.master)
    public void save(Customer customer) {
        mapper.insert(customer);
    }

    @DataSource(DataSourceKey.master)
    public void clearAll() {
        mapper.deleteByExample(new CustomerCriteria());
    }

    @DataSource(DataSourceKey.slave)
    public List<Customer> findAll() {
        return mapper.selectByExample(new CustomerCriteria());
    }

    public Customer findOne(long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @DataSource(DataSourceKey.master)
    public List<Customer> findByLastName(String lastName) {
        CustomerCriteria criteria = new CustomerCriteria();
        criteria.createCriteria().andLastNameEqualTo(lastName);
        return mapper.selectByExample(criteria);
    }
}
