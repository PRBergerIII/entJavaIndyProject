package com.prberger3.flexregistry.persistence;

import com.prberger3.flexregistry.entity.User;
import com.prberger3.flexregistry.entity.WishList;
import com.prberger3.flexregistry.entity.WishListItem;
import com.prberger3.flexregistry.util.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * This class tests DAO operations on User entities.
 *
 * @author Paul Berger
 */
class WishListItemDaoTest {

    GenericDao<WishListItem> itemDao;
    User testUser;
    WishList testList;

    /**
     * Sets up a new DAO and recreates the test database before each test.
     */
    @BeforeEach
    void setUp() {

        Database database = Database.getInstance();
        database.runSQL("cleanWishListItemTests.sql");

        testUser = new User(
                "pberger", "Paul", "Berger", "pberger@madisoncollege.edu",
                "123 main st", "place", "WI", "12324", "private",
                "I am a size 6", true);
        testList = new WishList(
                testUser, "My Birthday List", "public", true, "Birthday",
                LocalDate.parse("2022-05-31"));
        testUser.setId(1);
        testList.setId(1);

        itemDao = new GenericDao<>(WishListItem.class);

    }

    /**
     * Get all success.
     */
    @Test
    void getAllSuccess() {

        List<WishListItem> allItems = itemDao.getAll();
        assertEquals(8, allItems.size());

    }

    /**
     * Get by id success.
     */
    @Test
    void getByIdSuccess() {

        WishListItem testItem = new WishListItem(
                testList, "Hoosit", true,
                "go to this link: (pretend this is a link)",
                1, "50-100", false, null);
        testItem.setId(1);

        assertEquals(testItem, itemDao.getById(1));

    }

    /**
     * Save or update success.
     */
    @Test
    void saveOrUpdateSuccess() {

        WishListItem testItem = new WishListItem(
                testList, "Hoosits", false,
                "go kick rocks",
                5, "5000-10000", true, "hooray!");
        testItem.setId(1);

        itemDao.saveOrUpdate(testItem);
        assertEquals(testItem, itemDao.getById(1));

    }

    /**
     * Insert success.
     */
    @Test
    void insertSuccess() {

        WishListItem testItem = new WishListItem(
                testList, "Hoosits", false,
                "go kick rocks",
                5, "5000-10000", true, "hooray!");

        int newId = itemDao.insert(testItem);
        assertEquals(testItem, itemDao.getById(newId));

    }

    /**
     * Delete success.
     */
    @Test
    void deleteSuccess() {

        WishListItem testItem = new WishListItem(
                testList, "Hoosit", true,
                "go to this link: (pretend this is a link)",
                1, "50-100", false, null);
        testItem.setId(1);
        
        itemDao.delete(testItem);
        assertNull(itemDao.getById(1));

    }

}