package cn.ep.spring.boot.ch06.s03.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShiroTest {

    @Test
    public void testAuth() {
        Subject currentUser = SecurityUtils.getSubject();
        Throwable loginThrowable = null;
        if (!currentUser.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken("lonestarr", "vespa");
            try {
                currentUser.login(token);
            } catch (Throwable throwable) {
                loginThrowable = throwable;
            }
        }
        assertThat(loginThrowable).isNull();
        assertThat(currentUser.isAuthenticated()).isTrue();
        assertThat(currentUser.hasRole("schwartz")).isTrue();
        assertThat(currentUser.isPermitted("lightsaber:weild")).isTrue();
        assertThat(currentUser.isPermitted("winnebago:drive:eagle5")).isTrue();
        currentUser.logout();
        assertThat(currentUser.isAuthenticated()).isFalse();
    }

}
