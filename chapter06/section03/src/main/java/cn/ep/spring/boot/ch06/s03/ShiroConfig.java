package cn.ep.spring.boot.ch06.s03;

import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.text.IniRealm;
import org.springframework.context.annotation.*;

@Configuration
public class ShiroConfig {

    @Bean
    public Realm realm() {
        return new IniRealm("classpath:user-role.ini");
    }

}
