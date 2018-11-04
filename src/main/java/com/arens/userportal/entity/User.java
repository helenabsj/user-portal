package com.arens.userportal.entity;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;


@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String firstName;

    @Column(nullable= false)
    private String lastName;

    private String name;

    private String password;

    private String email;

    private String phoneNumber;

    private String hobbie;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="address_id")
    private Address address;

    @ManyToMany
    @JoinTable
    private Set<Role> roles;

    public User() {
    }

    public User(String firstName, String lastName, String email, String phoneNumber, String hobbie) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber =phoneNumber;
        this.hobbie = hobbie;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getHobbie() {
        return hobbie;
    }

    public void setHobbie(String hobbie) {
        this.hobbie = hobbie;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
