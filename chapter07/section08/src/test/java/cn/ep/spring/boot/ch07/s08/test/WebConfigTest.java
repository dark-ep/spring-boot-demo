package cn.ep.spring.boot.ch07.s08.test;

import cn.ep.spring.boot.ch07.s08.WebConfig;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.runner.WebApplicationContextRunner;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebConfigTest {

    private WebApplicationContextRunner runner;

    @Before
    public void init() {
        runner = new WebApplicationContextRunner().withConfiguration(AutoConfigurations.of(WebConfig.class));
    }

    @Test
    public void testConditionalClass() {
        runner.run(context -> {
            assertThat(context).hasSingleBean(WebConfig.class);//存在配置
        });
    }

}
