package cn.ep.spring.boot.ch05.s02.test;

import cn.ep.spring.boot.ch05.s02.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.*;
import org.springframework.core.ResolvableType;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomController.class)
@AutoConfigureJsonTesters
public class CustomJsonTest {

    @Autowired
    private GsonTester<Customer> jsonTester;

    @Autowired
    private MockMvc mvc;

    @Test
    public void testJson() throws Exception {
        mvc.perform(get("/getCustom").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(result -> {
            String content = result.getResponse().getContentAsString();

            JsonContent<Customer> jsonContent = new JsonContent<>(getClass(), ResolvableType.forClass(Customer.class), content);
            assertThat(jsonContent).hasJsonPathStringValue("@.firstName");
            assertThat(jsonContent).hasJsonPathStringValue("@.lastName");
            assertThat(jsonContent).extractingJsonPathStringValue("@.firstName").isEqualTo("aa");
            assertThat(jsonContent).extractingJsonPathStringValue("@.lastName").isEqualTo("bb");

            Customer customer = jsonTester.parseObject(content);
            assertThat(customer.getFirstName()).isEqualTo("aa");
            assertThat(customer.getLastName()).isEqualTo("bb");
        });
    }

}
