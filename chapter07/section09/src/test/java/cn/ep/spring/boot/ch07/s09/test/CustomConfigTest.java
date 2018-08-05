package cn.ep.spring.boot.ch07.s09.test;

import cn.ep.spring.boot.ch07.s09.CustomConfig;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomConfigTest {

    private ApplicationContextRunner runner;

    @Before
    public void init() {
        runner = new ApplicationContextRunner().withClassLoader(new FilteredClassLoader(CustomConfig.class)).withConfiguration(AutoConfigurations.of(CustomConfig.class));
    }

    @Test
    public void testConditionalClass() {
        runner.run(context -> {
            assertThat(context).hasFailed();//存在配置
            assertThat(context.getStartupFailure()).hasCauseInstanceOf(ClassNotFoundException.class);
        });

    }

}
