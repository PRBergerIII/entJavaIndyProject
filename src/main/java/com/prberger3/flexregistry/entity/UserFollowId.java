package com.prberger3.flexregistry.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

// TODO: 5/3/2022  add jacvadocs
/**
 * Adapted from the Composiite ID class in solution #2 at:
 *      https://www.codejava.net/frameworks/hibernate/hibernate-many-to-many-
 *      association-with-extra-columns-in-join-table-example
 */
@Embeddable
public class UserFollowId implements Serializable {

    @Column(name = "follower_id")
    @ManyToOne(cascade = CascadeType.ALL)
    private User follower;
    @Column(name = "user_followed_id")
    @ManyToOne(cascade = CascadeType.ALL)
    private User userFollowed;
    private boolean accepted;

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

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

}