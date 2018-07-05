package cn.ep.spring.boot.ch03.s05.dynamic;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({
        ElementType.METHOD
})
public @interface DataSource {

    DataSourceKey value();
}
