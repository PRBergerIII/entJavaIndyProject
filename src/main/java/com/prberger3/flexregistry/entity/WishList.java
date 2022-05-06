package com.prberger3.flexregistry.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "wish_list", schema = "flexregistry", catalog = "")
public class WishList {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "owner_id")
    private int ownerId;
    @Basic
    @Column(name = "title")
    private String title;
    @Basic
    @Column(name = "visibility")
    private String visibility;
    @Basic
    @Column(name = "purchased_items_visibility")
    private boolean purchasedItemsVisibility;
    @Basic
    @Column(name = "list_type")
    private String listType;
    @Basic
    @Column(name = "event_date")
    private Date eventDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public boolean isPurchasedItemsVisibility() {
        return purchasedItemsVisibility;
    }

    public void setPurchasedItemsVisibility(boolean purchasedItemsVisibility) {
        this.purchasedItemsVisibility = purchasedItemsVisibility;
    }

    public String getListType() {
        return listType;
    }

    public void setListType(String listType) {
        this.listType = listType;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WishList wishList = (WishList) o;
        return id == wishList.id && ownerId == wishList.ownerId && purchasedItemsVisibility == wishList.purchasedItemsVisibility && Objects.equals(title, wishList.title) && Objects.equals(visibility, wishList.visibility) && Objects.equals(listType, wishList.listType) && Objects.equals(eventDate, wishList.eventDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ownerId, title, visibility, purchasedItemsVisibility, listType, eventDate);
    }
}
