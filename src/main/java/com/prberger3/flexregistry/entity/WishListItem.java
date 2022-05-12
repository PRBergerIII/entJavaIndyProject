package com.prberger3.flexregistry.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

/**
 * This is the WishListItem class for the Flex Registry app.
 *
 * @author Paul Berger
 */
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
    private boolean specificItem;
    private String details;
    private int priority;
    @Column(name = "price_range")
    private String priceRange;
    private boolean purchased;
    @Column(name = "purchased_message")
    private String purchasedMessage;

    /**
     * Instantiates a new Wish list item.
     */
    public WishListItem() {}

    /**
     * Instantiates a new Wish list item.
     *
     * @param wishList         the wish list
     * @param name             the name
     * @param specificItem     the specific item
     * @param details          the details
     * @param priority         the priority
     * @param priceRange       the price range
     * @param purchased        the purchased
     * @param purchasedMessage the purchased message
     */
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
     * Gets wish list.
     *
     * @return the wish list
     */
    public WishList getWishList() {
        return wishList;
    }

    /**
     * Sets wish list.
     *
     * @param wishList the wish list
     */
    public void setWishList(WishList wishList) {
        this.wishList = wishList;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets specific item.
     *
     * @return the specific item
     */
    public boolean getSpecificItem() {
        return specificItem;
    }

    /**
     * Sets specific item.
     *
     * @param specificItem the specific item
     */
    public void setSpecificItem(boolean specificItem) {
        this.specificItem = specificItem;
    }

    /**
     * Gets details.
     *
     * @return the details
     */
    public String getDetails() {
        return details;
    }

    /**
     * Sets details.
     *
     * @param details the details
     */
    public void setDetails(String details) {
        this.details = details;
    }

    /**
     * Gets priority.
     *
     * @return the priority
     */
    public int getPriority() {
        return priority;
    }

    /**
     * Sets priority.
     *
     * @param priority the priority
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }

    /**
     * Gets price range.
     *
     * @return the price range
     */
    public String getPriceRange() {
        return priceRange;
    }

    /**
     * Sets price range.
     *
     * @param priceRange the price range
     */
    public void setPriceRange(String priceRange) {
        this.priceRange = priceRange;
    }

    /**
     * Is purchased boolean.
     *
     * @return the boolean
     */
    public boolean isPurchased() {
        return purchased;
    }

    /**
     * Sets purchased.
     *
     * @param purchased the purchased
     */
    public void setPurchased(boolean purchased) {
        this.purchased = purchased;
    }

    /**
     * Gets purchased message.
     *
     * @return the purchased message
     */
    public String getPurchasedMessage() {
        return purchasedMessage;
    }

    /**
     * Sets purchased message.
     *
     * @param purchasedMessage the purchased message
     */
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
