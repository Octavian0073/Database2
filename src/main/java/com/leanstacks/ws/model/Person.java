package com.leanstacks.ws.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

import static javax.persistence.CascadeType.*;

@Entity
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String personName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="cityId", referencedColumnName = "id", insertable = true, updatable = true)
    private City inCity;

    @Column
    private String personPhone;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="personRole", referencedColumnName = "id", insertable = true, updatable = true)
    private Roles role;



    /**
     * Create a new Person object.
     */
    public Person() {
        super();
    }

    /**
     * Create a new Person object with the supplied values.
     *
     *
     */
    public Person(String personName, City inCity, String personPhone, Roles role) {
        super();
        this.personName = personName;
        this.inCity = inCity;
        this.personPhone = personPhone;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public City getInCity() {
        return inCity;
    }

    public void setInCity(City inCity) {

        this.inCity = inCity;
    }

    public String getPersonPhone() {
        return personPhone;
    }

    public void setPersonPhone(String personPhone) {
        this.personPhone = personPhone;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {

        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id && Objects.equals(personName, person.personName) && Objects.equals(inCity, person.inCity) && Objects.equals(personPhone, person.personPhone) && Objects.equals(role, person.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, personName, inCity, personPhone, role);
    }

    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", fromCity=" + personName +
                ", toCity=" + inCity +
                ", toCity=" + personPhone +
                ", transportType='" + role + '\'' +
                '}';
    }
}
