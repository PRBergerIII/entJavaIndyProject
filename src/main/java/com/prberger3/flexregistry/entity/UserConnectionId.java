package com.prberger3.flexregistry.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.*;

// TODO: 5/3/2022  add jacvadocs
/**
 * Adapted from the Composiite ID class in solution #2 at:
 *      https://www.codejava.net/frameworks/hibernate/hibernate-many-to-many-
 *      association-with-extra-columns-in-join-table-example
 */
@Embeddable
public class UserConnectionId implements Serializable {

    @JoinColumn(name = "follower_id", referencedColumnName = "id")
    @ManyToOne(cascade = CascadeType.ALL)
    private User follower;
    @JoinColumn(name = "user_followed_id", referencedColumnName = "id")
    @ManyToOne(cascade = CascadeType.ALL)
    private User userFollowed;

    public UserConnectionId() {
    }

    public UserConnectionId(User follower, User userFollowed) {
        this.follower = follower;
        this.userFollowed = userFollowed;
    }

    public User getFollower() {
        return follower;
    }

    public void setFollower(User follower) {
        this.follower = follower;
    }

    public User getUserFollowed() {
        return userFollowed;
    }

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