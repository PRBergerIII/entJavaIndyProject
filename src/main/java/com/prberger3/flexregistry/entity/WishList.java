package com.prberger3.flexregistry.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

/**
 * This is the WishList class for the Flex Registry app.
 *
 * @author Paul Berger
 */
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
    private Set<WishListItem> items = new HashSet<>();

    /**
     * Instantiates a new Wish list.
     */
    public WishList() {}

    /**
     * Instantiates a new Wish list.
     *
     * @param owner                    the owner
     * @param title                    the title
     * @param visibility               the visibility
     * @param purchasedItemsVisibility the purchased items visibility
     * @param listType                 the list type
     * @param eventDate                the event date
     */
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

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets owner.
     *
     * @return the owner
     */
    public User getOwner() {
        return owner;
    }

    /**
     * Sets owner.
     *
     * @param owner the owner
     */
    public void setOwner(User owner) {
        this.owner = owner;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets visibility.
     *
     * @return the visibility
     */
    public String getVisibility() {
        return visibility;
    }

    /**
     * Sets visibility.
     *
     * @param visibility the visibility
     */
    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    /**
     * Is purchased items visibility boolean.
     *
     * @return the boolean
     */
    public boolean isPurchasedItemsVisibility() {
        return purchasedItemsVisibility;
    }

    /**
     * Sets purchased items visibility.
     *
     * @param purchasedItemsVisibility the purchased items visibility
     */
    public void setPurchasedItemsVisibility(boolean purchasedItemsVisibility) {
        this.purchasedItemsVisibility = purchasedItemsVisibility;
    }

    /**
     * Gets list type.
     *
     * @return the list type
     */
    public String getListType() {
        return listType;
    }

    /**
     * Sets list type.
     *
     * @param listType the list type
     */
    public void setListType(String listType) {
        this.listType = listType;
    }

    /**
     * Gets event date.
     *
     * @return the event date
     */
    public LocalDate getEventDate() {
        return eventDate;
    }

    /**
     * Sets event date.
     *
     * @param eventDate the event date
     */
    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    /**
     * Gets items.
     *
     * @return the items
     */
    public Set<WishListItem> getItems() {
        return items;
    }

    /**
     * Sets items.
     *
     * @param items the items
     */
    public void setItems(Set<WishListItem> items) {
        this.items = items;
    }

    /**
     * Add item.
     *
     * @param newItem the new item
     */
    public void addItem(WishListItem newItem) {

        items.add(newItem);
        newItem.setWishList(this);

    }

    /**
     * Remove item.
     *
     * @param removedItem the removed item
     */
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
