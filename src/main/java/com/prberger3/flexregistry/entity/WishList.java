package com.prberger3.flexregistry.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

// TODO: 5/5/2022 add javadocs

@Entity(name = "WishList")
@Table(name = "wish_list")
public class WishList {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private int id;
    @ManyToOne
    private User owner;
    private String title;
    private String visibility;
    @Column(name = "purchased_items_visibility")
    private boolean purchasedItemsVisibility;
    @Column(name = "list_type")
    private String listType;
    @Column(name = "event_date")
    private LocalDate eventDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
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

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    @Override
    public String toString() {
        return "WishList{" +
                "id=" + id +
                ", ownerId=" + owner +
                ", title='" + title + '\'' +
                ", visibility='" + visibility + '\'' +
                ", purchasedItemsVisibility=" + purchasedItemsVisibility +
                ", listType='" + listType + '\'' +
                ", eventDate=" + eventDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WishList wishList = (WishList) o;
        return  id == wishList.id
                && owner.equals(wishList.owner)
                && purchasedItemsVisibility == wishList.purchasedItemsVisibility
                && Objects.equals(title, wishList.title)
                && Objects.equals(visibility, wishList.visibility)
                && Objects.equals(listType, wishList.listType)
                && Objects.equals(eventDate, wishList.eventDate);

    }

    @Override
    public int hashCode() {
        return Objects.hash(id, owner, title, visibility,
                            purchasedItemsVisibility, listType, eventDate);
    }

}
