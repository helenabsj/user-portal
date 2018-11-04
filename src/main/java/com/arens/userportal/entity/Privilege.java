package com.arens.userportal.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Privilege implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="PRIV_ID")
    private long id;

    @Column(unique = true, nullable = false)
    private String name;

    private String description;
}
