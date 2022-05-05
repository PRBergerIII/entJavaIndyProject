package com.prberger3.flexregistry.entity;

import java.io.Serializable;

import javax.persistence.*;

// TODO: 5/3/2022  add jacvadocs
/**
 * Adapted from the Composiite ID class in solution #2 at:
 *      https://www.codejava.net/frameworks/hibernate/hibernate-many-to-many-
 *      association-with-extra-columns-in-join-table-example
 */
@Embeddable
public class UserConnectionId implements Serializable {

    @JoinColumn(name = "follower_id")
    @ManyToOne(cascade = CascadeType.ALL)
    private User follower;
    @JoinColumn(name = "user_followed_id")
    @ManyToOne(cascade = CascadeType.ALL)
    private User userFollowed;

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

}