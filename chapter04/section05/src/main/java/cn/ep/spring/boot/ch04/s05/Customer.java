package cn.ep.spring.boot.ch04.s05;

import javax.validation.constraints.NotEmpty;

public class Customer {

    @NotEmpty(message = "firstName不能为空")
    private String firstName;
    @NotEmpty(message = "lastName不能为空")
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
