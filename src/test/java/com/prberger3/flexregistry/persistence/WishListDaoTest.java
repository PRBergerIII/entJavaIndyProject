package com.prberger3.flexregistry.persistence;

import com.prberger3.flexregistry.entity.User;
import com.prberger3.flexregistry.entity.UserConnection;
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

class WishListDaoTest {

    User testUser;

    GenericDao<WishList> listDao;

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

    }

    @Test
    void getByIdSuccess() {

        WishList testList = new WishList(
                testUser, "My Birthday List", "public", true, "Birthday",
                LocalDate.parse("2022-05-31"));
        testList.setId(1);

        assertEquals(testList, listDao.getById(1));

    }

//    @Test
//    void saveOrUpdateSuccess() {
//
//        WishListItem testList = new WishListItem(
//                testList, "Hoosits", false,
//                "go kick rocks",
//                5, "5000-10000", true, "hooray!");
//        testList.setId(1);
//
//        listDao.saveOrUpdate(testList);
//        assertEquals(testList, listDao.getById(1));
//
//    }
//
//    @Test
//    void insertSuccess() {
//
//        WishListItem testList = new WishListItem(
//                testList, "Hoosits", false,
//                "go kick rocks",
//                5, "5000-10000", true, "hooray!");
//
//        int newId = listDao.insert(testList);
//        assertEquals(testList, listDao.getById(newId));
//
//    }
//
//    @Test
//    void deleteSuccess() {
//
//        WishListItem testList = new WishListItem(
//                testList, "Hoosit", true,
//                "go to this link: (pretend this is a link)",
//                1, "50-100", false, null);
//        testList.setId(1);
//
//        listDao.delete(testList);
//        assertNull(listDao.getById(1));
//
//    }
//
//    @Test
//    void getAllSuccess() {
//
//        List<WishListItem> allItems = listDao.getAll();
//        assertEquals(8, allItems.size());
//
//    }

}