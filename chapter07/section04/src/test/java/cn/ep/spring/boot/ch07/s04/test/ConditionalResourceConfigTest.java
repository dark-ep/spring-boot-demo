package cn.ep.spring.boot.ch07.s04.test;

import cn.ep.spring.boot.ch07.s04.ConditionalResourceConfig;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConditionalResourceConfigTest {

    @Autowired
    private ApplicationContext applicationContext;

    private ApplicationContextRunner runner;

    @Before
    public void init() {
        runner = new ApplicationContextRunner().withParent(applicationContext);
    }

    @Test
    public void testConditionalClass() {
        runner.run(context -> {
            assertThat(context).hasSingleBean(ConditionalResourceConfig.class);//存在配置
            assertThat(context).hasBean("conditionalOnResourceStr");
        });
    }

}
