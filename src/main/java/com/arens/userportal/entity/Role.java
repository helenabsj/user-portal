package com.arens.userportal.entity;


import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ROLE_ID")
    private long id;

    @Column(unique = true, nullable = false)
    private String name;


    public Role() {}



}
