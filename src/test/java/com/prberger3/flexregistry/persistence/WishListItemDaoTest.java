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

// TODO: 4/30/2022 javadocs

class WishListItemDaoTest {

    GenericDao<User> userDao;
    GenericDao<WishList> listDao;
    GenericDao<WishListItem> itemDao;

    /**
     * Sets up a new DAO and recreates the test database before each test.
     */
    @BeforeEach
    void setUp() {

        Database database = Database.getInstance();
        database.runSQL("cleanWishListTests.sql");

        userDao = new GenericDao<>(User.class);
        listDao = new GenericDao<>(WishList.class);
        itemDao = new GenericDao<>(WishListItem.class);

    }

    @Test
    void getByIdSuccess() {

        User testUser = new User(
                "pberger", "Paul", "Berger", "pberger@madisoncollege.edu",
                "123 main st", "place", "WI", "12324", "private",
                "I am a size 6", true);
        WishList testList = new WishList(
                testUser, "My Birthday List", "public", true, "Birthday",
                LocalDate.parse("2022-05-31"));
        WishListItem testItem = new WishListItem(
                testList, "Hoosit", true,
                "go to this link: (pretend this is a link)",
                1, "50-100", false, null);

        testUser.setId(1);
        testList.setId(1);
        testItem.setId(1);

        assertEquals(testItem, itemDao.getById(1));

    }

    @Test
    void saveOrUpdateSuccess() {

        User testUser = new User("pbergerx",
                "Paulx",
                "Bergerx",
                "pberger@madisoncollege.edux",
                "123 main stx",
                "placex",
                "Wx",
                "1232x",
                "privatx",
                "I am a size 6x",
                false);
        testUser.setId(1);
        userDao.saveOrUpdate(testUser);
        assertEquals(testUser, userDao.getById(1));

    }

    @Test
    void insertSuccess() {

        User testUser = new User("pbergerx",
                "Paulx",
                "Bergerx",
                "pberger@madisoncollege.edux",
                "123 main stx",
                "placex",
                "Wx",
                "1232x",
                "privatx",
                "I am a size 6x",
                false);
        int newId = userDao.insert(testUser);
        assertEquals(testUser, userDao.getById(newId));
    }

    @Test
    void deleteSuccess() {

        User testUser = new User("pberger",
                "Paul",
                "Berger",
                "pberger@madisoncollege.edu",
                "123 main st",
                "place",
                "WI",
                "12324",
                "private",
                "I am a size 6",
                true);
        testUser.setId(1);
        userDao.delete(testUser);
        assertNull(userDao.getById(1));

    }

    @Test
    void getAllSuccess() {

        List<User> allUsers = userDao.getAll();
        assertEquals(3, allUsers.size());

    }

}