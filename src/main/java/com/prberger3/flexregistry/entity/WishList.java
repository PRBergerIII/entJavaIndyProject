package com.prberger3.flexregistry.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

// TODO: 5/5/2022 add javadocs

@Entity(name = "WishList")
@Table(name = "wish_list")
public class WishList {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private int id;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;
    private String title;
    private String visibility;
    @Column(name = "purchased_items_visibility")
    private boolean purchasedItemsVisibility;
    @Column(name = "list_type")
    private String listType;
    @Column(name = "event_date")
    private LocalDate eventDate;
    @OneToMany(mappedBy = "wishList",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<WishListItem> items = new ArrayList<>();

    public WishList() {}

    public WishList(User owner, String title, String visibility,
                    boolean purchasedItemsVisibility, String listType,
                    LocalDate eventDate) {

        this.owner = owner;
        this.title = title;
        this.visibility = visibility;
        this.purchasedItemsVisibility = purchasedItemsVisibility;
        this.listType = listType;
        this.eventDate = eventDate;

    }

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

    public List<WishListItem> getItems() {
        return items;
    }

    public void setItems(List<WishListItem> items) {
        this.items = items;
    }

    public void addItem(WishListItem newItem) {

        items.add(newItem);
        newItem.setWishList(this);

    }

    public void removeItem(WishListItem removedItem) {

        items.remove(removedItem);
        removedItem.setWishList(null);

    }

    @Override
    public String toString() {

        return "WishList{" +
                "id=" + id +
                ", owner.id=" + owner.getId() + // To prevent stack overflow
                ", title='" + title + '\'' +
                ", visibility='" + visibility + '\'' +
                ", purchasedItemsVisibility=" + purchasedItemsVisibility +
                ", listType='" + listType + '\'' +
                ", eventDate=" + eventDate +
                ", items=" + items +
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
