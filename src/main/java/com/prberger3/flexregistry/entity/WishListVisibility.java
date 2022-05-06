package com.prberger3.flexregistry.entity;

import javax.persistence.*;
import java.util.Objects;

// TODO: 5/5/2022 refactor to match other join table entity

@Entity
@Table(name = "wish_list_visibility", schema = "flexregistry", catalog = "")
@IdClass(WishListVisibilityId.class)
public class WishListVisibility {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "user_id")
    private int userId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "wish_list_id")
    private int wishListId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getWishListId() {
        return wishListId;
    }

    public void setWishListId(int wishListId) {
        this.wishListId = wishListId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WishListVisibility that = (WishListVisibility) o;
        return userId == that.userId && wishListId == that.wishListId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, wishListId);
    }
}
