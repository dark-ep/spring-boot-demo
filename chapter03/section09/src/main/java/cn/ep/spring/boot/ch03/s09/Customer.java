package cn.ep.spring.boot.ch03.s09;

import org.springframework.ldap.odm.annotations.*;

import javax.naming.Name;
import java.io.Serializable;

@Entry(base = "ou=people,dc=ep,dc=cn", objectClasses = "inetOrgPerson")
public class Customer implements Serializable {

    @Id
    private Name id;
    @Attribute(name = "cn")
    private String firstName;
    @Attribute(name = "sn")
    private String lastName;

    public Name getId() {
        return id;
    }

    public void setId(Name id) {
        this.id = id;
    }

    public Customer withId(Name id) {
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", firstName=").append(firstName);
        sb.append(", lastName=").append(lastName);
        sb.append("]");
        return sb.toString();
    }
}
