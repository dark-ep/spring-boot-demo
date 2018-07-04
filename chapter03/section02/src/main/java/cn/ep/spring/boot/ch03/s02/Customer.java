package cn.ep.spring.boot.ch03.s02;

public class Customer {

    private Long id;
    private String firstName;
    private String lastName;

    private Customer() {}

    Customer(long id, String firstName, String lastName) {
        this();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%d, firstName='%s', lastName='%s']",
                id, firstName, lastName);
    }

}
