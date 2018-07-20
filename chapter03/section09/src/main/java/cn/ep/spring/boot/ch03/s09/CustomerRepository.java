package cn.ep.spring.boot.ch03.s09;

import org.springframework.data.ldap.repository.LdapRepository;

public interface CustomerRepository extends LdapRepository<Customer> {

}
