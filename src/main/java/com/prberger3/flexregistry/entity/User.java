package com.prberger3.flexregistry.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * This is the user class for the Flex Registry app.
 *
 * @author Paul Berger
 */
@Entity(name = "User")
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private int id;

    @Column(name = "first_name")
    private String username;
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String email;
    private String street;
    private String city;
    private String state;
    private String zip;
    @Column(name = "address_visibility")
    private String addressVisibility;
    private String about;
    private boolean admin;

    public User() {
    }

    public User(String username,
                String firstName,
                String lastName,
                String email) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public User(String username,
                String firstName,
                String lastName,
                String email,
                String street,
                String city,
                String state,
                String zip,
                String addressVisibility,
                String about,
                boolean admin) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.addressVisibility = addressVisibility;
        this.about = about;
        this.admin = admin;
    }



}
