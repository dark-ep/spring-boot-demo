package cn.ep.spring.boot.ch07.s07.test;

import cn.ep.spring.boot.ch07.s07.CustomConfig;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomConfigTest {

    private ApplicationContextRunner runner;

    @Before
    public void init() {
        runner = new ApplicationContextRunner().withPropertyValues("prop.key=key", "prop.value=value").withConfiguration(AutoConfigurations.of(CustomConfig.class));
    }

    @Test
    public void testConditionalClass() {
        runner.run(context -> {
            assertThat(context).hasSingleBean(CustomConfig.class);//存在配置
            assertThat(context.getBean(CustomConfig.class).getKey()).isEqualToIgnoringCase("key");
            assertThat(context.getBean(CustomConfig.class).getValue()).isEqualToIgnoringCase("value");
        });
    }

}
