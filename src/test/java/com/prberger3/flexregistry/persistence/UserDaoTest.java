package com.prberger3.flexregistry.persistence;

import com.prberger3.flexregistry.entity.User;
import com.prberger3.flexregistry.entity.UserConnection;
import com.prberger3.flexregistry.entity.UserConnectionId;
import com.prberger3.flexregistry.util.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// TODO: 4/30/2022 javadocs
// FIXME: 4/30/2022 indents

class UserDaoTest {

    GenericDao<User> userDao;
    GenericDao<UserConnection> userConnectionDao;

    /**
     * Sets up a new DAO and recreates the test database before each test.
     */
    @BeforeEach
    void setUp() {

        Database database = Database.getInstance();
        database.runSQL("cleanUserTable.sql");

        userDao = new GenericDao<>(User.class);
        userConnectionDao = new GenericDao<>(UserConnection.class);

    }

    @Test
    void getByIdSuccess() {

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
        assertEquals(testUser, userDao.getById(1));

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

    @Test
    void addFollowerSuccess() {

        User follower = userDao.getById(1);
        User userFollowed = userDao.getById(2);

        UserConnection newConnection =
                new UserConnection(follower, userFollowed);
        UserConnectionId newConnectionId =
                userConnectionDao.insertConnection(newConnection);
        UserConnection connectionInserted =
                userConnectionDao.getById(newConnectionId);

        User followerInserted = connectionInserted.getFollower();
        User userFollowedInserted = connectionInserted.getUserFollowed();

        assertEquals(follower, followerInserted);
        assertEquals(userFollowed, userFollowedInserted);

        follower.followUser(userFollowed);


    }

}