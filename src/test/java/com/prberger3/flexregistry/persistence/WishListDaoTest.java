package com.prberger3.flexregistry.persistence;

import com.prberger3.flexregistry.entity.User;
import com.prberger3.flexregistry.entity.WishList;
import com.prberger3.flexregistry.entity.WishListItem;
import com.prberger3.flexregistry.util.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class tests DAO operations on User entities.
 *
 * @author Paul Berger
 */
class WishListDaoTest {

    User testUser;
    GenericDao<WishList> listDao;
    GenericDao<WishListItem> itemDao;

    /**
     * Sets up a new DAO and recreates the test database before each test.
     */
    @BeforeEach
    void setUp() {

        Database database = Database.getInstance();
        database.runSQL("cleanWishListTests.sql");

        testUser = new User(
                "pberger", "Paul", "Berger", "pberger@madisoncollege.edu",
                "123 main st", "place", "WI", "12324", "private",
                "I am a size 6", true);
        testUser.setId(1);

        listDao = new GenericDao<>(WishList.class);
        itemDao = new GenericDao<>(WishListItem.class);

    }

    /**
     * Tests for get all success.
     */
    @Test
    void getAllSuccess() {

        List<WishList> allItems = listDao.getAll();
        assertEquals(4, allItems.size());

    }

    /**
     * Get by id success.
     */
    @Test
    void getByIdSuccess() {

        WishList testList = new WishList(
                testUser, "My Birthday List", "public", true, "Birthday",
                LocalDate.parse("2022-05-31"));
        testList.setId(1);

        assertEquals(testList, listDao.getById(1));

    }

    /**
     * Find by property equal success.
     */
    @Test
    void findByPropertyEqualSuccess() {

        WishList testList = new WishList(
                testUser, "My Birthday List", "public", true, "Birthday",
                LocalDate.parse("2022-05-31"));
        testList.setId(1);

        List<WishList> foundLists = listDao.findByPropertyEqual("title",
                "My Birthday List");

        assertTrue(foundLists.contains(testList));
        assertEquals(1, foundLists.size());

    }

    /**
     * Save or update success.
     */
    @Test
    void saveOrUpdateSuccess() {

        WishList testList = new WishList(
                testUser, "My Birthday List 2", "public", false, "Birthday",
                LocalDate.parse("2023-05-31"));
        testList.setId(1);

        listDao.saveOrUpdate(testList);
        assertEquals(testList, listDao.getById(1));

    }

    /**
     * Insert success.
     */
    @Test
    void insertSuccess() {

        WishList testList = new WishList(
                testUser, "My Birthday List 2", "public", false, "Birthday",
                LocalDate.parse("2023-05-31"));

        int newId = listDao.insert(testList);
        assertEquals(testList, listDao.getById(newId));

    }

    /**
     * Delete success.
     */
    @Test
    void deleteSuccess() {

        WishList testList = new WishList(
                testUser, "My Birthday List", "public", true, "Birthday",
                LocalDate.parse("2022-05-31"));
        testList.setId(1);

        listDao.delete(testList);
        assertNull(listDao.getById(1));
        assertNull(itemDao.getById(1));

    }

    /**
     * Delete list with items success.
     */
    @Test
    void deleteListWithItemsSuccess() {

        WishList testList = listDao.getById(1);

        assertEquals(4, listDao.getAll().size());
        assertEquals(1, itemDao.getAll().size());

        listDao.delete(testList);

        assertEquals(3, listDao.getAll().size());
        assertEquals(0, itemDao.getAll().size());

    }

    /**
     * Insert list with item success.
     */
    @Test
    void insertListWithItemSuccess() {

        WishList testList = new WishList(
                testUser, "My Birthday List 2", "public", false, "Birthday",
                LocalDate.parse("2023-05-31"));
        WishListItem testItem = new WishListItem(
                null, "Hoosit", true,
                "go to this link: (pretend this is a link)",
                1, "50-100", false, null);

        assertEquals(0, testList.getItems().size());
        assertEquals(1, itemDao.getAll().size());

        testList.addItem(testItem);

        int newId = listDao.insert(testList);
        WishList updatedList = listDao.getById(newId);
        assertEquals(testList, updatedList);
        assertTrue(updatedList.getItems().contains(testItem));

        testItem.setWishList(testList);
        assertEquals(testItem, itemDao.getById(2));

    }

    /**
     * Add list item success.
     */
    @Test
    void addListItemSuccess() {

        WishList testList = listDao.getById(1);
        WishListItem testItem = new WishListItem(
                null, "Hoosit", true,
                "go to this link: (pretend this is a link)",
                1, "50-100", false, null);

        assertEquals(1, testList.getItems().size());
        assertEquals(1, itemDao.getAll().size());

        testList.addItem(testItem);

        listDao.saveOrUpdate(testList);
        WishList updatedList = listDao.getById(1);
        assertEquals(testList, updatedList);
        assertTrue(updatedList.getItems().contains(testItem));

        testItem.setWishList(testList);
        assertEquals(testItem, itemDao.getById(2));

    }

    /**
     * Remove list item success.
     */
    @Test
    void removeListItemSuccess() {

        WishList testList = listDao.getById(1);
        WishListItem testItem = itemDao.getById(1);

        assertEquals(1, testList.getItems().size());
        assertEquals(1, itemDao.getAll().size());

        testList.removeItem(testItem);

        listDao.saveOrUpdate(testList);

        assertEquals(0, testList.getItems().size());
        assertEquals(0, itemDao.getAll().size());

    }

}