package com.prberger3.flexregistry.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "wish_list_item", schema = "flexregistry", catalog = "")
public class WishListItem {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "wish_list_id")
    private int wishListId;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "specific_item")
    private Boolean specificItem;
    @Basic
    @Column(name = "details")
    private String details;
    @Basic
    @Column(name = "priority")
    private int priority;
    @Basic
    @Column(name = "price_range")
    private String priceRange;
    @Basic
    @Column(name = "purchased")
    private boolean purchased;
    @Basic
    @Column(name = "purchased_message")
    private String purchasedMessage;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWishListId() {
        return wishListId;
    }

    public void setWishListId(int wishListId) {
        this.wishListId = wishListId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getSpecificItem() {
        return specificItem;
    }

    public void setSpecificItem(Boolean specificItem) {
        this.specificItem = specificItem;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(String priceRange) {
        this.priceRange = priceRange;
    }

    public boolean isPurchased() {
        return purchased;
    }

    public void setPurchased(boolean purchased) {
        this.purchased = purchased;
    }

    public String getPurchasedMessage() {
        return purchasedMessage;
    }

    public void setPurchasedMessage(String purchasedMessage) {
        this.purchasedMessage = purchasedMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WishListItem that = (WishListItem) o;
        return id == that.id && wishListId == that.wishListId && priority == that.priority && purchased == that.purchased && Objects.equals(name, that.name) && Objects.equals(specificItem, that.specificItem) && Objects.equals(details, that.details) && Objects.equals(priceRange, that.priceRange) && Objects.equals(purchasedMessage, that.purchasedMessage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, wishListId, name, specificItem, details, priority, priceRange, purchased, purchasedMessage);
    }
}
