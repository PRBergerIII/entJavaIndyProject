package com.prberger3.flexregistry.persistence;

import com.prberger3.flexregistry.entity.*;
import com.prberger3.flexregistry.util.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// TODO: 4/30/2022 javadocs
// TODO: 5/5/2022 add tests for wishlists

class UserDaoTest {

    GenericDao<User> userDao;
    GenericDao<UserConnection> userConnectionDao;
    GenericDao<WishList> listDao;

    /**
     * Sets up a new DAO and recreates the test database before each test.
     */
    @BeforeEach
    void setUp() {

        Database database = Database.getInstance();
        database.runSQL("cleanUserTests.sql");

        userDao = new GenericDao<>(User.class);
        userConnectionDao = new GenericDao<>(UserConnection.class);
        listDao = new GenericDao<>(WishList.class);


    }

    @Test
    void getAllSuccess() {

        List<User> allUsers = userDao.getAll();
        assertEquals(3, allUsers.size());

    }

    @Test
    void getByIdSuccess() {

        User testUser = new User(
                "pberger", "Paul", "Berger", "pberger@madisoncollege.edu",
                "123 main st", "place", "WI", "12324", "private",
                "I am a size 6", true);
        testUser.setId(1);

        assertEquals(testUser, userDao.getById(1));

    }

    @Test
    void findByPropertyEqual() {

        User testUser = new User(
                "pberger", "Paul", "Berger", "pberger@madisoncollege.edu",
                "123 main st", "place", "WI", "12324", "private",
                "I am a size 6", true);
        testUser.setId(1);

        List<User> foundUsers = userDao.findByPropertyEqual("lastName",
                "Berger");

        assertTrue(foundUsers.contains(testUser));
        assertEquals(1, foundUsers.size());

    }

    @Test
    void saveOrUpdateSuccess() {

        User testUser = new User(
                "pbergerx", "Paulx", "Bergerx", "pberger@madisoncollege.edux",
                "123 main stx", "placex", "Wx", "1232x", "privatx",
                "I am a size 6x", false);

        testUser.setId(1);
        userDao.saveOrUpdate(testUser);
        assertEquals(testUser, userDao.getById(1));

    }

    @Test
    void insertSuccess() {

        User testUser = new User(
                "pbergerx", "Paulx", "Bergerx", "pberger@madisoncollege.edux",
                "123 main stx", "placex", "Wx", "1232x", "privatx",
                "I am a size 6x", false);

        int newId = userDao.insert(testUser);
        assertEquals(testUser, userDao.getById(newId));
    }

    @Test
    void deleteSuccess() {

        User testUser = new User(
                "pberger", "Paul", "Berger", "pberger@madisoncollege.edu",
                "123 main st", "place", "WI", "12324", "private",
                "I am a size 6", true);

        testUser.setId(1);
        userDao.delete(testUser);
        assertNull(userDao.getById(1));

    }

    @Test
    void addFollowerSuccess() {

        User follower = userDao.getById(1);
        User userFollowed = userDao.getById(2);

        assertEquals(5, userConnectionDao.getAll().size());

        follower.followUser(userFollowed);

        userDao.saveOrUpdate(follower);
        userDao.saveOrUpdate(userFollowed);

        assertEquals(6, userConnectionDao.getAll().size());

    }

    @Test
    void acceptFollowRequestSuccess() {

        User follower = userDao.getById(1);
        User userFollowed = userDao.getById(3);

        assertEquals(3, userConnectionDao.findByPropertyEqual(
                "accepted", false).size());

        userFollowed.acceptFollowRequest(follower);

        userDao.saveOrUpdate(follower);
        userDao.saveOrUpdate(userFollowed);

        User updatedFollower = userDao.getById(1);
        User updatedUserFollowed = userDao.getById(3);

        UserConnectionId connectionId = new UserConnectionId(updatedFollower,
                updatedUserFollowed);
        UserConnection updatedConnection = userConnectionDao.getById(connectionId);

        assertEquals(2, userConnectionDao.findByPropertyEqual(
                "accepted", false).size());
        assertTrue(updatedFollower.getUsersFollowed().contains(updatedConnection));
        assertTrue(updatedUserFollowed.getFollowers().contains(updatedConnection));

    }

    @Test
    void addListSuccess() {

        User testUser = userDao.getById(1);
        WishList testList = new WishList(
                null, "Christmas", "public", false, "Holiday",
                LocalDate.parse("2022-12-25"));

        assertEquals(1, testUser.getWishLists().size());
        assertEquals(1, listDao.getAll().size());

        testUser.addWishList(testList);

        userDao.saveOrUpdate(testUser);
        User updatedUser = userDao.getById(1);
        assertEquals(testUser, updatedUser);
        assertTrue(updatedUser.getWishLists().contains(testList));

        testList.setOwner(testUser);
        assertEquals(testList, listDao.getById(2));

    }

    @Test
    void removeListSuccess() {

        User testUser = userDao.getById(1);
        WishList testList = listDao.getById(1);

        assertEquals(1, testUser.getWishLists().size());
        assertEquals(1, listDao.getAll().size());

        testUser.removeWishList(testList);

        userDao.saveOrUpdate(testUser);

        assertEquals(0, testUser.getWishLists().size());
        assertEquals(0, listDao.getAll().size());

    }

}