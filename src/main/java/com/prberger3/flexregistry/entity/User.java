package com.prberger3.flexregistry.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;

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

    /**
     * Instantiates a new User.
     */
    public User() {}

    /**
     * Instantiates a new User.
     *
     * @param username  the username
     * @param firstName the first name
     * @param lastName  the last name
     * @param email     the email
     */
    public User(String username,
                String firstName,
                String lastName,
                String email) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.addressVisibility = "private";
        this.admin = false;
    }

    /**
     * Instantiates a new User.
     *
     * @param username          the username
     * @param firstName         the first name
     * @param lastName          the last name
     * @param email             the email
     * @param street            the street
     * @param city              the city
     * @param state             the state
     * @param zip               the zip
     * @param addressVisibility the address visibility
     * @param about             the about
     * @param admin             the admin
     */
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

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets username.
     *
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets first name.
     *
     * @param firstName the first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets last name.
     *
     * @param lastName the last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets street.
     *
     * @return the street
     */
    public String getStreet() {
        return street;
    }

    /**
     * Sets street.
     *
     * @param street the street
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Gets city.
     *
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets city.
     *
     * @param city the city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets state.
     *
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * Sets state.
     *
     * @param state the state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Gets zip.
     *
     * @return the zip
     */
    public String getZip() {
        return zip;
    }

    /**
     * Sets zip.
     *
     * @param zip the zip
     */
    public void setZip(String zip) {
        this.zip = zip;
    }

    /**
     * Gets address visibility.
     *
     * @return the address visibility
     */
    public String getAddressVisibility() {
        return addressVisibility;
    }

    /**
     * Sets address visibility.
     *
     * @param addressVisibility the address visibility
     */
    public void setAddressVisibility(String addressVisibility) {
        this.addressVisibility = addressVisibility;
    }

    /**
     * Gets about.
     *
     * @return the about
     */
    public String getAbout() {
        return about;
    }

    /**
     * Sets about.
     *
     * @param about the about
     */
    public void setAbout(String about) {
        this.about = about;
    }

    /**
     * Is admin boolean.
     *
     * @return the boolean
     */
    public boolean isAdmin() {
        return admin;
    }

    /**
     * Sets admin.
     *
     * @param admin the admin
     */
    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    /**
     * Gets followers.
     *
     * @return the followers
     */
    public Set<UserConnection> getFollowers() {
        return followers;
    }

    /**
     * Sets followers.
     *
     * @param followers the followers
     */
    public void setFollowers(Set<UserConnection> followers) {
        this.followers = followers;
    }

    /**
     * Gets users followed.
     *
     * @return the users followed
     */
    public Set<UserConnection> getUsersFollowed() {
        return usersFollowed;
    }

    /**
     * Sets users followed.
     *
     * @param usersFollowed the users followed
     */
    public void setUsersFollowed(Set<UserConnection> usersFollowed) {
        this.usersFollowed = usersFollowed;
    }

    /**
     * Gets wish lists.
     *
     * @return the wish lists
     */
    public Set<WishList> getWishLists() {
        return wishLists;
    }

    /**
     * Sets wish lists.
     *
     * @param wishLists the wish lists
     */
    public void setWishLists(Set<WishList> wishLists) {
        this.wishLists = wishLists;
    }

    /**
     * Follow user.
     *
     * @param userFollowed the user followed
     */
    public void followUser(User userFollowed) {

        UserConnection userConnection = new UserConnection(this, userFollowed);

        usersFollowed.add(userConnection);
        userFollowed.getFollowers().add(userConnection);

    }

    /**
     * Accept follow request.
     *
     * @param follower the follower
     */
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

    // future: add get accepted followers list method

    /**
     * Add wish list.
     *
     * @param newWishList the new wish list
     */
    public void addWishList(WishList newWishList) {

        newWishList.setOwner(this);
        wishLists.add(newWishList);

    }

    /**
     * Remove wish list.
     *
     * @param removedWishList the removed wish list
     */
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
