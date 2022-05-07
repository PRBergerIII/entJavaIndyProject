package com.prberger3.flexregistry.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

// TODO: 5/5/2022 add javadocs

@Entity(name = "WishListItem")
@Table(name = "wish_list_item")
public class WishListItem {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private int id;
    @ManyToOne
    @JoinColumn(name = "wish_list_id")
    private WishList wishList;
    private String name;
    @Column(name = "specific_item")
    private Boolean specificItem;
    private String details;
    private int priority;
    @Column(name = "price_range")
    private String priceRange;
    private boolean purchased;
    @Column(name = "purchased_message")
    private String purchasedMessage;

    public WishListItem() {}

    public WishListItem(WishList wishList, String name, Boolean specificItem,
                        String details, int priority, String priceRange,
                        boolean purchased, String purchasedMessage) {

        this.wishList = wishList;
        this.name = name;
        this.specificItem = specificItem;
        this.details = details;
        this.priority = priority;
        this.priceRange = priceRange;
        this.purchased = purchased;
        this.purchasedMessage = purchasedMessage;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public WishList getWishList() {
        return wishList;
    }

    public void setWishList(WishList wishList) {
        this.wishList = wishList;
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
    public String toString() {
        return "WishListItem{" +
                "id=" + id +
                ", wishList.id=" + wishList.getId() +
                ", name='" + name + '\'' +
                ", specificItem=" + specificItem +
                ", details='" + details + '\'' +
                ", priority=" + priority +
                ", priceRange='" + priceRange + '\'' +
                ", purchased=" + purchased +
                ", purchasedMessage='" + purchasedMessage + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WishListItem that = (WishListItem) o;
        return  id == that.id
                && wishList.equals(that.wishList)
                && priority == that.priority
                && purchased == that.purchased
                && Objects.equals(name, that.name)
                && Objects.equals(specificItem, that.specificItem)
                && Objects.equals(details, that.details)
                && Objects.equals(priceRange, that.priceRange)
                && Objects.equals(purchasedMessage, that.purchasedMessage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, wishList, name, specificItem, details,
                            priority, priceRange);
    }
}
