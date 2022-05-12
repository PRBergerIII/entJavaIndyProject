package com.prberger3.flexregistry.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * The type User connection.
 */
@Entity(name = "UserConnection")
@Table(name = "user_connection")
@AssociationOverrides({
        @AssociationOverride(name = "primaryKey.follower",
                joinColumns = @JoinColumn(name = "follower_id")),
        @AssociationOverride(name = "primaryKey.userFollowed",
                joinColumns = @JoinColumn(name = "user_followed_id")) })
public class UserConnection {

    // Composite-id key
    @EmbeddedId
    private UserConnectionId primaryKey = new UserConnectionId();
    private boolean accepted;

    /**
     * Instantiates a new User connection.
     */
    public UserConnection() {
    }

    /**
     * Instantiates a new User connection.
     *
     * @param follower     the follower
     * @param userFollowed the user followed
     */
    public UserConnection(User follower, User userFollowed) {
        this.primaryKey = new UserConnectionId(follower, userFollowed);
        accepted = false;
    }

    /**
     * Gets primary key.
     *
     * @return the primary key
     */
    public UserConnectionId getPrimaryKey() {
        return primaryKey;
    }

    /**
     * Sets primary key.
     *
     * @param primaryKey the primary key
     */
    public void setPrimaryKey(UserConnectionId primaryKey) {
        this.primaryKey = primaryKey;
    }

    /**
     * Gets follower.
     *
     * @return the follower
     */
    @Transient
    public User getFollower() {
        return getPrimaryKey().getFollower();
    }

    /**
     * Sets follower.
     *
     * @param user the user
     */
    public void setFollower(User user) {
        getPrimaryKey().setFollower(user);
    }

    /**
     * Gets user followed.
     *
     * @return the user followed
     */
    @Transient
    public User getUserFollowed() {
        return getPrimaryKey().getUserFollowed();
    }

    /**
     * Sets user followed.
     *
     * @param group the group
     */
    public void setUserFollowed(User group) {
        getPrimaryKey().setUserFollowed(group);
    }

    /**
     * Is accepted boolean.
     *
     * @return the boolean
     */
    public boolean isAccepted() {
        return accepted;
    }

    /**
     * Sets accepted.
     *
     * @param accepted the accepted
     */
    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    @Override
    public String toString() {
        return "UserConnection{" +
                "primaryKey=" + primaryKey +
                ", accepted=" + accepted +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserConnection that = (UserConnection) o;
        return accepted == that.accepted && primaryKey.equals(that.primaryKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(primaryKey, accepted);
    }
}
