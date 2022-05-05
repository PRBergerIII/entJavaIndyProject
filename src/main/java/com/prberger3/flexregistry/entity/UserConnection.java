package com.prberger3.flexregistry.entity;

import javax.persistence.*;
import java.util.Objects;

// TODO: 5/5/2022 javadoc

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

    public UserConnection() {
    }

    public UserConnection(User follower, User userFollowed) {
        this.primaryKey = new UserConnectionId(follower, userFollowed);
        accepted = false;
    }

    public UserConnectionId getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(UserConnectionId primaryKey) {
        this.primaryKey = primaryKey;
    }

    @Transient
    public User getFollower() {
        return getPrimaryKey().getFollower();
    }

    public void setFollower(User user) {
        getPrimaryKey().setFollower(user);
    }

    @Transient
    public User getUserFollowed() {
        return getPrimaryKey().getUserFollowed();
    }

    public void setUserFollowed(User group) {
        getPrimaryKey().setUserFollowed(group);
    }

    public boolean isAccepted() {
        return accepted;
    }

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
