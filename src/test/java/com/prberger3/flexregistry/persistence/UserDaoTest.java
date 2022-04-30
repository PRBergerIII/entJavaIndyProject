package com.prberger3.flexregistry.persistence;

import com.prberger3.flexregistry.entity.User;
import com.prberger3.flexregistry.util.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// TODO: 4/30/2022 javadocs
// FIXME: 4/30/2022 indents

class UserDaoTest {

    GenericDao<User> dao;

    /**
     * Sets up a new DAO and recreates the test database before each test.
     */
    @BeforeEach
    void setUp() {

        Database database = Database.getInstance();
        database.runSQL("cleanUserTable.sql");

        dao = new GenericDao<>(User.class);

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
        assertEquals(testUser, dao.getById(1));

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
        dao.saveOrUpdate(testUser);
        assertEquals(testUser, dao.getById(1));

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
        int newId = dao.insert(testUser);
        assertEquals(testUser, dao.getById(newId));
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
        dao.delete(testUser);
        assertNull(dao.getById(1));

    }

    @Test
    void getAllSuccess() {

        List<User> allUsers = dao.getAll();
        assertEquals(3, allUsers.size());

    }

}