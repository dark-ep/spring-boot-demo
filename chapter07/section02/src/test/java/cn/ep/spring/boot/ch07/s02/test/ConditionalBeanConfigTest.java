package cn.ep.spring.boot.ch07.s02.test;

import cn.ep.spring.boot.ch07.s02.ConditionalBeanConfig;
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
public class ConditionalBeanConfigTest {

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
            assertThat(context).hasSingleBean(ConditionalBeanConfig.class);//存在配置
            assertThat(context).hasBean("conditionalOnBeanStr");
            assertThat(context).hasBean("conditionalOnMissingBeanStr");
        });
    }

}
