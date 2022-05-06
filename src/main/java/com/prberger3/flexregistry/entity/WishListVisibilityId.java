package com.prberger3.flexregistry.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

// TODO: 5/5/2022 refactor to match other join table entity

public class WishListVisibilityId implements Serializable {
    @Column(name = "user_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    @Column(name = "wish_list_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
        WishListVisibilityId that = (WishListVisibilityId) o;
        return userId == that.userId && wishListId == that.wishListId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, wishListId);
    }
}
