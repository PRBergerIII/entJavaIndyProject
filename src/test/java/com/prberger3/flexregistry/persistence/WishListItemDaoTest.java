package com.prberger3.flexregistry.persistence;

import com.prberger3.flexregistry.entity.User;
import com.prberger3.flexregistry.entity.UserConnection;
import com.prberger3.flexregistry.entity.WishList;
import com.prberger3.flexregistry.entity.WishListItem;
import com.prberger3.flexregistry.util.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

// TODO: 4/30/2022 javadocs

class WishListItemDaoTest {

    GenericDao<User> userDao;
    GenericDao<WishList> wishListDao;
    GenericDao<WishListItem> wishListItemDao;

    /**
     * Sets up a new DAO and recreates the test database before each test.
     */
    @BeforeEach
    void setUp() {

        Database database = Database.getInstance();
        database.runSQL("cleanWishListTests.sql");

        userDao = new GenericDao<>(User.class);
        wishListDao = new GenericDao<>(WishList.class);
        wishListItemDao = new GenericDao<>(WishListItem.class);

    }

    @Test
    void getByIdSuccess() {



    }

    @Test
    void saveOrUpdateSuccess() {



    }

    @Test
    void insertSuccess() {


    }

    @Test
    void deleteSuccess() {



    }

    @Test
    void getAllSuccess() {



    }

    @Test
    void addFollowerSuccess() {



    }

}