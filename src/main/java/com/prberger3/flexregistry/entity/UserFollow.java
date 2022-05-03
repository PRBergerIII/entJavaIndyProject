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



}
