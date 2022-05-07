package com.prberger3.flexregistry.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;

// TODO: 4/28/2022 add javadocs

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
    private String username;
    @Column(name = "first_name")
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
    @OneToMany(mappedBy = "primaryKey.userFollowed",
               fetch = FetchType.EAGER,
               cascade = CascadeType.ALL,
               orphanRemoval = true)
    private Set<UserConnection> followers = new HashSet<>();
    @OneToMany(mappedBy = "primaryKey.follower",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<UserConnection> usersFollowed = new HashSet<>();
    @OneToMany(mappedBy = "owner",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<WishList> wishLists = new HashSet<>();

    public User() {}

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getAddressVisibility() {
        return addressVisibility;
    }

    public void setAddressVisibility(String addressVisibility) {
        this.addressVisibility = addressVisibility;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public Set<UserConnection> getFollowers() {
        return followers;
    }

    public void setFollowers(Set<UserConnection> followers) {
        this.followers = followers;
    }

    public Set<UserConnection> getUsersFollowed() {
        return usersFollowed;
    }

    public void setUsersFollowed(Set<UserConnection> usersFollowed) {
        this.usersFollowed = usersFollowed;
    }

    public Set<WishList> getWishLists() {
        return wishLists;
    }

    public void setWishLists(Set<WishList> wishLists) {
        this.wishLists = wishLists;
    }

    public void followUser(User userFollowed) {

        UserConnection userConnection = new UserConnection(this, userFollowed);

        usersFollowed.add(userConnection);
        userFollowed.getFollowers().add(userConnection);

    }

    public void acceptFollowRequest(User follower) {

        for (UserConnection connection : followers) {

            if (connection.getFollower().equals(follower)
                    && !connection.isAccepted()) {

                followers.remove(connection);
                follower.getUsersFollowed().remove(connection);

                connection.setAccepted(true);

                followers.add(connection);
                follower.getUsersFollowed().add(connection);

                break;

            }

        }

    }

    public void addWishList(WishList newWishList) {

        newWishList.setOwner(this);
        wishLists.add(newWishList);

    }

    public void removeWishList(WishList removedWishList) {

        wishLists.remove(removedWishList);
        removedWishList.setOwner(null);

    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip='" + zip + '\'' +
                ", addressVisibility='" + addressVisibility + '\'' +
                ", about='" + about + '\'' +
                ", admin=" + admin +
                ", followers=" + followers +
                ", usersFollowed=" + usersFollowed +
                ", wishLists=" + wishLists +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return  id == user.id && admin == user.admin
                && username.equals(user.username)
                && firstName.equals(user.firstName)
                && lastName.equals(user.lastName)
                && email.equals(user.email)
                && Objects.equals(street, user.street)
                && Objects.equals(city, user.city)
                && Objects.equals(state, user.state)
                && Objects.equals(zip, user.zip)
                && addressVisibility.equals(user.addressVisibility)
                && Objects.equals(about, user.about);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, firstName, lastName, email, street,
                            city, state, zip, addressVisibility, about, admin);
    }

}
