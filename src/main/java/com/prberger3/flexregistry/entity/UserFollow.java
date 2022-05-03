package com.prberger3.flexregistry.entity;

import javax.persistence.*;

@Entity(name = "UserFollow")
@Table(name = "user_follow")
@AssociationOverrides({
        @AssociationOverride(name = "primaryKey.follower",
                joinColumns = @JoinColumn(name = "follower_id")),
        @AssociationOverride(name = "primaryKey.userFollowed",
                joinColumns = @JoinColumn(name = "user_followed_id")) })
public class UserFollow {

    // Composite-id key
    private UserFollowId primaryKey = new UserFollowId();
    
    private boolean accepted;

    @EmbeddedId
    public UserFollowId getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(UserFollowId primaryKey) {
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

}
