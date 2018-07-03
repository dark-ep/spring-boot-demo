package cn.ep.spring.boot.ch02.s13;

import lombok.*;

// 包含@Getter @Setter @RequiredArgsConstructor @ToString @EqualsAndHashCode
@Data
// 只比较id是否一致
@EqualsAndHashCode(of = "id")
public class Customer {

    public String id;
    public String firstName;
    public String lastName;

}
