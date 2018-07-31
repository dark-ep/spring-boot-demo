package cn.ep.spring.boot.ch05.s03.test;

import cn.ep.spring.boot.ch05.s03.CustomController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomController.class)
public class CustomControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testHtmlBody() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().is(200)) // 请求的状态码是否符合预期
                .andExpect(content().string("Hello World!"));// 返回的内容是否符合预期
    }

}
