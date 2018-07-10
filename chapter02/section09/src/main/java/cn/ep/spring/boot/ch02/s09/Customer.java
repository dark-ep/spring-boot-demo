package cn.ep.spring.boot.ch02.s09;

import java.io.Serializable;

public class Customer implements Serializable {

    private String id;
    private String firstName;
    private String lastName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Customer withId(String id) {
        this.setId(id);
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName == null ? null : firstName.trim();
    }

    public Customer withFirstName(String firstName) {
        this.setFirstName(firstName);
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName == null ? null : lastName.trim();
    }

    public Customer withLastName(String lastName) {
        this.setLastName(lastName);
        return this;
    }

}
