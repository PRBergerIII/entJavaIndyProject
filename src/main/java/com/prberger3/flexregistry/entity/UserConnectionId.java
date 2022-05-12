package com.prberger3.flexregistry.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.*;

/**
 * Adapted from the Composite ID class in solution #2 at:
 * https://www.codejava.net/frameworks/hibernate/hibernate-many-to-many-
 * association-with-extra-columns-in-join-table-example
 */
@Embeddable
public class UserConnectionId implements Serializable {

    @JoinColumn(name = "follower_id")
    @ManyToOne(cascade = CascadeType.ALL)
    private User follower;
    @JoinColumn(name = "user_followed_id")
    @ManyToOne(cascade = CascadeType.ALL)
    private User userFollowed;

    /**
     * Instantiates a new User connection id.
     */
    public UserConnectionId() {
    }

    /**
     * Instantiates a new User connection id.
     *
     * @param follower     the follower
     * @param userFollowed the user followed
     */
    public UserConnectionId(User follower, User userFollowed) {
        this.follower = follower;
        this.userFollowed = userFollowed;
    }

    /**
     * Gets follower.
     *
     * @return the follower
     */
    public User getFollower() {
        return follower;
    }

    /**
     * Sets follower.
     *
     * @param follower the follower
     */
    public void setFollower(User follower) {
        this.follower = follower;
    }

    /**
     * Gets user followed.
     *
     * @return the user followed
     */
    public User getUserFollowed() {
        return userFollowed;
    }

    /**
     * Sets user followed.
     *
     * @param userFollowed the user followed
     */
    public void setUserFollowed(User userFollowed) {
        this.userFollowed = userFollowed;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserConnectionId that = (UserConnectionId) o;
        return  follower.equals(that.follower)
                && userFollowed.equals(that.userFollowed);

    }

    @Override
    public int hashCode() {
        return Objects.hash(follower, userFollowed);
    }
}