package cn.ep.spring.boot.ch05.s08.test;

import cn.ep.spring.boot.ch05.s08.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceTest {

    @MockBean
    private CustomerBuilder customerBuilder;
    @Autowired
    private CustomerService customerService;

    @Test
    public void testGet() {
        given(this.customerBuilder.create()).willReturn(new Customer("cc", "dd"));
        Customer custom = customerService.getCustom();
        assertThat(custom).isNotNull();
        assertThat(custom.getFirstName()).isEqualTo("cc");
        assertThat(custom.getLastName()).isEqualTo("dd");
    }

}
